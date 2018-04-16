/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : rov_ros

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-16 23:08:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rov
-- ----------------------------
DROP TABLE IF EXISTS `rov`;
CREATE TABLE `rov` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roll` double(5,2) DEFAULT NULL,
  `yaw` double(5,2) DEFAULT NULL,
  `pitch` double(5,2) DEFAULT NULL,
  `depth` double(5,2) DEFAULT NULL,
  `speed` double(5,2) DEFAULT NULL,
  `times` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
