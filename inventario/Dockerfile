# Dockerfile para proyecto con Lombok
FROM openjdk:17-jdk-slim

WORKDIR /app

# Instalar Maven y herramientas necesarias
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Verificar versión de Maven
RUN mvn -version

# Copiar pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fuente
COPY src ./src

# Construir aplicación (Lombok se procesará automáticamente)
RUN mvn clean package -DskipTests -Dmaven.compiler.showWarnings=false

EXPOSE 8080

# Variables de entorno
ENV JAVA_OPTS="-Xmx512m -Xms256m"

CMD ["sh", "-c", "java $JAVA_OPTS -jar target/*.jar --spring.profiles.active=prod"]
