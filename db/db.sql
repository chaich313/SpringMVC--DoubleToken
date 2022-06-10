/*
 Navicat Premium Data Transfer

 Source Server         : itranlin
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Schema         : basic

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 09/01/2021 17:38:02
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
    `username`    varchar(255) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `password`    varchar(255) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `avatar`      varchar(255) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `real_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `type`        varchar(255) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `create_time` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user`
VALUES ('1', 'admin', 'b9d11b3be25f5a1a7dc8ca04cd310b28', NULL, NULL, NULL);
COMMIT;


-- ----------------------------
-- Table structure for dic
-- ----------------------------
DROP TABLE IF EXISTS `dic`;
CREATE TABLE `dic`
(
    `id`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `name`         varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '字段名',
    `parent_id`    varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '父级id',
    `parent_ids`   varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父级关系',
    `create_time`  bigint(20) DEFAULT NULL,
    `deleted`      tinyint(2) DEFAULT NULL,
    `code`         varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL,
    `the_sort`     int(10) DEFAULT NULL,
    `allow_delete` tinyint(2) DEFAULT NULL COMMENT '是否允许被删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dic
-- ----------------------------
BEGIN;
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('001000', '景点标签', NULL, NULL, NULL, 0, 'JDBQ', NULL, 0);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('001001', '5A景点', '001000', '001000', NULL, 0, 'JDBQ-1', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('001002', '博物馆', '001000', '001000', NULL, 0, 'JDBQ-2', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('001003', '古代建筑', '001000', '001000', NULL, 0, 'JDBQ-3', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('001004', '雪山奇景', '001000', '001000', NULL, 0, 'JDBQ-4', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('001005', '海景风光', '001000', '001000', NULL, 0, 'JDBQ-5', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('002000', '国家标签', NULL, NULL, NULL, 0, 'GJBQ', NULL, 0);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('002001', '亚洲', '002000', '002000', NULL, 0, 'GJBQ-1', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('002002', '欧洲', '002000', '002000', NULL, 0, 'GJBQ-2', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('002003', '非洲', '002000', '002000', NULL, 0, 'GJBQ-3', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('002004', '大洋洲', '002000', '002000', NULL, 0, 'GJBQ-4', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('002005', '南美洲', '002000', '002000', NULL, 0, 'GJBQ-5', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('002006', '北美洲', '002000', '002000', NULL, 0, 'GJBQ-6', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('002007', '南极洲', '002000', '002000', NULL, 0, 'GJBQ-7', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('003000', '城市标签', NULL, NULL, NULL, 0, 'CSBQ', NULL, 0);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('003001', '历史古都', '003000', '003000', NULL, 0, 'CSBQ-1', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('003002', '风光胜地', '003000', '003000', NULL, 0, 'CSBQ-2', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('003003', '海边风情', '003000', '003000', NULL, 0, 'CSBQ-3', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('003004', '繁华都市', '003000', '003000', NULL, 0, 'CSBQ-4', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('003005', '亲子休闲', '003000', '003000', NULL, 0, 'CSBQ-5', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('003006', '美食之都', '003000', '003000', NULL, 0, 'CSBQ-6', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1527575916195201026', '异域风情', '003000', 'null/003000', 1653037454064, 0, 'CSBQ-10', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1527576092506963970', '异域风情', '003000', 'null/003000', 1653037496101, 1, 'CSBQ-9', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1528228944027959297', 'kkkkkkkkk', '002000', 'null/002000', 1653193148032, 1, 'kkkkkk', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1528274097576984577', '风景区', '003001', 'null/003001', 1653203913478, 1, '509862', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1528980748210601985', '国家中心', '003000', 'null/003000', 1653372392116, 0, 'CSBQ-8', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1529124227519627266', '无无', '002000', 'null/002000', 1653406600249, 1, 'GJBQ1', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1529124378023837697', '无无1', '1529124227519627266', 'null/1529124227519627266', 1653406636132, 1, 'GJBQ-1-1',
        NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1529125203756466177', '无无', '003000', 'null/003000', 1653406833002, 1, 'ABCDK', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1529364153330933762', '3A景区', '001000', 'null/001000', 1653463803017, 0, 'JDBQ-6', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1529364435490152449', '4A景区', '001000', 'null/001000', 1653463870291, 0, 'JDBQ-7', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1529364620207300609', '人气景点', '001000', 'null/001000', 1653463914331, 0, 'JDBQ-8', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1529364927763030017', '历史古都', '003000', 'null/003000', 1653463987658, 1, 'CSBQ-7', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1529365262233608194', '无垠大漠', '003000', 'null/003000', 1653464067403, 0, 'CSBQ-9', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1529365488608583682', '旅游城市', '003000', 'null/003000', 1653464121374, 0, 'CSBQ-11', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1529365587678044162', '戈壁大漠', '001000', 'null/001000', 1653464144995, 0, 'JDBQ-10', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1530554696303968258', '森林公园', '001000', 'null/001000', 1653747650586, 0, 'JDBQ-13', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1530555793450008577', '名山大川', '001000', 'null/001000', 1653747912166, 0, 'JDBQ-11', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1530556282329694209', '主题公园', '001000', 'null/001000', 1653748028724, 0, 'JDBQ-12', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1530557397569961985', '江河湖泊', '001000', 'null/001000', 1653748294618, 0, 'JDBQ-14', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1530558059640848385', '网红打卡', '001000', 'null/001000', 1653748452469, 1, 'JDBQ-16', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1530558270060691458', '著名地标', '001000', 'null/001000', 1653748502636, 0, 'JDBQ-15', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1530558448171810818', '小众推荐', '001000', 'null/001000', 1653748545101, 0, 'JDBQ-17', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1530561398860480513', '名校校园', '001000', 'null/001000', 1653749248600, 0, 'JDBQ-18', NULL, 1);
INSERT INTO `dic` (`id`, `name`, `parent_id`, `parent_ids`, `create_time`, `deleted`, `code`, `the_sort`,
                   `allow_delete`)
VALUES ('1530562081143717889', '苍茫草原', '001000', 'null/001000', 1653749411270, 0, 'JDBQ-19', NULL, 1);
COMMIT;

SET
FOREIGN_KEY_CHECKS = 1;
