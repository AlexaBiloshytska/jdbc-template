create table products
(id int  primary key,
name varchar(1000),
category varchar(200),
description varchar(2000));

insert into products values (1,'Asus UX430', 'Computers','A laptop which helps you to reach your goals');
insert into products values (2,'Huawei P20 Lite', 'Mobile', 'Tina Karol presents a new technology mobile phone');
insert into products values (3,'Samsung S 10','Mobile','Flagman which you don"t forget');
commit;