# Use OpenJDK 21 base image
FROM openjdk:21-jdk-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml /app
COPY src /app/src

# Install Maven and build the project
RUN apt-get update && apt-get install -y maven && \
    mvn clean package -DskipTests

# Use OpenJDK to run the application
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar from the build image
COPY --from=build /app/target/final-0.0.1-SNAPSHOT.jar /app/final-0.0.1-SNAPSHOT.jar

# Expose the port that the application will run on
EXPOSE 8080

# Command to run the jar file
ENTRYPOINT ["java", "-jar", "final-0.0.1-SNAPSHOT.jar"]
