## Prometheus

https://github.com/helm/charts/tree/master/stable/prometheus-operator

user/password: `admin/prom-operator`

```shell script
kubectl create namespace monitoring
kubectl config set-context --current --namespace=monitoring

helm repo add stable https://kubernetes-charts.storage.googleapis.com
helm repo update

helm install prom stable/prometheus-operator -f prometheus/prometheus.yaml --atomic --namespace=monitoring
helm uninstall prom

kubectl port-forward service/prom-grafana 9000:80
```

```shell script
kubectl get servicemonitors.monitoring.coreos.com
kubectl port-forward service/prom-prometheus-operator-prometheus 9090:9090
http://localhost:9000/service-discovery
```

```shell script
while true; do ab -n 50 -c 3 http://192.168.64.2:/users; sleep 3; done 
while true; do ab -n 50 -c 3 http://arch.homework/otusapp/dzakharov/users; sleep 3; done 
```

```shell script
minikube addons disable ingress
helm install nginx stable/nginx-ingress -f prometheus/nginx-ingress.yaml --atomic
```

RPS:
```
sum(rate(http_server_requests_seconds_count{uri="/users",status=~"2.+"}[2m]))
sum by (uri, method, status) (rate(http_server_requests_seconds_count[2m]))
histogram_quantile(0.95, sum by(le) (rate(http_server_requests_seconds_bucket[2m])))
```