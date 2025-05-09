# Subscription service

Запуск БД и приложения:

```bash
docker-compose up -d
```

Собрать образ приложения:

```bash
docker build -t subscription_service:latest .
```

Запуск БД:

```bash
docker-compose -f ./docker-compose-dev.yml up -d
```
