/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50549
 Source Host           : 127.0.0.1:3306
 Source Schema         : atcrowdfunding

 Target Server Type    : MySQL
 Target Server Version : 50549
 File Encoding         : 65001

 Date: 01/03/2019 19:35:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL COMMENT '名称',
  `pid` int(11) DEFAULT NULL COMMENT '自己关联自己',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL COMMENT '路径',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, '系统菜单', 0, NULL, NULL);
INSERT INTO `t_permission` VALUES (2, '控制面板', 1, '', 'glyphicon glyphicon-dashboard');
INSERT INTO `t_permission` VALUES (3, '权限管理', 1, NULL, 'glyphicon glyphicon glyphicon-tasks');
INSERT INTO `t_permission` VALUES (4, '用户维护', 3, '/user/index', 'glyphicon glyphicon-user');
INSERT INTO `t_permission` VALUES (5, '角色维护', 3, '/role/index', 'glyphicon glyphicon-king');
INSERT INTO `t_permission` VALUES (6, '许可维护', 3, '/permission/index', 'glyphicon glyphicon-lock');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'PM-项目经理');
INSERT INTO `t_role` VALUES (2, 'SE-软件工程师');
INSERT INTO `t_role` VALUES (3, 'PG-程序员');
INSERT INTO `t_role` VALUES (4, 'TL-组长');
INSERT INTO `t_role` VALUES (5, 'GL-组长');
INSERT INTO `t_role` VALUES (6, 'QC-品质控制');
INSERT INTO `t_role` VALUES (7, 'SA-软件架构师');
INSERT INTO `t_role` VALUES (8, 'CMO/CMS-配置管理');
INSERT INTO `t_role` VALUES (9, 'SYSTEM-系统管理员');
INSERT INTO `t_role` VALUES (11, 'admin-超级管理员');
INSERT INTO `t_role` VALUES (12, 'admin');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) DEFAULT NULL,
  `permissionid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `roleid`(`roleid`) USING BTREE,
  INDEX `permissionid`(`permissionid`) USING BTREE,
  CONSTRAINT `t_role_permission_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_role_permission_ibfk_2` FOREIGN KEY (`permissionid`) REFERENCES `t_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (10, 1, 1);
INSERT INTO `t_role_permission` VALUES (11, 1, 2);
INSERT INTO `t_role_permission` VALUES (12, 1, 3);
INSERT INTO `t_role_permission` VALUES (13, 1, 4);
INSERT INTO `t_role_permission` VALUES (14, 1, 5);
INSERT INTO `t_role_permission` VALUES (15, 1, 6);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `loginacct` varchar(50) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `userpswd` varchar(50) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `createtime` char(20) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'admin', 'admin', 'admin@test.com', NULL);
INSERT INTO `t_user` VALUES (2, 'wangwu', 'wangwu', 'wangwu', 'wangwu@test.com', NULL);
INSERT INTO `t_user` VALUES (3, 'lisi', 'lisi', 'lisi', 'lisi@test.com', NULL);
INSERT INTO `t_user` VALUES (4, 'zhangsan', 'zhangsan', 'zhangsan', 'zhangsan@test.com', NULL);
INSERT INTO `t_user` VALUES (5, 'suancai', 'suancai', 'suancai', 'suancai@test.com', NULL);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  INDEX `roleid`(`roleid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (7, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
