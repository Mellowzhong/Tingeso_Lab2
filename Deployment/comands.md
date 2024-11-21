<!-- Backend -->
cd .\Backend\

cd .\config\
mvn clean install
docker build -t mellow03/config-microservice:latest .
docker push mellow03/config-microservice:latest
cd ..

cd .\eureka\
mvn clean install
docker build -t mellow03/eureka-microservice:latest .
docker push mellow03/eureka-microservice:latest
cd ..

cd .\gateway\
mvn clean install
docker build -t mellow03/gateway-microservice:latest .
docker push mellow03/gateway-microservice:latest
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
kubectl delete -f .\postgres-config-map.yml
kubectl delete -f .\postgres-secrets.yml 
kubectl delete -f .\postgres.yml

kubectl delete -f .\config-microservice-deploy.yml
kubectl delete -f .\eureka-microservice-deploy.yml
kubectl delete -f .\gateway-microservice-deploy.yml
kubectl delete -f .\ms-user-deploy.yml
kubectl delete -f .\ms-document-deploy.yml
kubectl delete -f .\ms-financial-evaluation-deploy.yml
kubectl delete -f .\ms-credit-deploy.yml
kubectl delete -f .\ms-document-deploy.yml
kubectl delete -f .\ms-utils-deploy.yml

<!-- Deployment -->
<!-- DB -->
kubectl apply -f .\postgres-config-map.yml
kubectl apply -f .\postgres-secrets.yml
kubectl apply -f .\postgres.yml

kubectl apply -f .\config-microservice-deploy.yml
kubectl apply -f .\eureka-microservice-deploy.yml
kubectl apply -f .\gateway-microservice-deploy.yml
kubectl apply -f .\ms-user-deploy.yml
kubectl apply -f .\ms-document-deploy.yml
kubectl apply -f .\ms-financial-evaluation-deploy.yml
kubectl apply -f .\ms-credit-deploy.yml
kubectl apply -f .\ms-document-deploy.yml
kubectl apply -f .\ms-utils-deploy.yml
