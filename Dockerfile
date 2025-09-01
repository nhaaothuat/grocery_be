# Sử dụng JDK 17 (hoặc version bạn dùng)
FROM eclipse-temurin:17-jdk-alpine AS build

# Copy source code
WORKDIR /app
COPY . .

# Build ứng dụng bằng Maven
RUN ./mvnw -B clean package -DskipTests

# Stage chạy app
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy file jar từ stage build
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Lệnh chạy app
ENTRYPOINT ["java", "-jar", "app.jar"]
