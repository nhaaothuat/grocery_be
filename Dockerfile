# Sử dụng JDK nhẹ
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc trong container
WORKDIR /app

# Copy JAR vào container
COPY target/*.jar app.jar

# Chạy app
ENTRYPOINT ["java", "-jar", "app.jar"]
