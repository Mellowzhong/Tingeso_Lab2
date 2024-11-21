cd .\config\
docker build -t mellow03/config-microservice:latest .
docker push mellow03/config-microservice:latest
cd ..

cd .\eureka\
docker build -t mellow03/eureka-microservice:latest .
docker push mellow03/eureka-microservice:latest
cd ..

cd .\gateway\
docker build -t mellow03/gateway-microservice:latest .
docker push mellow03/gateway-microservice:latest
cd ..

cd .\document-microservice\
docker build -t mellow03/document-microservice:latest .
docker push mellow03/document-microservice:latest
cd ..

cd .\financialEvaluation-microservice\
docker build -t mellow03/financialEvaluation-microservice:latest .
docker push mellow03/financialEvaluation-microservice:latest
cd ..

cd .\credit-microservice\
docker build -t mellow03/credit-microservice:latest .
docker push mellow03/credit-microservice:latest
cd ..

cd .\document-microservice\
docker build -t mellow03/document-microservice:latest .
docker push mellow03/document-microservice:latest
cd ..

cd .\user-microservice\
docker build -t mellow03/user-microservice:latest .
docker push mellow03/user-microservice:latest
cd ..