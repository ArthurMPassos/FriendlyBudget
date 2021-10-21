#!/bin/bash
USER=admin
PASS=admin
DATABASE=friendlyBudget

#echo "Creating MySQL user, database and exemple user (Gabriel)"
#CREATE USER '$USER'@'192.168.1.21' IDENTIFIED BY '$PASS';
mysql -u root << MYSQL_SCRIPT

GRANT SELECT ON * . * TO '$USER'@'%';
GRANT INSERT ON * . * TO '$USER'@'%';
FLUSH PRIVILEGES;

CREATE SCHEMA IF NOT EXISTS $DATABASE;

USE $DATABASE;

create table if not exists USERS(
    NAME varchar(50) NOT NULL PRIMARY KEY
);

create table if not exists TRANSACTIONS(
    NAME varchar(50) NOT NULL,
    FOREIGN KEY (NAME) REFERENCES USERS(NAME),
    DATE char(10) NOT NULL,
    VALUE float NOT NULL,
    DESCRIPTION varchar(50)
);

INSERT INTO USERS (NAME) VALUES ('Gabriel');

MYSQL_SCRIPT
