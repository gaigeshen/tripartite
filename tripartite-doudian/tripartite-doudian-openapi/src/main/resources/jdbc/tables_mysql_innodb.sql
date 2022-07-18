create table if not exists `doudian_access_token` (
    `id` bigint(20) not null auto_increment comment '系统编号',
    `access_token` varchar(512) collate utf8mb4_bin default null comment '访问令牌',
    `refresh_token` varchar(512) collate utf8mb4_bin default null comment '刷新令牌',
    `scope` varchar(255) collate utf8mb4_bin default null comment '授权作用域',
    `shop_id` varchar(128) collate utf8mb4_bin default null comment '店铺编号',
    `shop_name` varchar(128) collate utf8mb4_bin default null comment '店铺名称',
    `expires_in` bigint(20) default null comment '有效期时长单位秒',
    `expires_timestamp` bigint(20) default null comment '有效期截止时间单位秒',
    `update_time` datetime default null comment '更新时间',
    primary key (`id`)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_bin comment='抖店访问令牌';
