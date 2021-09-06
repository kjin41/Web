-- 1.상품정보를 저장할 수 있는 테이블을 구성하여 보자
create database productdb;
use productdb;

-- 2.상품 데이터를 5 개 이상 저장하는 SQL 을 작성하여 보자
create table products(
	pcode int(4) primary key,
	pname varchar(20),
	price int(10));

insert into products(pcode, pname, price)
values(1001, '삼성 TV 138cm 4K UHD', 779000);
insert into products(pcode, pname, price)
values(2001, '삼성 냉장고 4도어 1쇼케이스', 2050000);
insert into products(pcode, pname, price)
values(1002, '삼성 TV QN65Q90T 65인치', 2460540);
insert into products(pcode, pname, price)
values(1003, '삼성 TV 50인치 UHD 4K', 669000);
insert into products(pcode, pname, price)
values(2002, '삼성 냉장고 2도어 냉장/냉동', 2000000);

-- 3.상품을 세일하려고 한다 . 15% 인하된 가격의 상품 정보를 출력하세요
select pname, price*0.85 "15% 할인가"
from products;

-- 4. TV관련 상품을 가격을 20% 인하하여 저장하세요 . 그 결과를 출력하세요
update products
set price=price*0.8;

select pname, price "20% 할인가"
from products;

-- 5.저장된 상품 가격의 총 금액을 출력하는 SQL 문장을 작성하세요
select sum(price)
from products;

