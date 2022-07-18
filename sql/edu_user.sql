CREATE DATABASE /*!32312 IF NOT EXISTS*/`edu_user` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `edu_user`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(255) NOT NULL COMMENT '用户昵称',
  `portrait` varchar(255) DEFAULT NULL COMMENT '用户头像地址',
  `phone` varchar(255) NOT NULL COMMENT '注册手机',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码（可以为空，支持只用验证码注册、登录）',
  `reg_ip` varchar(255) DEFAULT NULL COMMENT '注册ip',
  `account_non_expired` bit(1) DEFAULT b'1' COMMENT '是否有效用户',
  `credentials_non_expired` bit(1) DEFAULT b'1' COMMENT '账号是否未过期',
  `account_non_locked` bit(1) DEFAULT b'1' COMMENT '是否未锁定',
  `status` varchar(20) NOT NULL DEFAULT 'ENABLE' COMMENT '用户状态：ENABLE能登录，DISABLE不能登录',
  `is_del` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_phone_is_del` (`phone`,`is_del`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100030024 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`portrait`,`phone`,`password`,`reg_ip`,`account_non_expired`,`credentials_non_expired`,`account_non_locked`,`status`,`is_del`,`create_time`,`update_time`) values 
(100030011,'往事如烟','https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/06/28/15933251448762927.png','110','123',NULL,'','','','DISABLE','','2020-07-10 10:19:15','2020-08-14 21:44:25'),
(100030017,'天高云淡',NULL,'119','222',NULL,'','','','DISABLE','','2020-07-10 11:25:45','2020-07-13 10:56:31'),
(100030018,'吕奉先',NULL,'120','333',NULL,'','','','ENABLE','\0','2020-07-10 12:17:35','2020-07-10 12:17:35'),
(100030019,'休止符','https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/07/10/15943594999396473.png','18201288771','444',NULL,'','','','ENABLE','\0','2020-07-10 12:20:16','2020-07-10 12:20:16');
