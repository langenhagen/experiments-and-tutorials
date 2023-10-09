-- initial code for the database layer.

-- create a database
CREATE DATABASE mydb;
\connect mydb;

-- create table for an event store
CREATE TABLE mytable (
    id SERIAL PRIMARY KEY,
    value TEXT NOT NULL
);
