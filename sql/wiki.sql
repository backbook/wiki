drop table if exists wiki.`test`;

CREATE TABLE wiki.test(
    `id` INT NOT NULL comment 'primary key',
    `name` VARCHAR(50) comment '名称',
    `password` VARCHAR(50) comment '密码',
    `create_time` BIGINT(50) COMMENT 'create time',
    `update_time` BIGINT(50) COMMENT 'update time',
    primary key (`id`)
) engine=innodb default charset utf8mb4 comment '测试';

INSERT INTO wiki.test (id, name, password, create_time, update_time) VALUES (1, 'backbook', 'backbook', 1620376675418, 1620376675418);

CREATE TABLE wiki.demo(
                          `id` INT NOT NULL comment 'primary key',
                          `name` VARCHAR(50) comment '名称',
                          `password` VARCHAR(50) comment '密码',
                          `create_time` BIGINT(50) COMMENT 'create time',
                          `update_time` BIGINT(50) COMMENT 'update time',
                          primary key (`id`)
) engine=innodb default charset utf8mb4 comment '测试';

INSERT INTO wiki.demo (id, name, password, create_time, update_time) VALUES (2, 'backbook', 'backbook', 1620376675418, 1620376675418);

create database wiki;

drop table if exists wiki.`ebook`;
create table wiki.`ebook`(
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    `category1_id` bigint comment '分类1',
    `category2_id` bigint comment '分类2',
    `description` varchar(200) comment '描述',
    `cover` varchar(200) comment '封面',
    `doc_count` int comment '文档数',
    `view_count`  int comment '阅读数',
    `vote_count`  int comment '点赞数',
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment ='电子书信息';


INSERT INTO wiki.ebook (id, name, category1_id, category2_id, description, cover, doc_count, view_count, vote_count)
VALUES (7, 'c++入门到解放', 1, 2, '并发编程', '并发', 23, 13, 23);

INSERT INTO wiki.ebook (id, name, category1_id, category2_id, description, cover, doc_count, view_count, vote_count)
VALUES (8, 'c入门到解放', 1, 2, '并发编程', '并发', 23, 13, 23);

INSERT INTO wiki.ebook (id, name, category1_id, category2_id, description, cover, doc_count, view_count, vote_count)
VALUES (9, 'scala入门到解放', 1, 2, '并发编程', '并发', 23, 13, 23);

INSERT INTO wiki.ebook (id, name, category1_id, category2_id, description, cover, doc_count, view_count, vote_count)
VALUES (10, 'lua入门到解放', 1, 2, '并发编程', '并发', 23, 13, 23);

INSERT INTO wiki.ebook (id, name, category1_id, category2_id, description, cover, doc_count, view_count, vote_count)
VALUES (11, 'hive入门到解放', 1, 2, '并发编程', '并发', 23, 13, 23);

INSERT INTO wiki.ebook (id, name, category1_id, category2_id, description, cover, doc_count, view_count, vote_count)
VALUES (12, 'hadoop入门到解放', 1, 2, '并发编程', '并发', 23, 13, 23);


## 分类
drop table if exists wiki.`category`;
create table wiki.`category` (
    `id` bigint not null comment 'id',
    `parent` bigint not null default 0 comment '父id',
    `name` varchar(50) not null comment '名称',
    `sort` int comment '顺序',
    primary key (`id`)
) engine = innodb default character set = utf8mb4 comment '分类';

insert into wiki.category (id,parent,name,sort) values (100,000,'前端开发',100);
insert into wiki.category (id,parent,name,sort) values (101,100,'Vue',101);
insert into wiki.category (id,parent,name,sort) values (102,100,'HTML & CSS',102);
insert into wiki.category (id,parent,name,sort) values (200,000,'Java',200);
insert into wiki.category (id,parent,name,sort) values (201,200,'基础应用',201);
insert into wiki.category (id,parent,name,sort) values (202,200,'框架应用',202);
insert into wiki.category (id,parent,name,sort) values (300,000,'Python',300);
insert into wiki.category (id,parent,name,sort) values (301,300,'基础应用',301);
insert into wiki.category (id,parent,name,sort) values (302,300,'进阶方向应用',302);
insert into wiki.category (id,parent,name,sort) values (400,000,'数据库',400);
insert into wiki.category (id,parent,name,sort) values (401,400,'数据库',401);





