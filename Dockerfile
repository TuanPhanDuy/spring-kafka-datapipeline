# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the project’s build output (JAR file) into the container
COPY target/datapipeline-0.0.1-SNAPSHOT.jar /app/datapipeline-0.0.1-SNAPSHOT.jar

# Expose the port your Spring Boot application will run on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "datapipeline-0.0.1-SNAPSHOT.jar"]