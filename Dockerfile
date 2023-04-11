# Base image
FROM openjdk:17-jdk-alpine3.14

# Set the working directory inside the container
WORKDIR /app

# Copy the application files to the container
COPY . /app

# Build the application with Gradle
RUN ./gradlew build

# Expose the port that the app listens on (default is 8080)
EXPOSE 8080

# Run the app when the container starts
CMD ["java", "-jar", "/app/build/libs/demospring-0.0.1-SNAPSHOT.jar"]