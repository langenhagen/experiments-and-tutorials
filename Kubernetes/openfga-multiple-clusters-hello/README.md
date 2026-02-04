# OpenFGA Multiple Clusters Hello
Have 2 k8s clusters, each with an OpenFGA instance and populate one OpenFGA service from the other.

I largely use `k9s` for live inspection.

## Prerequisites:
```bash
# Setup the Helm repo; only needs to run once per machine
helm repo add openfga https://openfga.github.io/helm-charts
helm repo update  # helm's pendant to `apt update`
```

## Set up the `cloud` cluster:
```bash
# setup cloud cloud cluster
set context 'cloud'  # fish
k3d cluster create "$context" --kubeconfig-update-default=false
k3d kubeconfig write "$context" --output ./"$context.kubeconfig.yaml"

# export KUBECONFIG=./"$context.kubeconfig.yaml"
set -x KUBECONFIG ./"$context.kubeconfig.yaml"  # fish

# inspect clusters aka contexts
kubectl config get-contexts
kubectl config use-context "k3d-$context"

kubectl create namespace "ns-$context"
# -i/--install; -n/--namesapce -f/--values
helm upgrade -i openfga openfga/openfga -n "ns-$context" -f openfga.helmchart.yaml

# Forward both the HTTP API and the Playground UI to access the API and Playground locally
# this fucking blocks the CLI
kubectl -n "ns-$context" port-forward svc/openfga --address 0.0.0.0 8080:8080 3000:3000

# check OpenFGA accessibility
curl -sS -X GET 0.0.0.0:8080/healthz
curl -sS -X GET 0.0.0.0:8080/stores

# create a store
set -x FGA_STORE_ID (fga store create --name mystore | jq -r .store.id)  # fish
fga store list
fga store get

# import model + tuples from store.fga.yaml
# caution: import is _purely_ additive; tuples or model definitions won't get deleted, only extended
fga store import --file store.fga.yaml
fga tuple read  # just check

# visit http://localhost:3000/playground for the Playground UI (works properly in Firefox for me)
```

## Setup the `OS` cluster:
```bash
set context 'os'  # fish
k3d cluster create "$context" --kubeconfig-update-default=false
k3d kubeconfig write "$context" --output ./"$context.kubeconfig.yaml"

# export KUBECONFIG=./"$context.kubeconfig.yaml"
set -x KUBECONFIG ./"$context.kubeconfig.yaml"  # fish

kubectl config get-contexts
kubectl config use-context "k3d-$context"
kubectl create namespace "ns-$context"
helm upgrade -i openfga openfga/openfga -n "ns-$context" -f openfga.helmchart.yaml

kubectl -n "ns-$context" port-forward svc/openfga 9090:8080 3001:3000
curl -sS -X GET 0.0.0.0:9090/healthz
curl -sS -X GET 0.0.0.0:9090/stores

# build & make the Cronjob Docker image available inside the cluster
docker build -t import-fga:latest import-cronjob/
k3d image import -c os import-fga:latest

# set KUBECONFIG again bc you are probably in a new shell
set context 'os'  # fish
# export KUBECONFIG=./"$context.kubeconfig.yaml"
set -x KUBECONFIG ./"$context.kubeconfig.yaml"  # fish

# apply the import-cronjob.yaml
# Note: the CronJob expects the cloud OpenFGA API at http://host.k3d.internal:8080,
# so keep the cloud port-forward running with --address 0.0.0.0
kubectl -n "ns-$context" apply -f import-cronjob/import-cronjob.yaml
```

## Playing Around
The playground seems to be a bit shitty with its cookies or whatnot, I don't trust it, especially with 2 playgrounds running at the same time on different ports. That seems to confuse the poor playgrounds, maybe because of cookies.

```bash
echo 'cloud store:'
fga store list --api-url "http://0.0.0.0:8080"
set cloud_store_id (fga store list --api-url "http://0.0.0.0:8080" | jq -r '.stores[0].id')  # fish
fga tuple read --api-url "http://0.0.0.0:8080" --store-id "$cloud_store_id"
echo 'OS store'
fga store list --api-url "http://0.0.0.0:9090"
set os_store_id (fga store list --api-url "http://0.0.0.0:9090" | jq -r '.stores[0].id')  # fish
fga tuple read --api-url "http://0.0.0.0:9090" --store-id "$os_store_id"
```

Manipulations in cloud should reflect in os after a short while.
Also look into e.g. `pods` in `k9s`.

## Troubleshooting
```bash
# nice combo to reapply the cronjob docker image in case you made changes to it:
docker build -t import-fga import-cronjob/ && k3d image import -c os import-fga:latest && kubectl -n "ns-os" delete cronjob import-fga && kubectl -n "ns-os" apply -f import-cronjob/import-cronjob.yaml

# attach to a cronjob pod
kubectl -n mynamespace get pods --sort-by=.metadata.creationTimestamp
kubectl -n "ns-os" exec -it MYPOD -- sh
```

## Teardown:
```bash
# cloud
fga store delete --force --store-id $FGA_STORE_ID  # --force: don't ask for confirmation
fga store list
set context 'cloud'  # fish
kubectl config use-context "k3d-$context"
helm -n "ns-$context" uninstall openfga
kubectl delete namespace "ns-$context"
k3d cluster delete "$context"

# OS
set context 'os'  # fish
kubectl config use-context "k3d-$context"
helm -n "ns-$context" uninstall openfga
kubectl delete namespace "ns-$context"
k3d cluster delete "$context"
```
