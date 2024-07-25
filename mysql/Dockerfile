# Use the official MySQL 8.0 image as the base image
FROM mysql:8.0

# Copy the SQL script to initialize the database
COPY init.sql /docker-entrypoint-initdb.d/

# Install unzip, cron
RUN microdnf update && microdnf install -y unzip cronie

RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
RUN unzip awscliv2.zip
RUN ./aws/install

# Add the cronjob script
COPY backup.sh /usr/local/bin/backup.sh
RUN chmod +x /usr/local/bin/backup.sh

# Add the cronjob schedule

RUN crontab -l | { cat; echo "0 2 * * * /usr/local/bin/backup.sh"; } | crontab -

RUN crond

# Expose the default MySQL port
EXPOSE 3306