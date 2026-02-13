#!/bin/bash
# Convenience automation to set up the cloud cluster for presentation purposes.

clear

# prerequisite: get openfga helmchart
helm repo add openfga https://openfga.github.io/helm-charts
helm repo update  # helm's counterpart to `apt update`

# setup cloud cluster
context='cloud'
k3d cluster create "$context" --config "k3d-$context.yaml" --kubeconfig-update-default=false
k3d kubeconfig write "$context" --output ./"$context.kubeconfig.yaml"

export KUBECONFIG=./"$context.kubeconfig.yaml"

kubectl config use-context "k3d-$context"
kubectl create namespace "ns-$context"
helm upgrade -i openfga openfga/openfga -n "ns-$context" -f openfga.helmchart.yaml

cloud_url='http://0.0.0.0:8080'

# wait for OpenFGA to be ready
until curl "$cloud_url/healthz" 2>/dev/null; do
    echo waiting for cloud ofga...
    sleep 3
done

# create a store
fga_store_id="$(fga store create --api-url "$cloud_url" --name mystore | jq -r .store.id)"
fga store list --api-url "$cloud_url"
fga store get --api-url "$cloud_url" --store-id "$fga_store_id"

# import model + tuples from store.fga.yaml
# caution: import is _purely_ additive; tuples or model definitions won't get deleted, only extended
fga store import --api-url "$cloud_url" --store-id "$fga_store_id" --file store.fga.yaml
fga tuple read --api-url "$cloud_url" --store-id "$fga_store_id"

# install Persistent Volume Claim (PVC) and Cronjob
docker build -t export-fga:latest export-cronjob/
k3d image import -c cloud export-fga:latest
kubectl -n "ns-$context" apply -f export-cronjob/store-dump-server.yaml
kubectl -n "ns-$context" apply -f export-cronjob/export-cronjob.yaml

k9s --kubeconfig ./"$context.kubeconfig.yaml" -n "ns-$context"
