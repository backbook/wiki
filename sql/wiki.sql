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



