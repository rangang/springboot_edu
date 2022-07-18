
CREATE DATABASE /*!32312 IF NOT EXISTS*/`edu_comment` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `edu_comment`;


DROP TABLE IF EXISTS `course_comment`;

CREATE TABLE `course_comment` (
  `id` BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` INT(11) NOT NULL COMMENT '课程id',
  `section_id` INT(11) NOT NULL COMMENT '章节id',
  `lesson_id` INT(11) NOT NULL COMMENT '课时id',
  `user_id` INT(11) DEFAULT NULL COMMENT '用户id',
  `user_name` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '运营设置用户昵称',
  `parent_id` INT(11) DEFAULT NULL COMMENT '父级评论id',
  `is_top` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否置顶：0不置顶，1置顶',
  `comment` TEXT COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论',
  `like_count` INT(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `is_reply` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否回复留言：0普通留言，1回复留言',
  `type` INT(1) NOT NULL DEFAULT '0' COMMENT '留言类型：0用户留言，1讲师留言，2运营马甲 3讲师回复 4小编回复 5官方客服回复',
  `status` INT(1) NOT NULL DEFAULT '0' COMMENT '留言状态：0待审核，1审核通过，2审核不通过，3已删除',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `is_del` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `last_operator` INT(11) NOT NULL COMMENT '最后操作者id',
  `is_notify` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '是否发送了通知,1表示未发出，0表示已发出',  
  `mark_belong` TINYINT(1) DEFAULT '0' COMMENT '标记归属',
  `replied` TINYINT(1) DEFAULT '0' COMMENT '回复状态 0 未回复 1 已回复',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_1` (`course_id`,`lesson_id`,`status`) USING BTREE,
  KEY `idx_3` (`parent_id`,`status`,`is_top`,`like_count`) USING BTREE,
  KEY `idx_course_id_create_time_status_replied_mark_belong` (`course_id`,`status`,`create_time`,`mark_belong`,`replied`) USING BTREE,
  KEY `idx_courseId_status_parentId` (`course_id`,`parent_id`,`status`) USING BTREE
) ENGINE=INNODB AUTO_INCREMENT=444 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程留言表';
/*Data for the table `course_comment` */


/*Table structure for table `course_comment_favorite_record` */

DROP TABLE IF EXISTS `course_comment_favorite_record`;

CREATE TABLE `course_comment_favorite_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户评论点赞j记录ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `comment_id` int(11) NOT NULL COMMENT '用户评论ID',
  `is_del` tinyint(1) NOT NULL COMMENT '是否删除，0：未删除（已赞），1：已删除（取消赞状态）',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_userId_commentId` (`user_id`,`comment_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=285 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程留言点赞表';

/*Data for the table `course_comment_favorite_record` */
