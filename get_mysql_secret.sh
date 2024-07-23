> .env
aws secretsmanager get-secret-value --secret-id /kjaehyeok21_profile_website/spring_secrets | jq -r '.SecretString | fromjson | .mysql_username' >> .env
aws secretsmanager get-secret-value --secret-id /kjaehyeok21_profile_website/spring_secrets | jq -r '.SecretString | fromjson | .mysql_password' >> .env
aws secretsmanager get-secret-value --secret-id /kjaehyeok21_profile_website/spring_secrets | jq -r '.SecretString | fromjson | .mysql_db_name' >> .env

