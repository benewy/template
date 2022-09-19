/*
 通用数据表
 数据库类型 : MySQL
 数据库版本 : 80020
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for access_token
-- ----------------------------
DROP TABLE IF EXISTS `access_token`;
CREATE TABLE `access_token` (
  `access_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `token_type` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '类型 T：政务钉钉 Q：扫码鉴权',
  PRIMARY KEY (`access_token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT COMMENT='token类型表';

-- ----------------------------
-- Table structure for log_login
-- ----------------------------
DROP TABLE IF EXISTS `log_login`;
CREATE TABLE `log_login` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` char(32) NOT NULL COMMENT '用户id',
  `user_type` char(1) NOT NULL COMMENT '用户类型',
  `login_type` char(1) NOT NULL COMMENT '登录类型',
  `module_type` varchar(255) NOT NULL COMMENT '模块类型',
  `login_ip` varchar(255) NOT NULL COMMENT '登录ip',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=357 DEFAULT CHARSET=utf8 COMMENT='登录日志表';

-- ----------------------------
-- Table structure for log_req
-- ----------------------------
DROP TABLE IF EXISTS `log_req`;
CREATE TABLE `log_req` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` char(32) DEFAULT NULL COMMENT '用户id',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型',
  `req_url` varchar(5000) NOT NULL COMMENT '请求地址',
  `req_method` varchar(255) NOT NULL COMMENT '请求方法',
  `req_ip` varchar(255) NOT NULL COMMENT '请求ip',
  `req_params` longtext COMMENT '请求参数',
  `req_body` longtext COMMENT '请求body',
  `duration` int NOT NULL COMMENT '时长',
  `is_error` tinyint unsigned NOT NULL COMMENT '是否异常',
  `error_info` longtext COMMENT '异常信息',
  `error_code` varchar(255) DEFAULT NULL COMMENT '错误码',
  `error_msg` varchar(5000) DEFAULT NULL COMMENT '错误消息',
  `module_type` varchar(255) NOT NULL COMMENT '模块类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=146831 DEFAULT CHARSET=utf8 COMMENT='请求日志记录表';

-- ----------------------------
-- Table structure for log_sql
-- ----------------------------
DROP TABLE IF EXISTS `log_sql`;
CREATE TABLE `log_sql` (
  `id` int NOT NULL AUTO_INCREMENT,
  `table_name` varchar(255) NOT NULL COMMENT '表名',
  `sql_type` char(1) NOT NULL COMMENT 'sql类型',
  `exe_sql` longtext NOT NULL COMMENT '执行的sql',
  `source_data` longtext NOT NULL COMMENT '源数据',
  `fields` varchar(5000) NOT NULL COMMENT '字段',
  `where_sql` text NOT NULL COMMENT '查询',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3916 DEFAULT CHARSET=utf8 COMMENT='sql日志表';

-- ----------------------------
-- Table structure for sys_agency
-- ----------------------------
DROP TABLE IF EXISTS `sys_agency`;
CREATE TABLE `sys_agency` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `seq` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '排序',
  `pid` int DEFAULT NULL COMMENT '父id',
  `user_ids` longtext COMMENT '用户id',
  `manager_ids` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '管理人员id',
  `is_father` int(1) unsigned zerofill DEFAULT '0' COMMENT '是否为父级单位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12728 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门';

-- ----------------------------
-- Table structure for sys_agency_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_agency_user`;
CREATE TABLE `sys_agency_user` (
  `id` varchar(32) NOT NULL,
  `agency_id` varchar(32) DEFAULT NULL COMMENT '部门id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门用户';

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `id` int NOT NULL,
  `pid` int unsigned NOT NULL COMMENT '父级id',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `level` tinyint unsigned NOT NULL COMMENT '级别 1：省级 2：市级 3：区县级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行政区域表';

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` char(32) NOT NULL,
  `key` varchar(255) NOT NULL COMMENT '配置项key',
  `value` varchar(5000) NOT NULL COMMENT '配置值',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置表';

-- ----------------------------
-- Table structure for sys_files
-- ----------------------------
DROP TABLE IF EXISTS `sys_files`;
CREATE TABLE `sys_files` (
  `id` char(32) NOT NULL COMMENT 'id',
  `filename` varchar(255) NOT NULL COMMENT '文件名称',
  `suffix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文件后缀',
  `path` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL COMMENT '文件路径',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '文件创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件表';

-- ----------------------------
-- Table structure for sys_filter_unit
-- ----------------------------
DROP TABLE IF EXISTS `sys_filter_unit`;
CREATE TABLE `sys_filter_unit` (
  `id` char(32) NOT NULL,
  `key` varchar(255) NOT NULL COMMENT '业务key',
  `remark` varchar(255) DEFAULT NULL COMMENT '说明',
  `unit_mode` char(1) NOT NULL COMMENT '单位模式',
  `unit_data` varchar(255) DEFAULT NULL COMMENT '单位数据',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户筛选配置';

-- ----------------------------
-- Table structure for sys_filter_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_filter_user`;
CREATE TABLE `sys_filter_user` (
  `id` char(32) NOT NULL,
  `key` varchar(255) NOT NULL COMMENT '业务key',
  `type` char(1) NOT NULL COMMENT '类型',
  `remark` varchar(255) DEFAULT NULL COMMENT '说明',
  `unit_mode` char(1) DEFAULT NULL COMMENT '单位模式',
  `unit_data` varchar(255) DEFAULT NULL COMMENT '单位数据',
  `user_mode` char(1) DEFAULT NULL COMMENT '用户模式',
  `user_data` longtext COMMENT '用户数据',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户筛选配置';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `pid` int NOT NULL COMMENT '父级菜单',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  `type` tinyint unsigned NOT NULL COMMENT '0：菜单夹 1：菜单',
  `sort_num` float(3,1) unsigned NOT NULL COMMENT '排序',
  `is_new_tab` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否在新标签下打开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 COMMENT='路由菜单表';

-- ----------------------------
-- Table structure for sys_menu_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_permission`;
CREATE TABLE `sys_menu_permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `sys_menu_id` int unsigned NOT NULL COMMENT '菜单id',
  `permission` varchar(255) NOT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message` (
  `id` char(32) NOT NULL,
  `problem_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题id',
  `look_user` char(32) NOT NULL COMMENT '查看人',
  `user_type` char(1) NOT NULL COMMENT '查看人用户类型',
  `content` varchar(2000) NOT NULL COMMENT '消息内容',
  `create_user` char(32) DEFAULT NULL COMMENT '创建人(发送人)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `look_time` datetime DEFAULT NULL COMMENT '查看时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `remark` varchar(255) DEFAULT NULL COMMENT '说明',
  `type` char(1) NOT NULL COMMENT '类型',
  `unit_tags` varchar(255) DEFAULT NULL COMMENT '类型为范围是的单位标签',
  `user_tags` varchar(255) DEFAULT NULL COMMENT '类型为范围是的用户标签',
  `deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sys_role_id` int unsigned NOT NULL COMMENT '角色id',
  `sys_menu_id` int unsigned NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=993 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Table structure for sys_tag
-- ----------------------------
DROP TABLE IF EXISTS `sys_tag`;
CREATE TABLE `sys_tag` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `tag_type` varchar(255) DEFAULT NULL COMMENT '类型',
  `tag_name` varchar(255) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='标签表';

-- ----------------------------
-- Table structure for sys_tag_map
-- ----------------------------
DROP TABLE IF EXISTS `sys_tag_map`;
CREATE TABLE `sys_tag_map` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL COMMENT '类型',
  `sys_tag_id` int DEFAULT NULL COMMENT '关联tagId',
  `ass_id` varchar(40) DEFAULT NULL COMMENT '关联映射表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='标签关联表';

-- ----------------------------
-- Table structure for sys_unit
-- ----------------------------
DROP TABLE IF EXISTS `sys_unit`;
CREATE TABLE `sys_unit` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `pid` int unsigned NOT NULL,
  `unit_name` varchar(255) NOT NULL COMMENT '单位名称',
  `sort_num` float(3,1) unsigned NOT NULL COMMENT '序号',
  `type` char(1) NOT NULL COMMENT '类型',
  `dd_type` char(1) DEFAULT NULL COMMENT '钉钉类型',
  `organization_code` varchar(255) DEFAULT NULL COMMENT '组织code',
  `unified_social_credit_code` varchar(255) DEFAULT NULL COMMENT '统一社会信用代码',
  `is_last` tinyint unsigned DEFAULT '0' COMMENT '是否最后',
  `deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `unit_code` varchar(30) DEFAULT NULL COMMENT '单位编码\r\n',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unit_unit_code` (`unit_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12328 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='单位表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` char(32) NOT NULL COMMENT 'id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `employee_code` varchar(255) DEFAULT NULL COMMENT '钉钉员工code',
  `zwdd_account_id` varchar(255) DEFAULT NULL COMMENT '钉钉用户id',
  `current_unit_index` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '用户当前单位索引',
  `deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sys_user_id` char(32) NOT NULL COMMENT '用户id',
  `sys_role_id` int unsigned NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='用户权限表';

-- ----------------------------
-- Table structure for sys_user_unit
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_unit`;
CREATE TABLE `sys_user_unit` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `sys_user_id` char(32) NOT NULL COMMENT '用户id',
  `sys_unit_id` int unsigned NOT NULL COMMENT '单位id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1234570 DEFAULT CHARSET=utf8 COMMENT='用户单位关联表';

SET FOREIGN_KEY_CHECKS = 1;
