./scripts/initialize.sh

echo "AWS_DIRECTORY=${AWS_DIRECTORY}" >> .env

docker image rm -f kjaehyeok21/profile_website

docker compose build

docker compose down

docker compose up -d