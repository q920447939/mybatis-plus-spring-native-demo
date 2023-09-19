


CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `organization_id` int(11) unsigned NOT NULL COMMENT '组织ID',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `name` varchar(60) NOT NULL DEFAULT '未设置' COMMENT '姓名',
  `is_enabled` tinyint(1) unsigned NOT NULL DEFAULT 1 COMMENT '是否启用',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '更新时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `updated_by` int(11) unsigned DEFAULT NULL COMMENT '更新者',
  `nickname` varchar(60) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(300) DEFAULT NULL COMMENT '头像url',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户';
