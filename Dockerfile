# Use an official OpenJDK runtime as a parent image
FROM maven:3-jdk-11-slim

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# RUN apk add --no-cache maven

# Run Maven to build the application
RUN mvn package -DskipTests

# Set the default command to run when the container starts
CMD ["java", "-jar", "target/spring_security_demo.jar"]
