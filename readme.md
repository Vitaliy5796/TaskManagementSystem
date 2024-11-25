# Task Management System

Этот проект представляет собой систему управления задачами с использованием Spring Boot и MySQL. В этом README описано,
как запустить приложение с помощью Docker и Docker Compose.

## Требования

- Docker
- Docker Compose

## Структура проекта

- **Spring Boot** для бизнес-логики и REST API.
- **MySQL** для хранения данных.
- **Flyway** для управления миграциями базы данных.

## Запуск приложения

Для запуска приложения в контейнерах Docker с помощью `docker-compose`, выполните следующие шаги:

### 1. Клонируйте репозиторий

```bash
git clone https://github.com/Vitaliy5796/TaskManagementSystem.git
cd <папка проекта>
```

### 2. Запуск контейнеров с помощью Docker Compose

```bash
docker-compose up --build
```

#### Эта команда:

Соберет образ приложения, если он еще не был собран. \
Запустит контейнеры для приложения Spring Boot и базы данных MySQL

### 3. Ожидайте завершения сборки

Docker Compose автоматически скачает необходимые образы и создаст контейнеры для вашего приложения и базы данных. Если
все настроено правильно, вывод должен быть похож на следующий:

```bash
Building app
Step 1/7 : FROM maven:3.8.4-openjdk-17 as builder


Creating network "taskmanagementsystem_default" with the default driver
Creating volume "taskmanagementsystem_mysql-data" with default driver
Creating mysql-db ... done
Creating spring-boot-app ... done
```

### 4. Доступ к приложению

После запуска контейнеров приложение будет доступно по адресу:

http://localhost:8080

### 5. Доступ к базе данных MySQL

MySQL будет доступен по следующему адресу:

Хост: localhost \
Порт: 3306 \
Пользователь: root \
Пароль: root \
Имя базы данных: task_managements

### 6. Управление миграциями с Flyway

Flyway автоматически выполнит миграции при запуске приложения, если они есть в папке src/main/resources/db/migration.

### 7. Аутентификация и получение токена

    В качестве пользователей для получения токена использовать:
        **POST** `/api/auth/token` 

```json
      {
        "login" : "user@mail.ru",
        "password" : "user"
      }
```
```json
      {
        "login" : "admin@mail.ru",
        "password" : "admin"
      } 
```

### 8. Остановка контейнеров

Для остановки и удаления контейнеров выполните следующую команду:

```bash

docker-compose down
Это остановит все контейнеры, а также удалит сети и тома, созданные Docker Compose.
```

