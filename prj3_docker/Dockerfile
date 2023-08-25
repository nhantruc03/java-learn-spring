# syntax=docker/dockerfile:1
FROM maven:3.8.1-openjdk-17-slim

WORKDIR /app
COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src

CMD ["mvn", "spring-boot:run"]