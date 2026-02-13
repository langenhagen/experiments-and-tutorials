#!/bin/bash
# Convenience automation to play around with the OpenFGA inter-cluster sync experiment.

clear

cloud_url='http://0.0.0.0:8080'
os_url='http://0.0.0.0:9090'

cloud_store_id="$(fga store list --api-url "$cloud_url" | jq -r '.stores[0].id')"

read -r -p $'Press [ENTER] to read the cloud tuples'
fga tuple read --api-url "$cloud_url" --store-id "$cloud_store_id"

read -r -p $'Press [ENTER] to write a tuple to cloud'
set -x
fga tuple write \
    --api-url "$cloud_url" \
    --store-id "$cloud_store_id" \
    user:bello developer organization:wuff
set +x

read -r -p $'Wait a minute, then press [ENTER] to curl the cloud store dump'
set -x
curl http://0.0.0.0:8082/store-dump.fga.yaml
set +x

read -r -p $'Press [ENTER] to write a tuple to OS'
os_store_id="$(fga store list --api-url "$os_url" | jq -r '.stores[0].id')"
set -x
fga tuple write --api-url "$os_url" --store-id "$os_store_id" user:cat developer organization:meoowwww

fga tuple read --api-url "$os_url" --store-id "$os_store_id"
set +x

read -r -p $'Wait 1 minute, then press [ENTER] to read the OS store tuples\nThe new cloud tuple should be there\nThe OS tuple should be gone'
os_store_id="$(fga store list --api-url "$os_url" | jq -r '.stores[0].id')"
set -x
fga tuple read --api-url "$os_url" --store-id "$os_store_id"
set +x

read -r -p $'Press [ENTER] to delete / change tuples on cloud'
set -x
fga tuple delete --api-url "$cloud_url" --store-id "$cloud_store_id" user:bello developer organization:wuff
fga tuple delete --api-url "$cloud_url" --store-id "$cloud_store_id" user:bob developer organization:myorg
fga tuple delete --api-url "$cloud_url" --store-id "$cloud_store_id" user:charlie operator organization:myorg
fga tuple write --api-url "$cloud_url" --store-id "$cloud_store_id" user:bob admin organization:myorg
set +x

read -r -p $'Wait a minute, then press [ENTER] to curl the cloud store dump'
set -x
curl http://0.0.0.0:8082/store-dump.fga.yaml
set +x

read -r -p $'Wait another minute, then press [ENTER]; the deleted tuple should be gone from the OS store'
os_store_id="$(fga store list --api-url "$os_url" | jq -r '.stores[0].id')"
set -x
fga tuple read --api-url "$os_url" --store-id "$os_store_id"
set +x

printf '\nAll done!'
