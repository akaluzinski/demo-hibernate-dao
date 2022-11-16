create table product (
              id bigint not null auto_increment primary key,
              description varchar(255),
              product_status varchar(12),
              created_date timestamp,
              updated_date timestamp
) engine = InnoDB;
