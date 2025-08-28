FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy file jar từ máy host vào container
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
