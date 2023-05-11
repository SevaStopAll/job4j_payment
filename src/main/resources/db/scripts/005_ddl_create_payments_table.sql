create table IF NOT EXISTS payments(
    id serial primary key not null,
    pay_method int not null references pay_methods(id),
    status_id int not null references status(id)
);

comment on table payments is 'Таблица проведения оплаты';
comment on column payments.id is 'Идентификатор оплаты';
comment on column payments.pay_method is 'Способ оплаты';
comment on column payments.status_id is 'Статус оплаты';