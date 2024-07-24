./scripts/clear_secrets.sh
./scripts/fetch_mysql_secret.sh
./scripts/fetch_redis_secret.sh

echo ${HOME}

docker network create kjaehyeok21_network --subnet=172.18.0.0/24
docker compose up -d --build