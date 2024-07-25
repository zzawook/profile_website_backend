/usr/bin/mysqldump -u $MYSQL_USER -p$MYSQL_PASSWORD profile_website > /dbBackup.sql
aws s3 cp /dbBackup.sql s3://kjaehyeok21/dbBackup.sql