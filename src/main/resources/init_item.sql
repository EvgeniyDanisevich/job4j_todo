create table if not exists item (
    id serial primary key,
    user_id int not null references users(id),
    description text,
    created timestamp,
    done boolean default false NOT NULL
);