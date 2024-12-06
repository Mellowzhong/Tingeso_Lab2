<!-- Backend -->
cd Backend

cd backend-config
docker build -t mellow03/backend-config .
docker push mellow03/backend-config
cd ..

cd backend-eureka
docker build -t mellow03/backend-eureka .
docker push mellow03/backend-eureka
cd ..

cd backend-gateway
docker build -t mellow03/backend-gateway .
docker push mellow03/backend-gateway:latest
cd ..

cd user-microservice
docker build -t mellow03/user-microservice:latest .
docker push mellow03/user-microservice:latest
cd ..

cd financialEvaluation-microservice
docker build -t mellow03/financial-evaluation-microservice:latest .
docker push mellow03/financial-evaluation-microservice:latest
cd ..

cd requestTracking-microservice
docker build -t mellow03/request-tracking-microservice:latest .
docker push mellow03/request-tracking-microservice:latest
cd ..

cd credit-microservice
docker build -t mellow03/credit-microservice:latest .
docker push mellow03/credit-microservice:latest
cd ..

cd document-microservice
docker build -t mellow03/document-microservice:latest .
docker push mellow03/document-microservice:latest
cd ..

cd utils-microservice
docker build -t mellow03/utils-microservice:latest .
docker push mellow03/utils-microservice:latest
cd ..

<!-- Frontend -->
cd Frontend
docker buildx build --platform linux/amd64,linux/arm64 -t mellow03/frontend:latest --push .
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
kubectl delete -f ms-document-deploy.yaml
kubectl delete -f ms-financial-evaluation-deploy.yaml
kubectl delete -f ms-credit-deploy.yaml
kubectl delete -f ms-utils-deploy.yaml
kubectl delete -f ms-request-tracking-deploy.yaml
kubectl delete -f frontend-deployment-service.yaml

<!-- Deployment -->
<!-- DB init-->
kubectl apply -f postgres-config-map.yaml
kubectl apply -f postgres-secrets.yaml
kubectl apply -f postgres-dp-sv-pvc.yaml

<!-- Crear las bases de datos en el pod -->
kubectl get pods
kubectl exec -it postgres-67dbbf56f4-8r4q2 -- psql -U postgres

<!-- Crear las bases de datos -->
CREATE DATABASE dbuser;
CREATE DATABASE dbfinancialevaluation;
CREATE DATABASE dbdocument;
CREATE DATABASE dbcredit;

<!-- Listar las bases de datos -->
\l

<!-- Deploy microservices -->
kubectl apply -f backend-config-deployment-service.yaml
kubectl apply -f backend-eureka-deployment-service.yaml
kubectl apply -f backend-gateway-deployment-service.yaml
kubectl apply -f ms-user-deploy.yaml
kubectl apply -f ms-document-deploy.yaml
kubectl apply -f ms-financial-evaluation-deploy.yaml
kubectl apply -f ms-credit-deploy.yaml
kubectl apply -f ms-utils-deploy.yaml
kubectl apply -f ms-request-tracking-deploy.yaml
kubectl apply -f frontend-deployment-service.yaml

<!-- Ultimas configuraciones -->
kubectl get pods

kubectl port-forward <nombre-pod> <puerto-local>:<puerto-contenedor>
kubectl port-forward backend-gateway-deployment-866d6d9f95-78qw5 8080:8080

minikube service frontend
minikube tunnel