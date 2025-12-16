# -------- Stage 1: Build with Gradle and AWS CLI --------
FROM gradle:8.10.2-jdk17-alpine AS build

# Create working directory
WORKDIR /app

# Copy all project files
COPY . .

# Accept optional build-time args
ARG AUTH_TOKEN

# Pass them to Gradle via ENV
ENV AUTH_TOKEN=$AUTH_TOKEN

# Build project (skip tests)
RUN gradle clean build -x test -x integrationTest

# -------- Stage 2: Lightweight runtime --------
FROM openjdk:17-ea-jdk-alpine3.14

WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/build/libs/account-service.jar /app/account-service.jar

# Pass spring profile via ENV
ENV SPRING_PROFILES_ACTIVE=docker

# Expose the app port
EXPOSE 8080

# Run the app
CMD ["sh", "-c", "java -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar account-service.jar"]