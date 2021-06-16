create table if not exists category (
    id serial primary key,
    name text
);

insert into category (name) values ('Обычное');
insert into category (name) values ('Срочное');
insert into category (name) values ('Важное');
