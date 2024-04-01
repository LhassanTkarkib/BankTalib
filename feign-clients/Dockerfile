FROM maven:3.8.1-openjdk-17-slim

# Copy the application's .jar file to the container
COPY target/*.jar feign-clients.jar

# Expose the port the application runs on
EXPOSE 9094

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "feign-clients.jar"]