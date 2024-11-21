<!-- Backend -->
cd .\Backend\

cd .\backend-config\
mvn clean install
docker build -t mellow03/backend-config .
docker push mellow03/backend-config
cd ..

cd .\backend-eureka\
mvn clean install
docker build -t mellow03/backend-eureka .
docker push mellow03/backend-eureka
cd ..

cd .\backend-gateway\
mvn clean install
docker build -t mellow03/backend-gateway .
docker push mellow03/backend-gateway:latest
cd ..

cd .\user-microservice\
mvn clean install
docker build -t mellow03/user-microservice:latest .
docker push mellow03/user-microservice:latest
cd ..

cd .\document-microservice\
mvn clean install
docker build -t mellow03/document-microservice:latest .
docker push mellow03/document-microservice:latest
cd ..

cd .\financialEvaluation-microservice\
mvn clean install
docker build -t mellow03/financial-evaluation-microservice:latest .
docker push mellow03/financial-evaluation-microservice:latest
cd ..

cd .\credit-microservice\
mvn clean install
docker build -t mellow03/credit-microservice:latest .
docker push mellow03/credit-microservice:latest
cd ..

cd .\document-microservice\
mvn clean install
docker build -t mellow03/document-microservice:latest .
docker push mellow03/document-microservice:latest
cd ..

cd .\utils-microservice\
mvn clean install
docker build -t mellow03/utils-microservice:latest .
docker push mellow03/utils-microservice:latest
cd ..

<!-- Frontend -->
cd .\Frontend\
docker build -t mellow03/frontend:latest .
docker push mellow03/frontend:latest
cd ..


<!-- Kubernetes -->
<!-- DB -->
kubectl delete -f .\postgres-config-map.yaml
kubectl delete -f .\postgres-secrets.yaml 
kubectl delete -f .\postgres-dp-sv-pvc.yaml

kubectl delete -f .\backend-config-deployment-service.yaml
kubectl delete -f .\backend-eureka-deployment-service.yaml
kubectl delete -f .\backend-gateway-deployment-service.yaml
kubectl delete -f .\ms-user-deploy.yaml
kubectl delete -f .\ms-document-deploy.yml
kubectl delete -f .\ms-financial-evaluation-deploy.yml
kubectl delete -f .\ms-credit-deploy.yml
kubectl delete -f .\ms-document-deploy.yml
kubectl delete -f .\ms-utils-deploy.yml

<!-- Deployment -->
<!-- DB -->
kubectl apply -f .\postgres-config-map.yaml
kubectl apply -f .\postgres-secrets.yaml
kubectl apply -f postgres-dp-sv-pvc.yaml

kubectl apply -f .\backend-config-deployment-service.yaml
kubectl apply -f .\backend-eureka-deployment-service.yaml
kubectl apply -f .\backend-gateway-deployment-service.yaml
kubectl apply -f .\ms-user-deploy.yaml
kubectl apply -f .\ms-document-deploy.yml
kubectl apply -f .\ms-financial-evaluation-deploy.yml
kubectl apply -f .\ms-credit-deploy.yml
kubectl apply -f .\ms-document-deploy.yml
kubectl apply -f .\ms-utils-deploy.yml
