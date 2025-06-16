# Use official OpenJDK 17 image with slim variant for smaller size
FROM eclipse-temurin:17-jdk-jammy

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=prod \
    TZ=UTC \
    JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Create a non-root user and switch to it
RUN useradd -m myappuser && \
    mkdir -p /app && \
    chown myappuser:myappuser /app
USER myappuser

# Set the working directory
WORKDIR /app

# Copy the JAR file (renamed for simplicity)
COPY --chown=myappuser:myappuser target/RazorPay-0.0.1-SNAPSHOT.jar app.jar

# Expose port (documentation only - actual publishing happens at runtime)
EXPOSE 8080

# Use shell form for better signal handling
ENTRYINTY exec java $JAVA_OPTS -jar app.jar
