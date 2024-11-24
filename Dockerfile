# Базовый образ OpenJDK 17
FROM openjdk:17-jdk-slim

# Установка необходимых инструментов
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*

# Рабочая директория внутри контейнера
WORKDIR /app

# Копируем собранный JAR файл
COPY target/TaskManagementSystem-0.0.1-SNAPSHOT.jar app.jar

# Копируем скрипт запуска и делаем его исполняемым
COPY docker-entrypoint.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/docker-entrypoint.sh

# Открываем порт 8080 для приложения
EXPOSE 8080

# Указываем ENTRYPOINT как скрипт
ENTRYPOINT ["docker-entrypoint.sh"]

# Команда по умолчанию для запуска приложения
CMD ["java", "-jar", "app.jar"]
