#!/bin/bash
USER=admin
PASS=admin
DATABASE=friendlyBudget

echo "Creating MySQL user, database and exemple user (Gabriel)"
mysql -u root << MYSQL_SCRIPT
CREATE USER '$USER'@'localhost' IDENTIFIED BY '$PASS';
GRANT SELECT ON * . * TO '$USER'@'localhost';
GRANT INSERT ON * . * TO '$USER'@'localhost';
FLUSH PRIVILEGES;

CREATE SCHEMA IF NOT EXISTS $DATABASE;

USE $DATABASE;

create table if not exists USERS(
    NAME varchar(50) NOT NULL PRIMARY KEY,
    PASSWORD varchar(50) NOT NULL
);

create table TRANSACTIONS(
    NAME varchar(50) NOT NULL,
    FOREIGN KEY (NAME) REFERENCES USERS(NAME),
    DATE char(10) NOT NULL,
    VALUE float NOT NULL,
    DESCRIPTION varchar(50)
);

INSERT INTO USERS (NAME,PASSWORD) VALUES ('Gabriel','senha123');

MYSQL_SCRIPT
