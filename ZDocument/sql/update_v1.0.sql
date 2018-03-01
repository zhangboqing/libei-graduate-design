#========================================================李贝毕业设计 新增表结构=============================================
DROP TABLE IF EXISTS `lb_login_user`;
CREATE TABLE `lb_login_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '登录用户名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录用户表';

CREATE TABLE `lb_member` (
  `member_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `identity` varchar(30) NOT NULL DEFAULT '' COMMENT '身份',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户成员表';


CREATE TABLE `lb_meeting_room` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `room_no` int(11) NOT NULL COMMENT '房间号',
  `is_reserve` int(11) NOT NULL DEFAULT '0' COMMENT '0 空闲  1 已预定',
  `member_id` int(11) NOT NULL DEFAULT '0' COMMENT '预定成员id ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会议室表';





