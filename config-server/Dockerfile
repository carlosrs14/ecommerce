FROM maven:3.9.7-eclipse-temurin-21-alpine AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ ./src/
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21.0.3_9-jre-alpine

WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY --from=builder /app/${JAR_FILE} config-server.jar

EXPOSE 8888

ENTRYPOINT ["java", "-Dspring.profiles.active=native", "-jar", "config-server.jar"]
