# Stage 1: Build Stage (Uses a Maven image with Java 17 to compile the code)
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml .
COPY src ./src

# Compile and package the code inside the container, skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Runtime Stage (Uses a lightweight Java 17 runtime to run the app)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy ONLY the compiled .jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]