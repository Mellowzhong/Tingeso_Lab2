# Etapa de construcción
FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/app
COPY pom.xml ./
# Descargar dependencias antes de copiar el código fuente completo para aprovechar el caché de Docker
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-slim
WORKDIR /home/app

# Copiar el archivo JAR generado desde la etapa de construcción
COPY --from=build /home/app/target/gateway-0.0.1-SNAPSHOT.jar gateway.jar

ENTRYPOINT ["java", "-jar", "gateway.jar"]