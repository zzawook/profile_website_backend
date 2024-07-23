./scripts/clear_secrets.sh
./scripts/fetch_mysql_secret.sh
./scripts/fetch_redis_secret.sh

echo ${HOME}

docker compose up -d --build