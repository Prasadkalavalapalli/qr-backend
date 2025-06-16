# Build stage
FROM maven:3.8.6-eclipse-temurin-17 AS builder
WORKDIR /build
COPY . .
RUN mvn clean package

# Runtime stage
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=builder /build/target/RazorPay-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
