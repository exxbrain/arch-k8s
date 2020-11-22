```shell script
kubectl create namespace ksql
kubectl config set-context --current --namespace=ksql

helm repo add confluentinc https://confluentinc.github.io/cp-helm-charts/
helm repo update

helm install cp confluentinc/cp-helm-charts -f cp_values.yaml
```