echo "MYSQL_USERNAME=$(aws secretsmanager get-secret-value --secret-id /kjaehyeok21_profile_website/spring_secrets | jq -r '.SecretString | fromjson | .mysql_username')" >> .env
echo "MYSQL_PASSWORD=$(aws secretsmanager get-secret-value --secret-id /kjaehyeok21_profile_website/spring_secrets | jq -r '.SecretString | fromjson | .mysql_password')" >> .env
echo "MYSQL_DB_NAME=$(aws secretsmanager get-secret-value --secret-id /kjaehyeok21_profile_website/spring_secrets | jq -r '.SecretString | fromjson | .mysql_db_name')" >> .env

echo "CREATE DATABASE IF NOT EXISTS profile_website;" > ./mysql/init.sql
echo "GRANT ALL ON *.* TO '$(aws secretsmanager get-secret-value --secret-id /kjaehyeok21_profile_website/spring_secrets | jq -r '.SecretString | fromjson | .mysql_username')'@'%' WITH GRANT OPTION;" >> ./mysql/init.sql
echo "FLUSH PRIVILEGES;" >> ./mysql/init.sql