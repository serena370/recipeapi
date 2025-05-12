# Use an official Maven image to build the app
FROM maven:3.8.4-jdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and the source code into the container
COPY pom.xml .
COPY src ./src

# Build the app using Maven
RUN mvn clean install

# Use an official OpenJDK image to run the app
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the build image
COPY --from=build /app/target/recipeapi-0.0.1-SNAPSHOT.jar /app/recipeapi.jar

# Expose the port on which your app will run
EXPOSE 8080

# Run the JAR file when the container starts
ENTRYPOINT ["java", "-Dserver.port=8080", "-jar", "recipeapi.jar"]
