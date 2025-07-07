# Use official OpenJDK 21 JDK base image
FROM openjdk:21-jdk

WORKDIR /app

# Copy the built jar file into the container
COPY target/*.jar app.jar

EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
