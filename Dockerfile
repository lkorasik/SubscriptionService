# Используем официальный образ Maven для сборки
FROM maven:3.9.9-eclipse-temurin-17-focal AS build
WORKDIR /app

# Копируем исходный код и файлы pom.xml
COPY pom.xml .
COPY src ./src

# Собираем приложение
RUN mvn clean package -DskipTests

# Используем образ с Java для запуска
FROM openjdk:17-jdk-slim
WORKDIR /app

# Копируем собранный JAR из предыдущего этапа
COPY --from=build /app/target/SubscriptionService-0.0.1-SNAPSHOT.jar ./app.jar

# Указываем точку входа
ENTRYPOINT ["java", "-jar", "app.jar"]
