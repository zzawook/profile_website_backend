./scripts/initialize.sh

export AWS_DIRECTORY=${HOME}/.aws

docker compose up -d --build mysql redis
docker compose up -d --build spring-server nginx