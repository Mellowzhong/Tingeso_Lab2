# Etapa de ejecución
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /home/app

# Copiar el archivo JAR desde el directorio target
COPY target/user-microservice-0.0.1-SNAPSHOT.jar backend.jar

# Exponer el puerto 8080
EXPOSE 8090

# Comando de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "backend.jar"]