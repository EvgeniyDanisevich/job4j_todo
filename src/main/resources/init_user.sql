create table if not exists users (
    id serial primary key,
    name varchar(255),
    email text unique,
    password text
);