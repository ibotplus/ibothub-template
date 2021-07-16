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
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	creator varchar(100) NULL COMMENT '创建人',
	modify_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	modifier varchar(100) NULL COMMENT '修改人',
	del_flag INT DEFAULT 0 NOT NULL COMMENT '删除标识，1-已删除；0-未删除'
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
  del_flag INT DEFAULT 0 NOT NULL COMMENT '删除标识，1-已删除；0-未删除'
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
	role_id BIGINT NULL COMMENT '角色id'
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

drop table if exists `auth_permission`;
CREATE TABLE `ibothub-auth`.`auth_permission` (
	id BIGINT auto_increment NOT NULL primary key,
	name varchar(200) NULL COMMENT '名称',
	key_ varchar(200) NULL COMMENT '标识',
	uri_ varchar(200) NULL COMMENT '路径',
  icon_font varchar(200) NULL COMMENT '图标',
  parent_id BIGINT NULL COMMENT '上级权限id',
  order_ INT NULL COMMENT '排序',
	remark varchar(1000) NULL COMMENT '备注',
	summary varchar(200) NULL COMMENT '描述',
	type_ varchar(2000) NULL COMMENT '一级菜单 CATEGORY / 二级或三级..菜单 MODULE / 按钮 BUTTON',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	creator varchar(100) NULL COMMENT '创建人',
	modify_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	modifier varchar(100) NULL COMMENT '修改人',
    del_flag INT DEFAULT 0 NOT NULL COMMENT '删除标识，1-已删除；0-未删除'
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

drop table if exists `auth_role_permission`;
CREATE TABLE `ibothub-auth`.`auth_role_permission` (
	id BIGINT auto_increment NOT NULL primary key,
	role_id BIGINT NULL COMMENT '角色id',
	permission_id BIGINT NULL COMMENT '权限id'
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

REPLACE INTO `auth_role`(`id`, `name`, `key_`) VALUES (99999, '系统管理员', 'ROLE_ADMIN');
REPLACE INTO `auth_role`(`id`, `name`, `key_`) VALUES (88888, '管理员', 'ROLE_MAINTAINER');
REPLACE INTO `auth_role`(`name`, `key_`) VALUES ('开发者', 'ROLE_DEVELOPER');
REPLACE INTO `auth_role`(`name`, `key_`) VALUES ('查阅者', 'ROLE_REPORTER');
REPLACE INTO `auth_role`(`name`, `key_`) VALUES ('匿名者', 'ROLE_ANONYMOUS');
REPLACE INTO `auth_role`(`name`, `key_`) VALUES ('普通用户', 'ROLE_USER');

REPLACE INTO `auth_user_role`(`user_id`, `role_id`) VALUES ((select id from auth_user where username='admin'), (select id from auth_role where key_='ROLE_ADMIN'));

REPLACE INTO `auth_dept`(`id`, `name`, `key_`) VALUES (99999, '正欣集团', 'ROOT_COMPANY');

REPLACE INTO `auth_dept_user`(`user_id`, `dept_id`) VALUES ((select id from auth_user where username='admin'), (select id from auth_dept where key_='ROOT_COMPANY'));

REPLACE INTO `auth_permission`(`id`, `name`, `key_`, `uri_`, `icon_font`, `parent_id`, `order_`) VALUES (1, '首页', 'dashboard', 'dashboard', '', null, 0);
REPLACE INTO `auth_permission`(`id`, `name`, `key_`, `uri_`, `icon_font`, `parent_id`, `order_`) VALUES (9, '系统管理', 'system', 'system', '', null, 1);
REPLACE INTO `auth_permission`(`id`, `name`, `key_`, `uri_`, `icon_font`, `parent_id`, `order_`) VALUES (11, '用户管理', 'user_mgr', 'sys/user', '', 9, 11);
REPLACE INTO `auth_permission`(`id`, `name`, `key_`, `uri_`, `icon_font`, `parent_id`, `order_`) VALUES (12, '部门管理', 'dept_mgr', 'sys/dept', '', 9, 12);
REPLACE INTO `auth_permission`(`id`, `name`, `key_`, `uri_`, `icon_font`, `parent_id`, `order_`) VALUES (13, '角色管理', 'role_mgr', 'sys/role', '', 9, 13);
REPLACE INTO `auth_permission`(`id`, `name`, `key_`, `uri_`, `icon_font`, `parent_id`, `order_`) VALUES (14, '权限管理', 'perm_mgr', 'sys/perm', '', 9, 14);
REPLACE INTO `auth_permission`(`id`, `name`, `key_`, `uri_`, `icon_font`, `parent_id`, `order_`) VALUES (15, '系统设置', 'sys_conf', 'sys/conf', '', 9, 15);


COMMIT;