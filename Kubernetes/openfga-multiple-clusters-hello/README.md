# OpenFGA Multiple Clusters Hello
Have 2 k8s clusters, each with an OpenFGA instance and populate one OpenFGA service from the other.

I largely use `k9s` for live inspection.

## Prerequisites:
```bash
# Setup the Helm repo; only needs to run once per machine
helm repo add openfga https://openfga.github.io/helm-charts
helm repo update  # helm's counterpart to `apt update`
```

## Set up the `cloud` cluster:
```bash
# setup cloud cluster
set context 'cloud'  # fish
k3d cluster create "$context" --config "k3d-$context.yaml" --kubeconfig-update-default=false
k3d kubeconfig write "$context" --output ./"$context.kubeconfig.yaml"

# export KUBECONFIG=./"$context.kubeconfig.yaml"  # bash
set -x KUBECONFIG ./"$context.kubeconfig.yaml"  # fish

# inspect clusters aka contexts
kubectl config get-contexts
kubectl config use-context "k3d-$context"

kubectl create namespace "ns-$context"
# -i/--install; -n/--namespace -f/--values
helm upgrade -i openfga openfga/openfga -n "ns-$context" -f openfga.helmchart.yaml

set cloud_url 'http://0.0.0.0:8080'  # fish

# check OpenFGA accessibility
curl -sS -X GET "$cloud_url/healthz"
curl -sS -X GET "$cloud_url/stores"

# create a store
set fga_store_id (fga store create --api-url "$cloud_url" --name mystore | jq -r .store.id)  # fish
fga store list --api-url "$cloud_url"
fga store get --api-url "$cloud_url" --store-id "$fga_store_id"

# import model + tuples from store.fga.yaml
# caution: import is _purely_ additive; tuples or model definitions won't get deleted, only extended
fga store import --api-url "$cloud_url" --store-id "$fga_store_id" --file store.fga.yaml
fga tuple read --api-url "$cloud_url" --store-id "$fga_store_id"  # just checking

# build & make the cloud export CronJob image available inside the cluster
# then apply the dump file server and then apply the CronJob
docker build -t export-fga:latest export-cronjob/
k3d image import -c cloud export-fga:latest
kubectl -n "ns-$context" apply -f export-cronjob/store-dump-server.yaml
kubectl -n "ns-$context" apply -f export-cronjob/export-cronjob.yaml

# curl the dumped file
curl http://0.0.0.0:8082/store-dump.fga.yaml

# consider visiting http://localhost:3000/playground for the Playground UI
# Has issues in Chrome for me, presumably because of the I-Dont-Care-About-Cookies Extension
# Works properly in Firefox for me
# The Playground is a buggy mess however that becomes only obvious if you do more than the simplest
# shit
```

## Setup the `OS` cluster:
```bash
set context 'os'  # fish
k3d cluster create "$context" --config "k3d-$context.yaml" --kubeconfig-update-default=false
k3d kubeconfig write "$context" --output ./"$context.kubeconfig.yaml"

# export KUBECONFIG=./"$context.kubeconfig.yaml"
set -x KUBECONFIG ./"$context.kubeconfig.yaml"  # fish

kubectl config get-contexts
kubectl config use-context "k3d-$context"
kubectl create namespace "ns-$context"
helm upgrade -i openfga openfga/openfga -n "ns-$context" -f openfga.helmchart.yaml

set os_url 'http://0.0.0.0:9090'  # fish

curl -sS -X GET "$os_url/healthz"
curl -sS -X GET "$os_url/stores"

# build & make the OS CronJob Docker image available inside the cluster
# then apply the import-cronjob.yaml
# Note: the CronJob expects the cloud dump file at http://host.k3d.internal:8082/store-dump.fga.yaml
docker build -t import-fga:latest import-cronjob/
k3d image import -c os import-fga:latest
kubectl -n "ns-$context" apply -f import-cronjob/import-cronjob.yaml
```

## Playing Around
__Don't use the interactive web playground.__
The playground seems to be a bit shitty with its cookies or whatnot. I don't trust it. Especially
with 2 playgrounds running at the same time on different ports. When I make changes to a store in 1
playground, they get reflected in the other playground (on the other OpenFGA instance) as well. What
the fuck.

