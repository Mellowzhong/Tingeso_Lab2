<!-- Backend -->
cd Backend

cd backend-config
mvn clean install
docker buildx build --platform linux/amd64,linux/arm64 -t mellow03/backend-config:latest --push .
cd ..

cd backend-eureka
mvn clean install
docker buildx build --platform linux/amd64,linux/arm64 -t mellow03/backend-eureka --push .
cd ..

cd backend-gateway
mvn clean install
docker buildx build --platform linux/amd64,linux/arm64 -t mellow03/backend-gateway --push .
cd ..

cd user-microservice
mvn clean install
docker buildx build --platform linux/amd64,linux/arm64 -t mellow03/user-microservice:latest --push .
cd ..

cd financialEvaluation-microservice
mvn clean install
docker buildx build --platform linux/amd64,linux/arm64 -t mellow03/financial-evaluation-microservice:latest --push .
cd ..

cd credit-microservice
mvn clean install
docker buildx build --platform linux/amd64,linux/arm64 -t mellow03/credit-microservice:lates --push .
cd ..

cd document-microservice
mvn clean install
docker buildx build --platform linux/amd64,linux/arm64 -t mellow03/document-microservice:latest --push .
cd ..

cd utils-microservice
mvn clean install
docker buildx build --platform linux/amd64,linux/arm64 -t mellow03/utils-microservice:latest --push .
cd ..

cd requestTracking-microservice
mvn clean install
docker buildx build --platform linux/amd64,linux/arm64 -t mellow03/requestTracking-microservice:latest --push .
cd ..

<!-- Frontend -->
cd Frontend
docker build -t mellow03/frontend:latest .
docker push mellow03/frontend:latest
cd ..


<!-- Kubernetes -->
<!-- DB delete -->
kubectl delete -f postgres-config-map.yaml
kubectl delete -f postgres-secrets.yaml 
kubectl delete -f postgres-dp-sv-pvc.yaml

<!-- Deployment delete -->
kubectl delete -f backend-config-deployment-service.yaml
kubectl delete -f backend-eureka-deployment-service.yaml
kubectl delete -f backend-gateway-deployment-service.yaml
kubectl delete -f ms-user-deploy.yaml
kubectl delete -f ms-document-deploy.yml
kubectl delete -f ms-financial-evaluation-deploy.yml
kubectl delete -f ms-credit-deploy.yml
kubectl delete -f ms-document-deploy.yml
kubectl delete -f ms-utils-deploy.yml
kubectl delete -f ms-request-tracking-deploy.yml

<!-- Deployment -->
<!-- DB init-->
kubectl apply -f postgres-config-map.yaml
kubectl apply -f postgres-secrets.yaml
kubectl apply -f postgres-dp-sv-pvc.yaml

<!-- Crear las bases de datos -->
kubectl get pods
kubectl exec -it postgres-67dbbf56f4-psrf5 -- psql -U postgres -d postgres -c "CREATE DATABASE dbuser;"
kubectl exec -it postgres-67dbbf56f4-psrf5 -- psql -U postgres -d postgres -c "CREATE DATABASE dbfinancialevaluation;"
kubectl exec -it postgres-67dbbf56f4-psrf5 -- psql -U postgres -d postgres -c "CREATE DATABASE dbdocument;"
kubectl exec -it postgres-67dbbf56f4-psrf5 -- psql -U postgres -d postgres -c "CREATE DATABASE dbcredit;"

kubectl exec -it postgres-67dbbf56f4-psrf5 -- psql -U postgres

l

<!-- Deploy -->
kubectl apply -f backend-config-deployment-service.yaml
kubectl apply -f backend-eureka-deployment-service.yaml
kubectl apply -f backend-gateway-deployment-service.yaml
kubectl apply -f ms-user-deploy.yaml
kubectl apply -f ms-document-deploy.yml
kubectl apply -f ms-financial-evaluation-deploy.yaml
kubectl apply -f ms-credit-deploy.yml
kubectl apply -f ms-document-deploy.yml
kubectl apply -f ms-utils-deploy.yml
kubectl apply -f ms-request-tracking-deploy.yml