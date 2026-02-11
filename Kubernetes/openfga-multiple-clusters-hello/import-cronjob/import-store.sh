#!/bin/sh
# Import an openFGA store from a dump file.
set -eux

echo 'Henlo from the import-fga-store script!'
fga version

store_name="${STORE_NAME:-mystore}"
dump_file="${DUMP_FILE:-store-dump.fga.yaml}"
dump_url="${STORE_DUMP_URL:-http://host.k3d.internal:8082/store-dump.fga.yaml}"

# download the cloud source dump file
curl -fsSL "$dump_url" -o "$dump_file"

# create store on OS level
os_api_url="${OS_API_URL:-http://openfga:8080}"
os_store_id="$(fga store create --api-url "$os_api_url" --name "$store_name" | jq -r '.store.id')"
fga store get --api-url "$os_api_url" --store-id "$os_store_id" || {
	>&2 echo "Error: Getting OS fga store ${os_store_id} failed!"
	exit 1
}

# import model + tuples from file
fga store import --api-url "$os_api_url" --store-id "$os_store_id" --file "$dump_file" || {
	>&2 echo "Error: Importing from ${dump_file} failed!"
	exit 2
}
rm "$dump_file"

# delete all stores that are not the new store
fga store list --api-url "$os_api_url" |
	jq -r '.stores[].id' |
	grep -v "^${os_store_id}$" |
	while read -r store_id; do
		fga store delete --api-url "$os_api_url" --store-id "$store_id" --force
	done

echo 'All Done.'
