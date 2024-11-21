FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} document-microservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/document-microservice-0.0.1-SNAPSHOT.jar"]