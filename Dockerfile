# Sử dụng image OpenJDK chính thức từ DockerHub
FROM openjdk:21-jdk-slim

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Copy file .jar từ thư mục target vào container
COPY target/backend_project-0.0.1-SNAPSHOT.jar app.jar

# Expose cổng 8080 (cổng mặc định của Spring Boot)
EXPOSE 8080

# Lệnh khởi chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
