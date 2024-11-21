FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} utils-microservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/utils-microservice-0.0.1-SNAPSHOT.jar"]