#FROM ubuntu:latest
#LABEL authors="Dell Latitude 5440"
#
#ENTRYPOINT ["top", "-b"]

# Use an official OpenJDK 17 image as a parent image
FROM openjdk:17-oracle

# Set the working directory in the container
WORKDIR /app

# Copy any jar file from the target directory into the container at /app
# Here, the jar is renamed to UtilisateursService.jar for clarity
COPY target/*.jar UsersMicroService.jar

# Make port 8080 available to the world outside this container
EXPOSE 5050

# Run the jar file, specifying the renamed jar
ENTRYPOINT ["java", "-jar", "UsersMicroService.jar"]