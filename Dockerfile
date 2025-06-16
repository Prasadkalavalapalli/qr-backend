# Use a minimal base image with OpenJDK 17
FROM openjdk:17-jdk-slim

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=default \
    TZ=Asia/Kolkata

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file
COPY target/RazorPay-0.0.1-SNAPSHOT.jar app.jar

# Expose the port Spring Boot uses
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"] 