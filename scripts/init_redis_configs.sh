echo "REDIS_USERNAME=$(aws secretsmanager get-secret-value --secret-id /kjaehyeok21_profile_website/spring_secrets | jq -r '.SecretString | fromjson | .redis_client_name')" >> .env
echo "REDIS_PASSWORD=$(aws secretsmanager get-secret-value --secret-id /kjaehyeok21_profile_website/spring_secrets | jq -r '.SecretString | fromjson | .redis_password')" >> .env

rm -rf ./redis/redis.conf
cp ./redis/boilerplate.conf ./redis/redis.conf
printf "\nuser $(aws secretsmanager get-secret-value --secret-id /kjaehyeok21_profile_website/spring_secrets | jq -r '.SecretString | fromjson | .redis_client_name') on +@all ~* >$(aws secretsmanager get-secret-value --secret-id /kjaehyeok21_profile_website/spring_secrets | jq -r '.SecretString | fromjson | .redis_password')" >> ./redis/redis.conf