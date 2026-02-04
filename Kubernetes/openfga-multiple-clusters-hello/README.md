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
# setup cloud cloud cluster
set context 'cloud'  # fish
k3d cluster create "$context" --config "k3d-$context.yaml" --kubeconfig-update-default=false
k3d kubeconfig write "$context" --output ./"$context.kubeconfig.yaml"

# export KUBECONFIG=./"$context.kubeconfig.yaml"
set -x KUBECONFIG ./"$context.kubeconfig.yaml"  # fish

# inspect clusters aka contexts
kubectl config get-contexts
kubectl config use-context "k3d-$context"

kubectl create namespace "ns-$context"
# -i/--install; -n/--namespace -f/--values
helm upgrade -i openfga openfga/openfga -n "ns-$context" -f openfga.helmchart.yaml

# check OpenFGA accessibility
curl -sS -X GET 0.0.0.0:8080/healthz
curl -sS -X GET 0.0.0.0:8080/stores

# create a store
set fga_store_id (fga store create --name mystore | jq -r .store.id)  # fish
fga store list --api-url 'http://0.0.0.0:8080'
fga store get --api-url 'http://0.0.0.0:8080' --store-id "$fga_store_id"

# import model + tuples from store.fga.yaml
# caution: import is _purely_ additive; tuples or model definitions won't get deleted, only extended
fga store import --api-url 'http://0.0.0.0:8080' --store-id "$fga_store_id" --file store.fga.yaml
fga tuple read --api-url 'http://0.0.0.0:8080' --store-id "$fga_store_id"  # just check

# visit http://localhost:3000/playground for the Playground UI
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

curl -sS -X GET 0.0.0.0:9090/healthz
curl -sS -X GET 0.0.0.0:9090/stores

# build & make the Cronjob Docker image available inside the cluster
# then apply the import-cronjob.yaml
# Note: the CronJob expects the cloud OpenFGA API at http://host.k3d.internal:8080
docker build -t import-fga:latest import-cronjob/
k3d image import -c os import-fga:latest
kubectl -n "ns-$context" apply -f import-cronjob/import-cronjob.yaml
```

## Playing Around
Don't use the interactive web playground.
The playground seems to be a bit shitty with its cookies or whatnot. I don't trust it. Especially
with 2 playgrounds running at the same time on different ports. When I make changes to a store in 1 playground, they get reflected in the other playground (on the other OpenFGA instance) as well. What the fuck.

```bash
echo 'cloud store:'

fga store list --api-url "http://0.0.0.0:8080"
set cloud_store_id (fga store list --api-url "http://0.0.0.0:8080" | jq -r '.stores[0].id')  # fish
fga tuple read --api-url "http://0.0.0.0:8080" --store-id "$cloud_store_id"

echo 'OS store'

# write a tuple
fga tuple write \
  --api-url "http://0.0.0.0:9090" \
  --store-id "$os_store_id" \
    user:katzolini developer organization:miauuuuuu

