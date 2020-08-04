#!/bin/bash
# Download all repos from gitlab.
#
# author: andreasl

get_gitlab_repo_urls() {
    # Get the URLS of all visible gitlab repos.
    private_token='YyVD3x3ta59_srrRyLGm'
    url='https://gitlab.celeraone.com/api/v4/projects'
    page_size=50
    page_nr=1
    while :; do
        querystring="private_token=${private_token}&per_page=${page_size}&page=${page_nr}"
        new_project_urls="$(curl -s "${url}?${querystring}" | jq '.[]."ssh_url_to_repo"')"
        printf "${new_project_urls}\n"
        (( page_nr++ ))
        [ "$(wc -l <<< "$new_project_urls")" -lt "$page_size" ] && break
    done
}

urls_str="$(get_gitlab_repo_urls)"
mapfile -t urls <<< "$urls_str"
for quoted_url in "${urls[@]}"; do
    tmp="${quoted_url%\"}"
    url="${tmp#\"}"
    tmp="${url#*:}"
    folder="${tmp%%/*}"
    mkdir -p "$folder"

    pushd "$folder" || exit 1
    git clone "$url"
    popd || exit 1
done
