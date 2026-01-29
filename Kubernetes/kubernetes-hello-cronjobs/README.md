# Kubernetes Hello Cronjobs

```bash
# Create a local Kubernetes cluster named "cronjobs-hello" inside Docker containers
# k3d cluster create lab : create + name the cluster "lab"
# --agents 2           : add 2 worker nodes (k3s "agents") in addition to the server node(s)
# --servers 1          : create 1 control-plane node (k3s "server"); this runs the API server, scheduler, etc.
# --k3s-arg ...        : pass raw arguments through to the underlying k3s process
#   "--disable=traefik": disables the bundled Traefik ingress controller that k3s would otherwise install
#   "@server:0"        : target selector; apply this arg to server node index 0 (the first/only server)
#  --kubeconfig-update-default=false:  don't overwrite default ~.kube/config
k3d cluster create cronjobs-hello \
  --agents 2 \
  --servers 1 \
  --k3s-arg "--disable=traefik@server:0" \
  --kubeconfig-update-default=false


# write da kubeconfig
k3d kubeconfig write cronjobs-hello --output ~/wandel/stuff/local-kubeconfig

set -x KUBECONFIG ~/wandel/stuff/local-kubeconfig  # fish syntax

# list ze clusters
k3d cluster list

# coarse info about the cluster
kubectl cluster-info

# List nodes and details
# kubectl get nodes    : list node objects registered with the cluster
# -o wide              : include extra columns (e.g., internal IP, OS image, kernel, container runtime)
kubectl get nodes -o wide

kubectl create namespace cronjobs-hello-ns

# Apply the CronJob from a version-controlled YAML file
# - -n cronjobs-hello-ns apply: apply into given namespace
# - -f hello-cron.yaml : read manifest from file
kubectl -n cronjobs-hello-ns apply -f cron-hello-01.yaml

# Watch the CronJob create Jobs and Pods in real time
kubectl -n cronjobs-hello-ns get cronjob --watch  # 1 cronjob
kubectl -n cronjobs-hello-ns get job --watch  # several jobs (retains successfulJobsHistoryLimit & failedJobsHistoryLimit from the yaml

# Pause the thing
kubectl -n cronjobs-hello-ns patch cronjob hello-cron -p '{"spec":{"suspend":true}}'
# Resume the thing
kubectl -n cronjobs-hello-ns patch cronjob hello-cron -p '{"spec":{"suspend":false}}'

# Pause and delete jobs (cronjob as a resource remains, but the jobs it spans vanish)
kubectl -n cronjobs-hello-ns patch cronjob hello-cron -p '{"spec":{"suspend":true}}'
kubectl -n cronjobs-hello-ns delete job --all

# Delete the Cronjob entirely
kubectl -n cronjobs-hello-ns delete cronjob hello-cron

# Delete the namespace
kubectl delete ns cronjobs-hello-ns

# Stop the cluster
k3d cluster stop cronjobs-hello

# Delete the cluster
k3d cluster delete cronjobs-hello

# Troubleshooting
# I had disk space issues with docker, had to `docker system prune --all --force`m then restarting the cluster solved the issue
docker system prune --all --force
k3d cluster stop cronjobs-hello
k3d cluster start cronjobs-hello

# aux commands
kubectl -n cronjobs-hello-ns get pods
kubectl -n cronjobs-hello-ns describe pod hello-cron-29495077-9kjzx
kubectl -n cronjobs-hello-ns get events --sort-by=.lastTimestamp | tail -n 30

kubectl -n cronjobs-hello-ns delete job hello-cron-29495077
```

- in `k9s` you can look for `:jobs`