fga store list --api-url "http://0.0.0.0:9090"
set os_store_id (fga store list --api-url "http://0.0.0.0:9090" | jq -r '.stores[0].id')  # fish
fga tuple read --api-url "http://0.0.0.0:9090" --store-id "$os_store_id"
```

Manipulations in cloud should reflect in os after a short while.
Also look into e.g. `pods` in `k9s`.

## Troubleshooting & Tips
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
fga store delete --force --store-id $fga_store_id  # --force: don't ask for confirmation
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


## Prose Explanation of the Project

This project is a small, concrete demo of "multi-cluster OpenFGA," built so you can see how two
Kubernetes clusters can each run their own OpenFGA instance while one cluster continuously copies
authorization data from the other. Think of it as a lab setup for understanding cross-cluster data
flow, with OpenFGA stores as the data being synchronized.

At a high level there are two Kubernetes clusters created with k3d: a "cloud" cluster and an "os"
cluster. Each cluster runs OpenFGA via the official Helm chart, and each OpenFGA instance exposes a
service on the host so you can reach it locally. The cloud cluster is the source of truth. It has a
store called mystore that is seeded with a model and sample tuples. The os cluster is the consumer.
It does not maintain its own authoritative model or tuples; instead, a Kubernetes CronJob running
inside the os cluster regularly exports the store from the cloud cluster and imports it into its
local OpenFGA instance. In practice this simulates "cloud-hosted authorization data" being
replicated into a local or edge environment.

The mechanics of the clusters are straightforward. The two k3d configs (k3d-cloud.yaml and
k3d-os.yaml) each create a single-node cluster with a k3d load balancer. The cloud cluster maps its
OpenFGA service to host port 8080 and the playground UI to 3000. The os cluster maps its OpenFGA
service to host port 9090 and the playground UI to 3001. This matters because it lets you hit the
two OpenFGA APIs from your host machine without dealing with internal cluster networking. You can
curl the health endpoints or use the OpenFGA CLI (fga) against the two API endpoints as if they were
ordinary local services.

OpenFGA itself is deployed with a minimal Helm configuration (openfga.helmchart.yaml). The chart
uses the official openfga/openfga image and an in-memory datastore, which keeps this demo
self-contained: there is no PostgreSQL dependency or external storage to set up. The chart also
enables the built-in Playground UI (exposed through the same service) and creates a dedicated
service account so the deployment has a clear Kubernetes identity. The service type is a
LoadBalancer, which k3d implements via its internal load balancer node and port mappings, so the
service becomes accessible at the host ports mentioned above.

The store model and initial data live in store.fga.yaml. This file declares a simple domain with
user, organization, and cell types, plus relations like admin, developer, and member. It is
intentionally compact but still demonstrates OpenFGA's relationship-based access rules: for example,
a cell can inherit admin or developer roles from its owning organization. This gives you enough
structure to test real authorization queries (like "can user bob manage cell1?") without getting
lost in a complicated schema.

The cross-cluster sync is implemented as a Kubernetes CronJob in the os cluster
(import-cronjob/import-cronjob.yaml). The CronJob runs every minute. It starts a small container
image that bundles the OpenFGA CLI, jq, and a shell script. That script
(import-cronjob/import-store.sh) is the heart of the workflow: it finds the mystore ID in the cloud
cluster by calling the cloud OpenFGA API, exports that store to a file, creates a fresh store in the
os cluster, imports the file, and then deletes any older stores to keep the os instance clean. The
result is a simple "pull-based" replication loop that keeps the os store closely aligned with the
cloud source.

The CronJob environment variables show how cross-cluster access is achieved. The cloud API is
reachable from inside the os cluster via http://host.k3d.internal:8080 . In k3d, host.k3d.internal
 resolves to the host machine, which is where the cloud cluster's load balancer port is exposed.
 Meanwhile, the os cluster accesses its own OpenFGA service at http://openfga:8080 because that is
 the internal Service name created by the Helm chart. This dual view-external host address for the
 cloud, internal service name for os-is a key Kubernetes networking idea, and this demo makes it
 visible in a very tangible way.

This project gives you a feel for how they fit together: k3d provides the lightweight local
clusters; Helm packages an application and its Service into a predictable deployment; the Service
exposes a stable name and port inside the cluster; and the CronJob schedules a container to run at
fixed intervals, which is a standard pattern for sync and housekeeping tasks. The demo also
highlights how you can bridge cluster boundaries without introducing complex networking overlays: in
local k3d, the host acts as a simple rendezvous point.

In short, this project is a minimal, repeatable example of "OpenFGA in multiple clusters with
replication." It shows how authorization data can be treated like any other API-accessible state
that's periodically exported and imported. Once you understand the flow here, it becomes easier to
imagine real-world variants: using a persistent datastore, adding authentication between clusters,
or replacing the simple CronJob with a smarter sync pipeline. For a learning project, it
deliberately keeps those out of scope so the Kubernetes and OpenFGA concepts stay front and center.

