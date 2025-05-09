Запуск котейнера с БД и контейнера с приложением:

```bash
docker-compose up -d
```

Собрать образ приложения:

```bash
docker build -t subscription_service:latest .
```

Запуск контейнера с БД:

```bash
docker-compose -f ./docker-compose-dev.yml up -d
```
