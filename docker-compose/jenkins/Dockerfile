FROM jenkins/jenkins:latest

# Set environment variables
# ENV JENKINS_OPTS=""
# ENV JAVA_OPTS=""

# Expose Jenkins port
EXPOSE 8080

# Copy plugins.txt file
# COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

# Install Jenkins plugins
# RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# Copy custom configuration files
# COPY config.xml /usr/share/jenkins/ref/config.xml
# COPY scriptApproval.xml /usr/share/jenkins/ref/scriptApproval.xml

# Copy custom Groovy scripts
# COPY init.groovy.d/ /usr/share/jenkins/ref/init.groovy.d/

# Set permissions
USER root
RUN chown -R jenkins:jenkins /usr/share/jenkins/ref/
USER jenkins

# Start Jenkins
# CMD ["/sbin/tini", "--", "/usr/local/bin/jenkins.sh"]