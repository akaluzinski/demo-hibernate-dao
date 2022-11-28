create table customer
(
    id            bigint not null auto_increment primary key,
    customer_name varchar(50),
    address       varchar(30),
    city          varchar(30),
    state         varchar(30),
    zip_code      varchar(30),
    phone         varchar(30),
    email         varchar(30),
    created_date  timestamp,
    updated_date  timestamp
);

alter table order_header
    add column customer_id bigint;

alter table order_header
    add constraint order_customer_pk
        foreign key (customer_id) references customer (id);

alter table order_header drop column customer;

insert into customer (customer_name, address, city, state, zip_code, phone, email)
values ('Abacki', 'Pilsudskiego 11', 'Kraków', 'Malopolskie', '31000', '555-555-555', 'example@mail.com');

update order_header
set order_header.customer_id = (select id from customer limit 1)
