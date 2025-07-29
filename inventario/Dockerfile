# Usar OpenJDK 17 como imagen base
FROM openjdk:17-jdk-slim

# Establecer directorio de trabajo
WORKDIR /app

# Instalar Maven (por si no tienes mvnw)
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Copiar archivos de configuración de Maven
COPY pom.xml .

# Copiar Maven Wrapper si existe
COPY mvnw* ./
COPY .mvn .mvn 2>/dev/null || echo "No .mvn directory found, using system maven"

# Dar permisos de ejecución si mvnw existe
RUN if [ -f "./mvnw" ]; then chmod +x ./mvnw; fi

# Descargar dependencias (usar mvnw si existe, sino maven)
RUN if [ -f "./mvnw" ]; then ./mvnw dependency:go-offline -B; else mvn dependency:go-offline -B; fi

# Copiar código fuente
COPY src ./src

# Construir la aplicación (usar mvnw si existe, sino maven)
RUN if [ -f "./mvnw" ]; then ./mvnw clean package -DskipTests; else mvn clean package -DskipTests; fi

# Exponer puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/*.jar", "--spring.profiles.active=prod"]
