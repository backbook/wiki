drop table if exists wiki.`test`;

CREATE TABLE wiki.test(
    `id` INT NOT NULL comment 'primary key',
    `name` VARCHAR(50) comment '名称',
    `password` VARCHAR(50) comment '密码',
    `create_time` BIGINT(50) COMMENT 'create time',
    `update_time` BIGINT(50) COMMENT 'update time',
    primary key (`id`)
) engine=innodb default charset utf8mb4 comment '测试';

show tables;

select *
from test;

show databases;