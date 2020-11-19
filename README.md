## Start services

```shell script
kubectl create namespace api-gateway
kubectl create namespace monitoring
kubectl create namespace app
# minikube addons enable ingress

helm repo add codecentric https://codecentric.github.io/helm-charts
kubectl create secret generic realm-secret --from-file=keycloak/realm.json -n=api-gateway
helm install keycloak --values keycloak/values.yaml codecentric/keycloak -n=api-gateway

helm install prom stable/prometheus-operator -f prometheus/prometheus.yaml --atomic --namespace=monitoring
helm install krakend krakend/krakend-chart -n=api-gateway
```


```shell script
helm upgrade keycloak --values keycloak/values.yaml codecentric/keycloak -n=api-gateway
kubectl config set-context --current --namespace=api-gateway
kubectl port-forward service/keycloak-postgresql 5433:5432
```