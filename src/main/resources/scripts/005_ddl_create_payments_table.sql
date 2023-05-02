create table IF NOT EXISTS payments(
    id serial primary key not null,
    payment_method_id int not null references payment_methods(id),
    status_id int not null references statuses(id)
);