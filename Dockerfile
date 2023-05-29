# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Run Maven to build the application
RUN ./mvnw package -DskipTests

# Set the default command to run when the container starts
CMD ["java", "-jar", "target/spring_security_demo.jar"]
