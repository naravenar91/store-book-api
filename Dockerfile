# Usar imagen oficial de Maven para build
FROM maven:3.9.0-eclipse-temurin-17 AS build

# Directorio de trabajo
WORKDIR /app

# Copiar pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el resto del proyecto y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# ----------------------
# Imagen final solo con Java
# ----------------------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiar el jar generado
COPY --from=build /app/target/*.jar app.jar

# Puerto que expone la app
EXPOSE 8080

# Comando para correr la app
ENTRYPOINT ["java","-jar","app.jar"]