# Kubernetes
Graphs helping understand Kubernetes concepts and patterns.

## Relation between Service, Deployment, Nodes and Pods
```mermaid
graph TD;

subgraph Cluster
  subgraph Node1
    A[Pod A]
    B[Pod B]
  end

  subgraph Node2
    C[Pod C]
    E[Pod E]
  end

  D[Deployment]
  D -->|Manages| A
  D -->|Manages| B
  D -->|Manages| C
  D -->|Manages| E

  S[Service]
  S -->|Directs Traffic to| D

  D --->|Uses| CM[ConfigMap]
  D --->|Uses| SEC[Secret]
  D --->|Claims storage via| PVC[PVC\nPersistent Volume Claim]

  In[Ingress]
  In -->|Routes to| S

  SC[Storage Class\n\n]
  PVC -->|Requests| SC[StorageClass]

  PV[PV\nPersistent Volume]
  PVC -->|Binds| PV
  SC -->|Provisions| PV
end

I[Internet] -->|Exposes| In[Service]
```

---

**Ingresses** are not necessary. Alternatives:
- **NodePort**: Opens a port on each node to access the Service.
- **LoadBalancer**: Uses a cloud provider's load balancer to direct internet traffic to the Service.
- **Ingress**: Manages HTTP traffic and provides advanced routing capabilities for web applications.

**Storage Classes** don't necessarily need to be defined. A Storage Class in Kubernetes is a cluster-level resource that defines how and where persistent storage should be provisioned. They specify how to interact with physical storage, e.g. an SSD or a cloud provider's storage.
