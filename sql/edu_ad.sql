
CREATE DATABASE /*!32312 IF NOT EXISTS*/`edu_ad` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `edu_ad`;

DROP TABLE IF EXISTS `promotion_space`;

CREATE TABLE `promotion_space` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `space_key` varchar(255) DEFAULT NULL COMMENT '广告位key',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_del` int(2) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `promotion_space_key_isDel` (`space_key`,`is_del`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告区域表';

/*Data for the table `promotion_space` */

insert  into `promotion_space`(`id`,`name`,`space_key`,`create_time`,`update_time`,`is_del`) values 
(1,'首页顶部推荐位','666','2020-07-14 13:03:31','2020-07-17 13:13:21',0),
(2,'首页侧边广告位','888','2020-07-14 13:03:31','2020-07-17 11:53:02',0),
(3,'首页顶部轮播','999','2020-07-14 13:03:31','2020-07-17 13:13:03',0);

/*Table structure for table `promotion_ad` */

DROP TABLE IF EXISTS `promotion_ad`;

CREATE TABLE `promotion_ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '广告名',
  `space_id` int(11) DEFAULT NULL COMMENT '广告位id',
  `keyword` varchar(255) DEFAULT NULL COMMENT '精确搜索关键词',
  `html_content` text COMMENT '静态广告的内容',
  `text` varchar(255) DEFAULT NULL COMMENT '文字一',
  `link` varchar(255) DEFAULT NULL COMMENT '链接一',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT '0',
  `priority` int(4) DEFAULT '0' COMMENT '优先级',
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `promotion_ad_SEG` (`space_id`,`start_time`,`end_time`,`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1093 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告表';

/*Data for the table `promotion_ad` */

insert  into `promotion_ad`(`id`,`name`,`space_id`,`keyword`,`html_content`,`text`,`link`,`start_time`,`end_time`,`create_time`,`update_time`,`status`,`priority`,`img`) values 
(1074,'java基础训练营',1,NULL,NULL,'sdfsadf','http://edufront.lagou.com/#/content?courseId=1','2020-06-23 17:03:27','2020-08-30 17:03:45','2020-07-14 14:28:34','2020-08-17 17:06:54',1,0,'https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/07/17/15949583460826312.jpeg'),
(1075,'精选课程',1,NULL,NULL,NULL,'http://edufront.lagou.com/#/content?courseId=2','2020-06-29 17:03:25','2020-08-30 17:03:45','2020-07-14 14:28:34','2020-07-17 13:13:51',1,0,'https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2021/01/22/16112835332492876.jpg'),
(1076,'java金牌训练营',1,NULL,NULL,NULL,'http://edufront.lagou.com/#/content?courseId=3','2020-06-29 17:03:25','2020-08-30 17:03:45','2020-07-14 14:28:34','2020-07-17 13:14:11',1,0,'http://dabaoku.com/sucaidatu/zhonghua/shanshuihua/072472.jpg'),
(1077,'轮播广告',1,NULL,NULL,NULL,'http://edufront.lagou.com/#/content?courseId=4','2020-06-29 17:03:25','2020-08-31 17:03:45','2020-07-14 14:28:34','2020-07-17 13:07:52',1,0,'https://oscimg.oschina.net/oscnet/260b6ff2c7ed9e2ba69f66f8a3c6f440ce0.png'),
(1078,'广告1',162,NULL,NULL,'这是文本内容111','http://www.163.com111','2020-01-01 00:00:00','2020-09-30 00:00:00','2020-07-14 14:28:34','2020-07-17 11:22:31',0,2,'https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/07/17/15949561472241579.jpg');
