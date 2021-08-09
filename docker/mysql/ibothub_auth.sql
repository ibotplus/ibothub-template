CREATE DATABASE `ibothub-auth` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ibothub-auth`;

drop table if exists `auth_user`;
CREATE TABLE `ibothub-auth`.`auth_user` (
	id BIGINT auto_increment NOT NULL primary key,
	username varchar(100) NULL COMMENT '账号',
	username_cn varchar(100) NULL COMMENT '用户名',
	password varchar(100) NULL COMMENT '密码',
	job_number varchar(100) NULL COMMENT '工号',
	sex int NULL COMMENT '性别，1-男；0-女',
	email varchar(200) NULL COMMENT '邮箱',
	phone varchar(100) NULL COMMENT '电话号码',
	birthday DATETIME NULL COMMENT '生日',
	id_card varchar(18) NULL COMMENT '身份证',
	remark varchar(1000) NULL COMMENT '备注',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	creator varchar(100) NULL COMMENT '创建人',
	modify_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	modifier varchar(100) NULL COMMENT '修改人',
	del_flag INT DEFAULT 0 NOT NULL COMMENT '删除标识，1-已删除；0-未删除',
	KEY `idx_user_username` (`username`) USING BTREE,
	KEY `idx_user_job_number` (`job_number`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

drop table if exists `auth_dept`;
CREATE TABLE `ibothub-auth`.`auth_dept` (
	id BIGINT auto_increment NOT NULL primary key,
	name varchar(200) NULL COMMENT '部门名称',
	parent_id BIGINT NULL COMMENT '上级部门',
	key_ varchar(200) NULL COMMENT '部门标识',
	path_ varchar(2000) NULL COMMENT '部门路径，小数点.分隔',
	remark varchar(1000) NULL COMMENT '备注',
	is_company INT DEFAULT 0 COMMENT '是否是公司，1-是；0-否，默认0',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	creator varchar(100) NULL COMMENT '创建人',
	modify_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	modifier varchar(100) NULL COMMENT '修改人',
  del_flag INT DEFAULT 0 NOT NULL COMMENT '删除标识，1-已删除；0-未删除',
  KEY `idx_dept_parent_id` (`parent_id`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

drop table if exists `auth_dept_user`;
CREATE TABLE `ibothub-auth`.`auth_dept_user` (
	id BIGINT auto_increment NOT NULL primary key,
	user_id BIGINT NULL COMMENT '用户id',
	dept_id BIGINT NULL COMMENT '部门id',
	KEY `idx_deptuser_id` (`user_id`, `dept_id`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

drop table if exists `auth_role`;
CREATE TABLE `ibothub-auth`.`auth_role` (
	id BIGINT auto_increment NOT NULL primary key,
	name varchar(200) NULL COMMENT '名称',
	key_ varchar(200) NULL COMMENT '标识',
	remark varchar(1000) NULL COMMENT '备注',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	creator varchar(100) NULL COMMENT '创建人',
	modify_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	modifier varchar(100) NULL COMMENT '修改人',
  del_flag INT DEFAULT 0 NOT NULL COMMENT '删除标识，1-已删除；0-未删除'
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

drop table if exists `auth_user_role`;
CREATE TABLE `ibothub-auth`.`auth_user_role` (
	id BIGINT auto_increment NOT NULL primary key,
	user_id BIGINT NULL COMMENT '用户id',
	role_id BIGINT NULL COMMENT '角色id',
	KEY `idx_userrole_id` (`user_id`, `role_id`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

drop table if exists `auth_permission`;
CREATE TABLE `ibothub-auth`.`auth_permission` (
	id BIGINT auto_increment NOT NULL primary key,
	title varchar(200) NULL COMMENT '中文名称',
	name varchar(200) NULL COMMENT '英文名称',
	key_ varchar(200) NULL COMMENT '标识',
	uri_ varchar(200) NULL COMMENT '路径',
  icon_font varchar(200) NULL COMMENT '图标',
  parent_id BIGINT NULL COMMENT '上级权限id',
  order_ INT NULL COMMENT '排序',
	remark varchar(1000) NULL COMMENT '备注',
	summary varchar(200) NULL COMMENT '描述',
	type_ varchar(2000) NULL COMMENT '一级菜单 CATEGORY / 二级或三级..菜单 MODULE / 按钮 BUTTON',
	redirect_ varchar(1000) NULL COMMENT '跳转路径',
	component varchar(1000) NULL COMMENT '组件',
	is_menu INT DEFAULT 0 COMMENT '是否为菜单，1-是；0-否',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	creator varchar(100) NULL COMMENT '创建人',
	modify_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	modifier varchar(100) NULL COMMENT '修改人',
  del_flag INT DEFAULT 0 NOT NULL COMMENT '删除标识，1-已删除；0-未删除',
  KEY `idx_perm_parent_id` (`parent_id`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

drop table if exists `auth_role_permission`;
CREATE TABLE `ibothub-auth`.`auth_role_permission` (
	id BIGINT auto_increment NOT NULL primary key,
	role_id BIGINT NULL COMMENT '角色id',
	permission_id BIGINT NULL COMMENT '权限id',
	KEY `idx_roleperm_id` (`role_id`, `permission_id`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

drop table if exists `sys_conf`;
CREATE TABLE `ibothub-auth`.`sys_conf` (
	id BIGINT auto_increment NOT NULL primary key,
	code varchar(200) NULL COMMENT 'Code',
	value varchar(2000) NULL COMMENT '值',
	default_value varchar(2000) NULL COMMENT '默认值',
	name varchar(1000) NULL COMMENT '名称',
	type_ int NULL COMMENT '类型：0-字符串（默认）；1-数字；2-日期；9-图片',
	remark varchar(1000) NULL COMMENT '备注',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	creator varchar(100) NULL COMMENT '创建人',
	modify_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	modifier varchar(100) NULL COMMENT '修改人',
  del_flag INT DEFAULT 0 NOT NULL COMMENT '删除标识，1-已删除；0-未删除'
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

drop table if exists `sys_conf_extra`;
CREATE TABLE `ibothub-auth`.`sys_conf_extra` (
	id BIGINT auto_increment NOT NULL primary key,
	conf_id BIGINT NULL COMMENT 'conf id',
	content blob NULL COMMENT '内容'
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

BEGIN;
REPLACE INTO `auth_user`(`id`, `username`, `username_cn`, `password`, `job_number`, `sex`, `email`, `phone`, `birthday`, `id_card`) VALUES (99999, 'admin', '系统管理员', '21232f297a57a5a743894a0e4a801fc3', '9007', 0, 'admin@ibothub.com', '18526535296', '1992-10-01 00:00:00', '421181199210019007');
REPLACE INTO `auth_user`(`username`, `username_cn`, `password`, `job_number`, `sex`, `email`, `phone`, `birthday`, `id_card`) VALUES ('zhangsan', '张三', 'e10adc3949ba59abbe56e057f20f883e', '9527', 0, 'zhangsan@ibothub.com', '18526535896', '1992-10-01 00:00:00', '421181199210015698');
REPLACE INTO `auth_user`(`username`, `username_cn`, `password`, `job_number`, `sex`, `email`, `phone`, `birthday`, `id_card`) VALUES ('lisi', '李四', 'e10adc3949ba59abbe56e057f20f883e', '9528', 0, 'lisi@ibothub.com', '18526535896', '1992-10-01 00:00:00', '421181199211015698');
REPLACE INTO `auth_user`(`username`, `username_cn`, `password`, `job_number`, `sex`, `email`, `phone`, `birthday`, `id_card`) VALUES ('wangwu', '王五', 'e10adc3949ba59abbe56e057f20f883e', '9529', 1, 'wangwu@ibothub.com', '18526535896', '1992-10-01 00:00:00', '422181199210015698');
REPLACE INTO `auth_user`(`username`, `username_cn`, `password`, `job_number`, `sex`, `email`, `phone`, `birthday`, `id_card`) VALUES ('zhaoliu', '赵六', 'e10adc3949ba59abbe56e057f20f883e', '9530', 1, 'zhaoliu@ibothub.com', '18526535896', '1992-10-01 00:00:00', '422181198210015698');
REPLACE INTO `auth_user`(`username`, `username_cn`, `password`, `job_number`, `sex`, `email`, `phone`, `birthday`, `id_card`) VALUES ('caocao', '曹操', 'e10adc3949ba59abbe56e057f20f883e', '9531', 1, 'caocao@ibothub.com', '15523802657', '1992-10-01 00:00:00', '130701199310302288');
REPLACE INTO `auth_user`(`username`, `username_cn`, `password`, `job_number`, `sex`, `email`, `phone`, `birthday`, `id_card`) VALUES ('zhugeliang', '诸葛亮', 'e10adc3949ba59abbe56e057f20f883e', '9532', 1, 'zhugeliang@ibothub.com', '15673358697', '1992-10-01 00:00:00', '130205197804195709');
REPLACE INTO `auth_user`(`username`, `username_cn`, `password`, `job_number`, `sex`, `email`, `phone`, `birthday`, `id_card`) VALUES ('guanyu', '关羽', 'e10adc3949ba59abbe56e057f20f883e', '9533', 1, 'guanyu@ibothub.com', '18526535896', '1992-10-01 00:00:00', '430811196802133607');
REPLACE INTO `auth_user`(`username`, `username_cn`, `password`, `job_number`, `sex`, `email`, `phone`, `birthday`, `id_card`) VALUES ('zhangfei', '张飞', 'e10adc3949ba59abbe56e057f20f883e', '9534', 1, 'zhangfei@ibothub.com', '13901251117', '1992-10-01 00:00:00', '220524199207278721');
REPLACE INTO `auth_user`(`username`, `username_cn`, `password`, `job_number`, `sex`, `email`, `phone`, `birthday`, `id_card`) VALUES ('zhaoyun', '赵云', 'e10adc3949ba59abbe56e057f20f883e', '9535', 1, 'zhaoyun@ibothub.com', '13810012563', '1992-10-01 00:00:00', '341004199809039969');
REPLACE INTO `auth_user`(`username`, `username_cn`, `password`, `job_number`, `sex`, `email`, `phone`, `birthday`, `id_card`) VALUES ('liubei', '刘备', 'e10adc3949ba59abbe56e057f20f883e', '9536', 1, 'liubei@ibothub.com', '15745263845', '1992-10-01 00:00:00', '310105197002143948');
REPLACE INTO `auth_user`(`username`, `username_cn`, `password`, `job_number`, `sex`, `email`, `phone`, `birthday`, `id_card`) VALUES ('machao', '马超', 'e10adc3949ba59abbe56e057f20f883e', '9537', 1, 'machao@ibothub.com', '13852456987', '1992-10-01 00:00:00', '370211196512112286');
REPLACE INTO `auth_user`(`username`, `username_cn`, `password`, `job_number`, `sex`, `email`, `phone`, `birthday`, `id_card`) VALUES ('huangzhong', '黄忠', 'e10adc3949ba59abbe56e057f20f883e', '9538', 1, 'huangzhong@ibothub.com', '13952489654', '1992-10-01 00:00:00', '422181198210015698');
REPLACE INTO `auth_user`(`username`, `username_cn`, `password`, `job_number`, `sex`, `email`, `phone`, `birthday`, `id_card`) VALUES ('lvbu', '吕布', 'e10adc3949ba59abbe56e057f20f883e', '9539', 1, 'lvbu@ibothub.com', '15214526985', '1992-10-01 00:00:00', '350800197804195567');
REPLACE INTO `auth_user`(`username`, `username_cn`, `password`, `job_number`, `sex`, `email`, `phone`, `birthday`, `id_card`) VALUES ('diaochan', '貂蝉', 'e10adc3949ba59abbe56e057f20f883e', '9540', 0, 'diaochan@ibothub.com', '18852639452', '1992-10-01 00:00:00', '654200196211102422');

REPLACE INTO `auth_role`(`id`, `name`, `key_`) VALUES (99999, '系统管理员', 'ROLE_ADMIN');
REPLACE INTO `auth_role`(`id`, `name`, `key_`) VALUES (88888, '管理员', 'ROLE_MAINTAINER');
REPLACE INTO `auth_role`(`name`, `key_`) VALUES ('开发者', 'ROLE_DEVELOPER');
REPLACE INTO `auth_role`(`name`, `key_`) VALUES ('查阅者', 'ROLE_REPORTER');
REPLACE INTO `auth_role`(`name`, `key_`) VALUES ('匿名者', 'ROLE_ANONYMOUS');
REPLACE INTO `auth_role`(`name`, `key_`) VALUES ('普通用户', 'ROLE_USER');

REPLACE INTO `auth_user_role`(`user_id`, `role_id`) VALUES ((select id from auth_user where username='admin'), (select id from auth_role where key_='ROLE_ADMIN'));

REPLACE INTO `auth_dept`(`id`, `name`, `key_`) VALUES (99999, '正欣集团', 'ROOT_COMPANY');

REPLACE INTO `auth_dept_user`(`user_id`, `dept_id`) VALUES ((select id from auth_user where username='admin'), (select id from auth_dept where key_='ROOT_COMPANY'));

REPLACE INTO `auth_permission`(`id`, `title`, `name`, `key_`, `uri_`, `icon_font`, `parent_id`, `order_`, `is_menu`, `redirect_`, `component`) VALUES (1, '首页', 'Dashboard', 'dashboard', 'dashboard', 'dashboard', null, 0, 1, null, null);
REPLACE INTO `auth_permission`(`id`, `title`, `name`, `key_`, `uri_`, `icon_font`, `parent_id`, `order_`, `is_menu`, `redirect_`, `component`) VALUES (9, '系统管理', 'System', 'system', 'system', 'el-icon-s-help', null, 1, 1, 'system/user', 'Layout');
REPLACE INTO `auth_permission`(`id`, `title`, `name`, `key_`, `uri_`, `icon_font`, `parent_id`, `order_`, `is_menu`, `redirect_`, `component`) VALUES (11, '用户管理', 'UserMgr', 'user_mgr', 'system/user', 'el-icon-user', 9, 11, 1, null, null);
REPLACE INTO `auth_permission`(`id`, `title`, `name`, `key_`, `uri_`, `icon_font`, `parent_id`, `order_`, `is_menu`, `redirect_`, `component`) VALUES (12, '部门管理', 'DeptMgr', 'dept_mgr', 'system/dept', 'el-icon-office-building', 9, 12, 1, null, null);
REPLACE INTO `auth_permission`(`id`, `title`, `name`, `key_`, `uri_`, `icon_font`, `parent_id`, `order_`, `is_menu`, `redirect_`, `component`) VALUES (13, '角色管理', 'RoleMgr', 'role_mgr', 'system/role', 'el-icon-medal-1', 9, 13, 1, null, null);
REPLACE INTO `auth_permission`(`id`, `title`, `name`, `key_`, `uri_`, `icon_font`, `parent_id`, `order_`, `is_menu`, `redirect_`, `component`) VALUES (14, '权限管理', 'PermMgr', 'perm_mgr', 'system/perm', 'el-icon-lock', 9, 14, 1, null, null);
REPLACE INTO `auth_permission`(`id`, `title`, `name`, `key_`, `uri_`, `icon_font`, `parent_id`, `order_`, `is_menu`, `redirect_`, `component`) VALUES (15, '系统设置', 'SysConf', 'sys_conf', 'system/conf', 'el-icon-setting', 9, 15, 1, null, null);

REPLACE INTO `auth_role_permission`(role_id, permission_id) VALUES (99999, 1);
REPLACE INTO `auth_role_permission`(role_id, permission_id) VALUES (99999, 9);
REPLACE INTO `auth_role_permission`(role_id, permission_id) VALUES (99999, 11);
REPLACE INTO `auth_role_permission`(role_id, permission_id) VALUES (99999, 12);
REPLACE INTO `auth_role_permission`(role_id, permission_id) VALUES (99999, 13);
REPLACE INTO `auth_role_permission`(role_id, permission_id) VALUES (99999, 14);
REPLACE INTO `auth_role_permission`(role_id, permission_id) VALUES (99999, 15);

REPLACE INTO `sys_conf`(`id`, `code`, `value`, `default_value`, `name`, `remark`, `type_`) VALUES (1, 'title', '正欣会计', '正欣会计', '系统标题', NULL, 0);
REPLACE INTO `sys_conf`(`id`, `code`, `value`, `default_value`, `name`, `remark`, `type_`) VALUES (2, 'loginTitle', '正欣会计', '正欣会计', '登录页标题', NULL, 0);
REPLACE INTO `sys_conf`(`id`, `code`, `value`, `default_value`, `name`, `remark`, `type_`) VALUES (3, 'loginLogo', NULL, NULL, '登录页 Logo', NULL, 9);
REPLACE INTO `sys_conf`(`id`, `code`, `value`, `default_value`, `name`, `remark`, `type_`) VALUES (4, 'bannerTitle', '正欣会计', '正欣会计', 'Banner标题', NULL, 0);
REPLACE INTO `sys_conf`(`id`, `code`, `value`, `default_value`, `name`, `remark`, `type_`) VALUES (5, 'bannerLogo', NULL, NULL, 'Banner Logo', NULL, 9);
REPLACE INTO `sys_conf`(`id`, `code`, `value`, `default_value`, `name`, `remark`, `type_`) VALUES (6, 'favTitle', '正欣会计', '正欣会计', '地址栏标题', NULL, 0);
REPLACE INTO `sys_conf`(`id`, `code`, `value`, `default_value`, `name`, `remark`, `type_`) VALUES (7, 'favIcon', NULL, NULL, '地址栏Icon', NULL, 9);

COMMIT;