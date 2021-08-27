
CREATE SCHEMA IF NOT EXISTS friendlyBudget;

USE friendlyBudget;

create table USERS(
    NAME varchar(50) PRIMARY KEY NOT NULL
);

create table WALLET(
    NAME varchar(50) NOT NULL,
    FOREIGN KEY(NAME) REFERENCES USERS(NAME)
);