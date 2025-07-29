# Dockerfile para proyecto con Lombok
FROM openjdk:17-jdk-slim

WORKDIR /app

# Instalar Maven y herramientas necesarias
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Configurar locale y encoding
ENV LC_ALL=C.UTF-8
ENV LANG=C.UTF-8
ENV MAVEN_OPTS="-Dfile.encoding=UTF-8"

# Copiar pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fuente
COPY src ./src

# Construir aplicación con configuración específica para properties
RUN mvn clean package -DskipTests \
    -Dfile.encoding=UTF-8 \
    -Dproject.build.sourceEncoding=UTF-8 \
    -Dmaven.resources.escapeString=\ \
    --batch-mode

EXPOSE 8080

ENV JAVA_OPTS="-Xmx512m -Xms256m -Dfile.encoding=UTF-8"

CMD ["sh", "-c", "java $JAVA_OPTS -jar target/*.jar --spring.profiles.active=prod"]
