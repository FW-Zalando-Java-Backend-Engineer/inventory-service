# === Stage 1: Build the application ===
FROM maven:3.9.4-eclipse-temurin-24 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# === Stage 2: Run the application ===
FROM eclipse-temurin:24-jdk-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8587
ENTRYPOINT ["java", "-jar", "app.jar"]
