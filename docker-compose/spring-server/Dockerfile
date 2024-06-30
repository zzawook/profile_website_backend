# Use an official Java runtime as the base image
FROM ubuntu:latest

RUN apt-get update && apt-get install -y git openjdk-17-jdk

# Set the working directory in the container
WORKDIR /app

# Clone the GitHub repository
RUN git clone https://github.com/zzawook/profile_website_backend.git .

RUN ls

WORKDIR /app/profile-website

RUN chmod +x mvnw

# Build the Spring Boot project
RUN ./mvnw clean package -Dmaven.test.skip

# Expose the port on which the Spring Boot application will run
EXPOSE 8080

RUN pwd

RUN chmod +x target/profile-website-*.jar

RUN ls target

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/profile-website/target/profile-website-0.0.1-SNAPSHOT.jar"]