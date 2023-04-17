-- mysql 주석
drop database shopping ;
create database shopping ;
-- show databases;
use shopping;

desc products ;

-- where 절 없이 삭제시 경고 메시지 제거하기 위한 설정
SET SQL_SAFE_UPDATES=0;

select * from products ;
delete from products ;
commit ;
select * from products ;

update members set role = 'ADMIN' where email = 'admin@naver.com' ;
update members set role = 'ADMIN' where email = 'ugcadman02@naver.com' ;
commit ;
select * from members ;
desc members;

-- 영속성 전이 테스트
select max(order_id) from orders ;

-- select ord.order_id, op.product_id, prd.price, prd.stock

select * from orders ;
select * from order_products ;
select * from products ;

select ord.order_id, op.product_id, prd.name, prd.price, prd.stock
from (orders ord join order_products op
on ord.order_id=op.order_id) join products prd
on op.product_id=prd.product_id ;


delete from product_images ;
delete from products ;


delete from product_images ;
delete from products ;
commit ;

select * from products ;
select * from product_images ;


-- carts 
drop table cart;
desc carts ;
delete from carts ; 
select * from carts  ;



delete from cart_products ; 
delete from carts ; 
commit ;
select * from carts ; 
select * from cart_products ; 



rollback ;
delete from order_products ;
delete from product_images ;
select * from product_images ;
commit ;
alter table order_product drop order_item_id ;
select * from order_product ;
desc order_product;

-- 상품 섹션
delete from products ;
rollback ;
select count(*) from products ;
select * from products ;
desc products;

select * from products ;

select * from products
where product_status = 'SELL' and description like '%아%'
order by price desc ;

select * from products
where product_status = 'SELL' 
and price > 200 
and description like '%어%' ;

select * from products
where product_status = 'SELL' 
and price > 200 
and description like '%어%' 
LIMIT 2, 2;

-- 상품 이미지
delete from product_image ;
commit;

-- 회원섹션
-- delete from members ;
rollback ;
select count(*) from members ;
select * from members ;
desc members;
-- drop table members ;
delete from members where id = 29;
update members set role = 'USER' where id = 29;
update members set role = 'ADMIN' where id = 31;
commit ;

# for saram
select * from sarams ;
desc sarams;
select * from sarams where salary >= 500 ;

select * from sarams
where salary < 600 and address like '%포%'
order by name desc ;

select * from sarams
where salary <= 600 and address like '%포%'
order by name desc 
limit 4, 4;


use shopping;
delete from products ;
select count(*) from products ;
select * from products ;
desc products;

select * from products
where product_status = 'SELL' and description like '%아%'
order by price desc ;

select * from products
where product_status = 'SELL' 
and price > 200 
and description like '%어%' ;

select * from products
where product_status = 'SELL' 
and price > 200 
and description like '%어%' 
LIMIT 2, 2;



# for saram
select * from sarams ;
desc sarams;
select * from sarams where salary >= 500 ;

select * from sarams
where salary < 600 and address like '%포%'
order by name desc ;

select * from sarams
where salary <= 600 and address like '%포%'
order by name desc 
limit 4, 4;

drop table boards ;
create table boards(
	no int not null auto_increment primary key,
	writer varchar(255),
    subject varchar(255),    
    description varchar(255)
);
insert into boards(writer, subject, description) values('kim', 'jsp', '제이에스피');
insert into boards(writer, subject, description) values('park', 'python', '파이쑨');
commit;
select * from boards ;