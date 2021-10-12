#!/bin/bash
USER=admin
PASS=admin
DATABASE=friendlybudget

echo "Creating MySQL user, database and exemple user (Gabriel)"
mysql -u root << MYSQL_SCRIPT
CREATE USER '$USER'@'localhost' IDENTIFIED BY '$PASS';
GRANT ALL PRIVILEGES ON $USER.* TO '$USER'@'localhost';
FLUSH PRIVILEGES;


USE $DATABASE
CREATE SCHEMA IF NOT EXISTS $DATABASE;

USE $DATABASE;

create table USERS(
    NAME varchar(50) NOT NULL PRIMARY KEY
);

create table TRANSACTIONS(
    NAME varchar(50) NOT NULL,
    FOREIGN KEY (NAME) REFERENCES USERS(NAME),
    DATE char(10) NOT NULL,
    VALUE float NOT NULL,
    DESCRIPTION varchar(50)
);

INSERT INTO USERS (NAME) VALUES ('Gabriel');

MYSQL_SCRIPT