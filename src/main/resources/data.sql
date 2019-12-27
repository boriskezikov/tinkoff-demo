drop table if exists application, contact;

create table application
(
  contact_id     bigint                not null,
  application_id bigint auto_increment not null primary key,
  product_name   character varying(128) not null,
  crt_date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
);

create table contact
(
  contact_id bigint not null primary key
);

insert into contact(contact_id)
values (1231);
insert into contact(contact_id)
values (1344);
insert into contact(contact_id)
values (1245);
insert into contact(contact_id)
values (0991);
insert into contact(contact_id)
values (11);
insert into contact(contact_id)
values (123);
insert into contact(contact_id)
values (414);
insert into contact(contact_id)
values (5512);

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










