# Use the redis-stack image as the base image
FROM redis/redis-stack-server

# Set the working directory to /app
WORKDIR /app

# Copy the redis configuration file to the container
COPY redis.conf /usr/local/etc/redis/redis.conf

# Expose the default Redis port
EXPOSE 6379

# Start Redis server using the custom configuration file
CMD ["redis-server", "/usr/local/etc/redis/redis.conf"]