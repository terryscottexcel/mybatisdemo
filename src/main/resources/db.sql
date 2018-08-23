
create database mybatisdb character set='utf8';

create table t_blog(
id int auto_increment comment '编号',
code varchar(20) comment '代码',
name varchar(50) comment '名字',
rmk varchar(500) comment '备注',
primary key (id)
);
alter table t_blog comment '博客表';



create table t_bloginfo(
id int auto_increment comment '编号',
title varchar(200) comment '标题',
context varchar(2000) comment '内容',
keyword varchar(500) comment '关键字',
user_id int comment '用户id',
primary key (id)
);
alter table t_bloginfo comment '博客信息表';

create table t_userinfo(
id int auto_increment comment '编号',
user_code varchar(20) comment '代码',
user_name varchar(50) comment '名字',
birthday datetime comment '生日',
primary key (id)
);
alter table t_userinfo comment '用户信息表';

create table t_fansinfo(
id int auto_increment comment '编号',
fans_code varchar(20) comment '粉丝代码',
fans_name varchar(200) comment '粉丝名字',
fans_rmk varchar(500) comment '粉丝备注',
user_id int comment '用户id',
primary key (id)
);
alter table t_fansinfo comment '粉丝信息表';


create table t_filmfocus(
id int auto_increment comment '编号',
film_code varchar(20) comment '电影代号',
film_name varchar(200) comment '电影名字',
film_actors varchar(2000) comment '电影演员',
film_show_date datetime comment '电影上映日期',
film_price decimal(5,2) comment '电影票价',
film_rmk varchar(5000) comment '电影备注',
user_id int comment '用户id',
primary key (id)
);
alter table t_filmfocus comment '关注电影信息表';


