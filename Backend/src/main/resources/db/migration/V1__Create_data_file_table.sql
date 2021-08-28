
CREATE SCHEMA IF NOT EXISTS friendlyBudget;

USE friendlyBudget;

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
