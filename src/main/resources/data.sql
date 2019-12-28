drop table if exists application, contact;

create table application
(
  contact_id     bigint                not null,
  application_id bigint auto_increment not null primary key,
  product_name   character varying(128) not null,
  crt_date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP() not null
);


insert into application(contact_id, product_name)
values (1231, 'order1');
insert into application(contact_id, product_name)
values (1231, 'order2');
insert into application(contact_id, product_name)
values (11, 'order1');
insert into application(contact_id, product_name)
values (1231, 'order3');
insert into application(contact_id, product_name)
values (1344, 'order1');
insert into application(contact_id, product_name)
values (1344, 'order2');
insert into application(contact_id, product_name)
values (1231, 'order3');
insert into application(contact_id, product_name)
values (1231, 'order3');
insert into application(contact_id, product_name)
values (1231, 'order4');
insert into application(contact_id, product_name)
values (1231, 'order5');
insert into application(contact_id, product_name)
values (1231, 'order6');
insert into application(contact_id, product_name)
values (1231, 'order7');
insert into application(contact_id, product_name)
values (1231, 'order8');
insert into application(contact_id, product_name)
values (1231, 'order9');
insert into application(contact_id, product_name)
values (1231, 'order10');
insert into application(contact_id, product_name)
values (1231, 'order11');










