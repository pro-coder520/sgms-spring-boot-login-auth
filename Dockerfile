# Use the official OpenJDK 17 image
FROM eclipse-temurin:17-jdk-jammy

# Copy the JAR file into the container
COPY target/auth-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]