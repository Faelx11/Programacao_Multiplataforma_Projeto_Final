### Fase de build usando Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B clean package -DskipTests

### Imagem final
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/modulo1-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]