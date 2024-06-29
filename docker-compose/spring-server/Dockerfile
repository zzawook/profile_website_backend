# Use a base image with Java and Maven installed
FROM maven:3.8.4-openjdk-11-slim AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml file
COPY profile_website_backend/profile_website/pom.xml .

# Download the dependencies
RUN mvn dependency:go-offline -B

# Copy the source code
COPY profile_website_backend/profile_website/src ./src

# Build the application
RUN mvn package -DskipTests

# Use a lightweight base image with Java installed
FROM adoptopenjdk:11-jre-hotspot AS runtime

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/profile_website-*.jar app.jar

# Expose the port that the application listens on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]