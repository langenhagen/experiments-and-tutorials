#!/bin/bash
# Convenience automation to set up the OS cluster for presentation purposes.

clear

# setup OS cluster
context='os'
k3d cluster create "$context" --config "k3d-$context.yaml" --kubeconfig-update-default=false
k3d kubeconfig write "$context" --output ./"$context.kubeconfig.yaml"

export KUBECONFIG=./"$context.kubeconfig.yaml"

kubectl config use-context "k3d-$context"
kubectl create namespace "ns-$context"
helm upgrade -i openfga openfga/openfga -n "ns-$context" -f openfga.helmchart.yaml

# # wait for OpenFGA to be ready
# os_url='http://0.0.0.0:9090'
# until curl "$os_url/healthz" 2>/dev/null; do
#     echo waiting for OS ofga...
#     sleep 3
# done

# build & make the OS CronJob Docker image available inside the cluster
# then apply the import-cronjob.yaml
# Note: the CronJob expects the cloud dump file at http://host.k3d.internal:8082/store-dump.fga.yaml
docker build -t import-fga:latest import-cronjob/
k3d image import -c "$context" import-fga:latest
kubectl -n "ns-$context" apply -f import-cronjob/import-cronjob.yaml

k9s --kubeconfig ./"$context.kubeconfig.yaml" -n "ns-$context"
