# OpenFGA Helm Chart Hello
Showcase how to deploy OpenFGA as a Service based on its Helm chart.

Deploy [OpenFGA](https://openfga.dev/) into a local [k3d](https://k3d.io/) cluster via its official
Helm chart.

- File `openfga.yaml`: minimal overrides for the upstream chart so that OpenFGA runs with the
  in-memory datastore (instead of e.g. PostgeSQL) and the Playground UI enabled.

## Setup

```bash
# Create the cluster but don't clobber your default kubeconfig
k3d cluster create openfga-hello \
  --servers 1 \
  --agents 1 \
  --k3s-arg "--disable=traefik@server:0" \
  --kubeconfig-update-default=false

# Persist kubeconfig to a stable location and export it (adjust the path if desired)
k3d kubeconfig write openfga-hello --output ./openfga-hello.kubeconfig

set -x KUBECONFIG openfga-hello.kubeconfig  # fish
# export KUBECONFIG=./openfga-hello.kubeconfig

# Smoke test
kubectl cluster-info
kubectl get nodes -o wide

# If you stop working on the cluster or want troubleshoot, consider:
# `k3d cluster {start|stop|delete} openfga-hello`

kubectl create namespace openfga-hello

# Setup the Helm repo; only needs to run once per machine.
helm repo add openfga https://openfga.github.io/helm-charts
helm repo update  # helm's pendant to `apt update`

## Install OpenFGA with the local values

# `helm upgrade --install ...`: if a release named `openfga` already exists, upgrade it; otherwise
# install it
# `openfga` is the release name, so all Kubernetes objects will be labeled/derived from that.
# `openfga/openfga` points to the chart (openfga chart inside the `openfga` repo you added earlier).
# `--namespace openfga-hello`: place everything into the `openfga-hello` namespace; will be created
# automatically if it doesn't exist
# `--values openfga.yaml` feeds in the local override file
helm upgrade --install openfga openfga/openfga \
  --namespace openfga-hello \
  --values openfga.yaml

# Watch everything come up
kubectl -n openfga-hello get pods -w

# The release produces a single Service called `openfga` with ports `8080 (HTTP API)`, `8081 (gRPC)`
# and `3000 (Playground)`. Everything runs under the `openfga-hello` namespace.

# Forward both the HTTP API and the Playground UI to access the API and Playground locally
kubectl -n openfga-hello port-forward svc/openfga 8080:8080 3000:3000  # fucking blocks the CLI

curl -X GET 0.0.0.0:8080/healthz  # should work
curl -X GET 0.0.0.0:8080/stores  # should work
```

- Visit http://localhost:3000/playground to reach the Playground UI
  - heads up: apparently, my `idontcareboutcookies` extension fucks this up in Chrome

## Inspecting and Troubleshooting

```bash
kubectl -n openfga-hello get svc
kubectl -n openfga-hello logs deploy/openfga

# `kubectl -n openfga-hello describe pod -l app.kubernetes.io/name=openfga` shows a detailed status
# dump for every pod in `openfga-hello` whose label `app.kubernetes.io/name` equals "openfga"
# The Helm chart sets that label on all its pods.
# `describe` includes events, container restarts, probe results, and mounted volumes,
# so it’s handy for debugging when pods aren’t healthy.
kubectl -n openfga-hello describe pod -l app.kubernetes.io/name=openfga
```

## Cleanup / Teardown

```bash
# Delete the Helm release named "openfga" from the `openfga-hello` namespace. Helm removes every
# Kubernetes object that release created (Deployment, Service, ServiceAccount, etc.) but leaves the
# namespace itself intact. This is how you cleanly tear down the OpenFGA install before recreating
# it or deleting the cluster.
# Apparently good hygiene to run before deleting the cluster
helm -n openfga-hello uninstall openfga

kubectl delete namespace openfga-hello
k3d cluster delete openfga-hello
```
