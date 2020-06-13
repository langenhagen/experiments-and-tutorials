#!/bin/bash
# Showcase usage of the gitlabe api.
#
# See: ?per_page=50"
# - https://subscription.packtpub.com/book/application_development/9781783986842/6/ch06lvl1sec51/getting-your-private-token-for-the-api
# - https://gitlab.celeraone.com/profile/personal_access_tokens
# - https://docs.gitlab.com/ee/api/README.html#oauth2-tokens
# - https://forum.gitlab.com/t/api-get-all-my-repos-also-privates/11195

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
