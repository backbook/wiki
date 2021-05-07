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

