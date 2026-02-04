#!/bin/sh
# Import an openFGA store.
set -eux

echo 'Henlo from the import-fga-store script!'
fga version

store_name="${STORE_NAME:-mystore}"
dump_file="${DUMP_FILE:-cloud-dump.fga.yaml}"

cloud_api_url="${CLOUD_API_URL:-http://0.0.0.0:8080}"
cloud_store_id="$(fga store list --api-url "$cloud_api_url" | jq -r --arg name "$store_name" '.stores[] | select(.name == $name) | .id')"

# dump the cloud source store to file
fga store export --api-url "$cloud_api_url" --store-id "$cloud_store_id" --max-tuples 999999 >"$dump_file"

# create store on OS level
os_api_url="${OS_API_URL:-http://openfga:8080}"
os_store_id="$(fga store create --api-url "$os_api_url" --name "$store_name" | jq -r '.store.id')"
fga store get --api-url "$cloud_api_url" --store-id "$os_store_id" || {
    >&2 echo "Error: Getting OS fga store ${os_store_id} failed!"
    exit 1
}

# import model + tuples from file
fga store import --api-url "$os_api_url" --file "$dump_file" || {
    >&2 echo "Error: Importing from ${dump_file} failed!"
    exit 2
}

# delete all stores that are not the new store
fga store list --api-url "$os_api_url" |
    jq -r '.stores[].id' |
    grep -v "^${os_store_id}$" |
    while read -r os_store_id; do
        fga store delete --api-url "$os_api_url" --store-id "$os_store_id" --force
    done

echo 'All Done.'
