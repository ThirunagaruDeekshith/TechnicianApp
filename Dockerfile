# Use the Liberica OpenJDK Alpine image
FROM bellsoft/liberica-openjdk-alpine:18.0.2

# Install MySQL server
RUN apk update && apk add --no-cache mysql mysql-client

# Create MySQL data directory and set correct permissions using root privileges
RUN mkdir -p /var/lib/mysql && chown -R mysql:mysql /var/lib/mysql

# Initialize MySQL database
RUN mysql_install_db --user=mysql --datadir=/var/lib/mysql

# Set environment variables for MySQL
ENV MYSQL_ROOT_PASSWORD=root_password
ENV MYSQL_DATABASE=my_database
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=user_password

# Expose MySQL default port
EXPOSE 3306

# Create directory for Spring Boot app
VOLUME /tmp

# Copy the Spring Boot JAR file to the container
COPY target/*.jar app.jar

# Expose Spring Boot app port
EXPOSE 8080

# Copy the start script into the container
COPY start.sh /start.sh

# Make sure the start script is executable
RUN chmod +x /start.sh

# Set the entrypoint to run the start script
ENTRYPOINT ["/start.sh"]
