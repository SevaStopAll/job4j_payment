create table IF NOT EXISTS pay_methods(
    id serial primary key not null,
    name varchar(2000) not null
);

comment on table pay_methods is '������� �������� ������';
comment on column pay_methods.id is '������������� ������� ������';
comment on column pay_methods.name is '�������� ������� ������';