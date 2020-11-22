## Start services

```shell script
kubectl create namespace api-gateway
kubectl create namespace app
kubectl create namespace monitoring
# minikube addons enable ingress
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo add stable https://charts.helm.sh/stable
helm repo update

helm install prom prometheus-community/kube-prometheus-stack -f prometheus/prometheus.yaml --atomic --namespace=monitoring
helm install keycloak --values keycloak/values.yaml exxbrain/arch-keycloak -n=api-gateway
helm install krakend krakend/krakend-chart -n=api-gateway
```


```shell script
helm repo add codecentric https://codecentric.github.io/helm-charts
helm repo add stable https://kubernetes-charts.storage.googleapis.com
helm upgrade keycloak --values keycloak/values.yaml codecentric/keycloak -n=api-gateway
kubectl config set-context --current --namespace=api-gateway
kubectl port-forward service/keycloak-postgresql 5433:5432
kubectl port-forward pod/keycloak-0 8080
```