```bash
set cloud_url 'http://0.0.0.0:8080'  # fish
set os_url 'http://0.0.0.0:9090'  # fish

echo 'cloud store:'

set cloud_store_id (fga store list --api-url "$cloud_url" | jq -r '.stores[0].id')  # fish
fga tuple read --api-url "$cloud_url" --store-id "$cloud_store_id"

# write a tuple to cloud
fga tuple write \
  --api-url "$cloud_url" \
  --store-id "$cloud_store_id" \
    user:bello developer organization:wuff

# wait a bit ...

# curl the dumped file
# the new tuple should be there
curl http://0.0.0.0:8082/store-dump.fga.yaml

echo 'OS store'

set os_store_id (fga store list --api-url "$os_url" | jq -r '.stores[0].id')  # fish

# write a tuple to OS
fga tuple write \
  --api-url "$os_url" \
  --store-id "$os_store_id" \
    user:katzolini developer organization:miauuuuuu

# should show the new tuple for the dog (from cloud) and for the cat (OS)
fga tuple read --api-url "$os_url" --store-id "$os_store_id"

# wait a bit ...

# after a while, the new cat-tuple should NOT show the new OS tuple since it got
# overridden by the cloud dump & import CronJob
set os_store_id (fga store list --api-url "$os_url" | jq -r '.stores[0].id')  # fish
fga tuple read --api-url "$os_url" --store-id "$os_store_id"

echo 'cloud store:'

set cloud_store_id (fga store list --api-url "$cloud_url" | jq -r '.stores[0].id')  # fish
fga tuple delete --api-url "$cloud_url" --store-id "$cloud_store_id" user:bello developer organization:wuff

# wait a bit ...

# curl the dumped file
# the dog tuple should be gone
curl http://0.0.0.0:8082/store-dump.fga.yaml

echo 'OS store:'

# the dog tuple should be gone from the OS store as well
set os_store_id (fga store list --api-url "$os_url" | jq -r '.stores[0].id')  # fish
fga tuple read --api-url "$os_url" --store-id "$os_store_id"
```

In short:
Manipulations in cloud should reflect in OS after a short while.
Manipulations to OS should be undone in OS after a short while.


## Troubleshooting & Tips
```bash
# nice combo to reapply the cloud CronJob Docker image in case you made changes to it:
docker build -t export-fga:latest export-cronjob/ && k3d image import -c cloud export-fga:latest && kubectl -n "ns-cloud" delete cronjob export-fga && kubectl -n "ns-cloud" apply -f export-cronjob/export-cronjob.yaml

# same idea for the cloud OS import CronJob:
docker build -t import-fga:latest import-cronjob/ && k3d image import -c os import-fga:latest && kubectl -n "ns-os" delete cronjob import-fga && kubectl -n "ns-os" apply -f import-cronjob/import-cronjob.yaml

# attach to a CronJob pod
kubectl -n ns-os get pods --sort-by=.metadata.creationTimestamp
kubectl -n ns-os exec -it MYPOD -- sh

# Verify the dump file server's port mapping works
kubectl -n ns-cloud get svc openfga-dump -o wide

# Get the pod(s) for the dump file server
kubectl -n ns-cloud get pods -l app=openfga-store-dump-server

# Get the jobs (spawned from CronJobs); remember to set the right $KUBECONFIG
kubectl -n ns-cloud get jobs --sort-by=.metadata.creationTimestamp
kubectl -n ns-os get jobs --sort-by=.metadata.creationTimestamp

# Get the pods
kubectl -n ns-cloud get pods --sort-by=.metadata.creationTimestamp

# get logs  (or use k9s like a human being)
kubectl -n ns-cloud log pod/MYPOD


# locate the dump file on the pod of the openfga-store-dump-server
kubectl -n ns-cloud exec -it MYPOD -- ls -l /usr/share/nginx/html

# wget the file from the store dump server
kubectl -n ns-cloud exec -it MYPOD -- wget -qO- http://localhost/store-dump.fga.yaml
```


## Teardown:
```bash
# cloud
set context 'cloud'  # fish
k3d cluster delete "$context"

# OS
set context 'os'  # fish
k3d cluster delete "$context"
```

## Prose Explanation of the Project

This project is a small demo of "multi-cluster OpenFGA". Two k3d clusters ("cloud" and "os") each
run their own OpenFGA instance. The cloud cluster is the source of truth and holds a store called
mystore seeded from store.fga.yaml. The os cluster is a consumer that periodically replicates from
cloud.

The two clusters are exposed on the host for easy testing. The cloud OpenFGA HTTP API is on 8080.
The os OpenFGA HTTP API is on 9090. As a note, OpenFGA's gRPC port is 8081, so the dump server uses
8082 to avoid collisions.

Sync is implemented as two CronJobs plus a tiny HTTP file server in the cloud cluster. The cloud
export CronJob (`export-cronjob/export-cronjob.yaml`) runs a small image with the OpenFGA CLI and
`export-cronjob/export-store.sh`. It exports the store to a file on a shared PersistentVolumeClaim
(PVC). An nginx Deployment serves that PVC via `export-cronjob/store-dump-server.yaml`, and a
LoadBalancer Service exposes it on 8082 so other clusters can fetch the file. Only this CronJob
writes the file.

In the os cluster, the import CronJob (`import-cronjob/import-cronjob.yaml`) downloads the file over
HTTP, creates a fresh store, imports the dump, and deletes any older stores. That keeps the os
OpenFGA instance aligned with cloud without needing direct API access to the cloud OpenFGA service.

A key networking trick used in this toy example is host.k3d.internal: from inside the os cluster it
resolves to the host machine, which is where the cloud cluster's load balancer port (8082) is
exposed. That lets a pod in one cluster fetch a file served by another cluster without any
cross-cluster overlay networking.
