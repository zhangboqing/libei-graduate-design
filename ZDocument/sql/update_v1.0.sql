#========================================================李贝毕业设计 新增表结构=============================================
-- 创建数据库
DROP DATABASE xinx;
CREATE DATABASE xinx;

-- 选择数据库
USE xinx;

-- 建表
DROP TABLE IF EXISTS `lb_login_user`;
CREATE TABLE `lb_login_user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '登录用户名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '0 普通用户 1 管理员',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='登录用户表';

DROP TABLE IF EXISTS `lb_user_info`;
CREATE TABLE `lb_user_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `stu_name` varchar(50) NOT NULL DEFAULT ' ' COMMENT '用户姓名',
  `stu_no` varchar(100) NOT NULL DEFAULT ' ' COMMENT '学号',
  `phone` varchar(20) NOT NULL DEFAULT ' ' COMMENT '手机号',
  `age` varchar(11) NOT NULL DEFAULT '0' COMMENT '年龄',
  `sex` int(11) NOT NULL DEFAULT '0' COMMENT '0  男 1 女',
  `class_info` varchar(100) NOT NULL DEFAULT ' ' COMMENT '专业及班级信息',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户信息表';

DROP TABLE IF EXISTS `lb_meeting_room`;
CREATE TABLE `lb_meeting_room` (
  `room_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `room_no` int(11) NOT NULL COMMENT '房间号',
  `room_can_in_number` int(11) DEFAULT '0' COMMENT '能够容纳的人数',
  `room_facility` varchar(100) NOT NULL DEFAULT ' ' COMMENT '房间设备',
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='会议室表';

DROP TABLE IF EXISTS `lb_meeting_room_reserve_record`;
CREATE TABLE `lb_meeting_room_reserve_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `room_id` int(11) NOT NULL COMMENT '会议室id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `reserve_start_time` bigint(11) NOT NULL COMMENT '预约开始时间',
  `reserve_end_time` bigint(11) NOT NULL COMMENT '预约结束时间',
  `in_num` int(11) NOT NULL DEFAULT '0' COMMENT '参与人数',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0 待审核 1 审核不通过 2 审核通过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='会议室预定记录表';


DROP TABLE IF EXISTS `lb_notice`;
CREATE TABLE `lb_notice` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(1024) NOT NULL DEFAULT '' COMMENT '公告内容',
  `release_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='发布公告';


-- 初始化数据
-- 新增管理员账号
INSERT INTO `lb_login_user` (`user_id`, `user_name`, `password`, `type`)
VALUES
	(1, 'admin', '123', 1);







