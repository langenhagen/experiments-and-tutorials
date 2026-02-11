#!/bin/sh
# Export an OpenFGA store to a shared file.
set -eux

echo 'Henlo from the export-fga-store script!'
fga version

store_name="${STORE_NAME:-mystore}"
dump_file="${DUMP_FILE:-/dump/store-dump.fga.yaml}"

cloud_api_url="${CLOUD_API_URL:-http://openfga:8080}"
cloud_store_id="$(fga store list --api-url "$cloud_api_url" | jq -r --arg name "$store_name" '.stores[] | select(.name == $name) | .id')"

if [ -z "$cloud_store_id" ] || [ "$cloud_store_id" = "null" ]; then
	>&2 echo "Error: No store named ${store_name} found in cloud OpenFGA"
	exit 1
fi

tmp_file="${dump_file}.tmp"
fga store export --api-url "$cloud_api_url" --store-id "$cloud_store_id" --max-tuples 999999 >"$tmp_file"
mv "$tmp_file" "$dump_file"

echo 'All Done.'
