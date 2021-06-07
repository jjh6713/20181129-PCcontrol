create table pc_member(
member_id varchar(15) PRIMARY KEY,
member_pw varchar(15) not null,
member_name varchar(20) not null,
member_age int(5) not null,
member_Phone varchar(20) not null,
member_Mail varchar(50) not null,
member_cost varchar(20) not null
);

create table pc_stock(
stock_num int(10) AUTO_INCREMENT PRIMARY KEY,
stock_name varchar(20) not null,
stock_price int(10) not null,
stock_remarks varchar(50),
stock_category int(10) REFERENCES pc_category(category_num)
);

create table pc_category(
category_num int(10) AUTO_INCREMENT PRIMARY KEY,
category_name varchar(20) not null
);

create table pc_sale(
sale_num int(10) AUTO_INCREMENT PRIMARY KEY,
sale_id varchar(15) REFERENCES pc_member(member_id),
sale_content varchar(50) not null,
sale_cost varchar(20) not null,
sale_date varchar(30) not null
);