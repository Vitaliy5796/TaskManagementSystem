# Стадия сборки с Maven
FROM maven:3.8.4-openjdk-17 as builder

WORKDIR /app

COPY . .

RUN mvn clean package

# Основной образ
FROM openjdk:17-jdk-slim

# Установка необходимых инструментов
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Копируем JAR
COPY --from=builder /app/target/TaskManagementSystem-0.0.1-SNAPSHOT.jar app.jar

# Копируем скрипт запуска
COPY docker-entrypoint.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/docker-entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["docker-entrypoint.sh"]

# Запуска приложения
CMD ["java", "-jar", "app.jar"]