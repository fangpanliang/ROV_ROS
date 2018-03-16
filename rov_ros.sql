/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : rov_ros

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-15 22:57:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rov_ip` varchar(255) DEFAULT NULL,
  `cam_ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for keyboard
-- ----------------------------
DROP TABLE IF EXISTS `keyboard`;
CREATE TABLE `keyboard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `UporDown` double(5,2) DEFAULT NULL,
  `LeftorRight` double(5,2) DEFAULT NULL,
  `ForwardorBackward` double(5,2) DEFAULT NULL,
  `ClimborDive` double(5,2) DEFAULT NULL,
  `times` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for rov
-- ----------------------------
DROP TABLE IF EXISTS `rov`;
CREATE TABLE `rov` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `RobotYaw` double(5,2) DEFAULT NULL,
  `RobotDepth` double(5,2) DEFAULT NULL,
  `times` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
