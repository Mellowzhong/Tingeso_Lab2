FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} credit-microservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/credit-microservice-0.0.1-SNAPSHOT.jar"]