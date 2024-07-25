FROM jenkins/jenkins:latest

# Set environment variables
# ENV JENKINS_OPTS=""
# ENV JAVA_OPTS=""

# Expose Jenkins port
EXPOSE 8080

# Set permissions
USER root
RUN chown -R jenkins:jenkins /usr/share/jenkins/ref/

RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
RUN unzip awscliv2.zip
RUN ./aws/install

RUN apt-get update
RUN apt-get install less jq -y

# Add cronjob for Jenkins backup every 2AM
RUN echo "0 2 * * * aws s3 sync --delete /var/jenkins_home s3://kjaehyeok21/jenkins_home" >> /etc/crontab

# Start Jenkins
# CMD ["/sbin/tini", "--", "/usr/local/bin/jenkins.sh"]