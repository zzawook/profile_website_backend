FROM redis:latest

COPY redis.conf /usr/local/etc/redis/redis.conf

# Expose the default Redis port
EXPOSE 6379

ARG USERNAME

ARG PASSWORD

RUN redis-cli

RUN ACL SETUSER $USERNAME on >$PASSWORD allkeys allcommands

# Set the default command to run Redis server
CMD ["redis-server"]

# You can add any additional configuration options here
# For example, to enable persistence, you can uncomment the following line:
# CMD ["redis-server", "--appendonly", "yes"]
# TODO: Add a custom configuration file to enable persistence