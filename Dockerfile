FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Directly copy the pre-compiled JAR created during the CodeBuild phase
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]