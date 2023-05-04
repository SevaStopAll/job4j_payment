create table IF NOT EXISTS payments(
    id serial primary key not null,
    pay_method int not null references pay_methods(id),
    status_id int not null references status(id)
);