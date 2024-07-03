FROM redis:latest

# Expose the default Redis port
EXPOSE 6379

COPY redis.conf /usr/local/etc/redis/redis.conf

CMD [ "redis-server", "/usr/local/etc/redis/redis.conf" ]

# You can add any additional configuration options here
# For example, to enable persistence, you can uncomment the following line:
# CMD ["redis-server", "--appendonly", "yes"]
# TODO: Add a custom configuration file to enable persistence