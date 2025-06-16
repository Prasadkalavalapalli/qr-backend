# Use a minimal base image with OpenJDK 17
FROM openjdk:17-jdk-slim

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=default \
    TZ=Asia/Kolkata

# Set the working directory inside the container
WORKDIR /app

# Copy the exact JAR file
COPY target/RazorPay-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Command to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
