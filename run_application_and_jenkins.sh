./scripts/clear_secrets.sh
./scripts/fetch_mysql_secret.sh
./scripts/fetch_redis_secret.sh
# ./scripts/run_jenkins.sh

docker compose up -d --build
docker compose -f jenkins/compose.yaml up -d --build