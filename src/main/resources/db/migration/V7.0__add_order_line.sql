create table order_line
(
    id bigint not null auto_increment primary key ,
    quantity_order int,
    order_header_id bigint,
    created_date timestamp,
    updated_date timestamp,
    constraint order_header_pk foreign key (order_header_id) references order_header(id)
);
