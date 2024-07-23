# Use the official MySQL 8.0 image as the base image
FROM mysql:8.0

# Set environment variables for MySQL
# ENV MYSQL_DATABASE=$MYSQL_DBNAME
# ENV MYSQL_USER=$MYSQL_USERNAME
# ENV MYSQL_PASSWORD=$MYSQL_PASSWORD
# ENV MYSQL_ROOT_PASSWORD=$MYSQL_PASSWORD

# Copy the SQL script to initialize the database
COPY init.sql /docker-entrypoint-initdb.d/

# Expose the default MySQL port
EXPOSE 3306