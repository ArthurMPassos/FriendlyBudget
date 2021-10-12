#!/bin/bash
echo "Creating MySQL user and database"
USER=admin
PASS=admin
DATABASE=friendlybudget

mysql -u root << MYSQL_SCRIPT
CREATE DATABASE $DATABASE;
CREATE USER '$USER'@'localhost' IDENTIFIED BY '$PASS';
GRANT ALL PRIVILEGES ON $USER.* TO '$USER'@'localhost';
FLUSH PRIVILEGES;
MYSQL_SCRIPT