CREATE TABLE `t_book`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `reader`      varchar(255),
    `isbn`        varchar(255),
    `title`       varchar(255),
    `author`      varchar(255),
    `description` varchar(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE `undo_log`
(
    `id`            bigint   NOT NULL AUTO_INCREMENT COMMENT 'increment id',
    `branch_id`     bigint   NOT NULL COMMENT 'branch transaction id',
    `xid`           varchar(100) NOT NULL COMMENT 'global transaction id',
    `context`       varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` longblob NOT NULL COMMENT 'rollback info',
    `log_status`    int      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   datetime NOT NULL COMMENT 'create datetime',
    `log_modified`  datetime NOT NULL COMMENT 'modify datetime',
    PRIMARY KEY (`id`)
) ;
