#!/bin/sh

# Start MySQL service in the background
mysqld_safe --datadir='/var/lib/mysql' &

# Wait for MySQL to fully start before proceeding
until mysqladmin ping -h 127.0.0.1 --silent; do
    echo "Waiting for MySQL to start..."
    sleep 2
done

# Initialize MySQL with a custom database and user
echo "Initializing MySQL database..."
mysql -uroot -p$MYSQL_ROOT_PASSWORD <<EOF
CREATE DATABASE IF NOT EXISTS $MYSQL_DATABASE;
CREATE USER IF NOT EXISTS '$MYSQL_USER'@'%' IDENTIFIED BY '$MYSQL_PASSWORD';
GRANT ALL PRIVILEGES ON $MYSQL_DATABASE.* TO '$MYSQL_USER'@'%';
FLUSH PRIVILEGES;
EOF

# Start the Spring Boot application
echo "Starting Spring Boot application..."
exec java -jar /app.jar
