#!/bin/sh
set -e

# Ожидание базы данных (если требуется)
if [ "$DB_HOST" != "" ]; then
  echo "Ожидание базы данных на $DB_HOST:$DB_PORT..."
  while ! nc -z $DB_HOST $DB_PORT; do
    sleep 1
  done
  echo "База данных доступна!"
fi

# Запуск приложения
exec "$@"
