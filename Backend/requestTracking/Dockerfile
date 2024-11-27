FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} requestTracking-microservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/requestTracking-microservice-0.0.1-SNAPSHOT.jar"]