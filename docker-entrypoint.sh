#!/bin/bash
echo "Ожидание подключения к базе данных..."
until nc -z mysql 3306; do
    echo "Ожидание..."
    sleep 2
done
echo "База данных доступна. Запуск приложения..."
exec "$@"
