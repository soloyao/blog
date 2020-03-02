# Host: 127.0.0.1  (Version: 5.5.15)
# Date: 2020-03-02 14:50:17
# Generator: MySQL-Front 5.3  (Build 4.269)

/*!40101 SET NAMES gb2312 */;

#
# Structure for table "archives"
#

DROP TABLE IF EXISTS `archives`;
CREATE TABLE `archives` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `archiveName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Data for table "archives"
#

INSERT INTO `archives` VALUES (1,'2018��07��'),(2,'2018��08��'),(5,'2020��01��');

#
# Structure for table "article"
#

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleId` bigint(20) NOT NULL,
  `author` varchar(255) NOT NULL,
  `originalAuthor` varchar(255) NOT NULL,
  `articleTitle` varchar(255) NOT NULL,
  `articleContent` longtext NOT NULL,
  `articleTags` varchar(255) NOT NULL,
  `articleType` varchar(255) NOT NULL,
  `articleCategories` varchar(255) NOT NULL,
  `publishDate` varchar(255) NOT NULL,
  `updateDate` varchar(255) NOT NULL,
  `articleUrl` varchar(255) NOT NULL,
  `articleTabloid` text NOT NULL,
  `likes` int(11) NOT NULL,
  `lastArticleId` bigint(20) DEFAULT NULL,
  `nextArticleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

#
# Data for table "article"
#

INSERT INTO `article` VALUES (30,1578384244,'������','������','����','111','����,ԭ��','ԭ��','111','2020-01-07','2020-01-07','http://127.0.0.1:8811/article/1578384244','111...',1,0,1578903116),(31,1578903116,'������','������','����','123\n123\n123\n123\n123\n`xxxxxxxxxxxxxxxxxxxxx`','1111,ԭ��','ԭ��','�ҵĹ���','2020-01-13','2020-01-16','http://127.0.0.1:8811/article/1578903116','123123123123123xxxxxxxxxxxxxxxxxxxxx...',0,1578384244,0);

#
# Structure for table "article_likes_record"
#

DROP TABLE IF EXISTS `article_likes_record`;
CREATE TABLE `article_likes_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleId` bigint(20) NOT NULL,
  `likerId` int(11) NOT NULL,
  `likeDate` varchar(255) NOT NULL,
  `isRead` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

#
# Data for table "article_likes_record"
#

INSERT INTO `article_likes_record` VALUES (39,1578384244,2,'2020-01-08 13:31',0);

#
# Structure for table "categories"
#

DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Data for table "categories"
#

INSERT INTO `categories` VALUES (1,'�ҵĹ���'),(2,'SpringBoot'),(5,'111');

#
# Structure for table "comment_likes_record"
#

DROP TABLE IF EXISTS `comment_likes_record`;
CREATE TABLE `comment_likes_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleId` bigint(20) NOT NULL,
  `pId` int(11) NOT NULL,
  `likerId` int(11) NOT NULL,
  `likeDate` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "comment_likes_record"
#


#
# Structure for table "comment_record"
#

DROP TABLE IF EXISTS `comment_record`;
CREATE TABLE `comment_record` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `pId` bigint(20) NOT NULL,
  `articleId` bigint(20) NOT NULL,
  `answererId` int(11) NOT NULL,
  `respondentId` int(11) NOT NULL,
  `commentDate` varchar(255) NOT NULL,
  `likes` int(11) NOT NULL DEFAULT '0',
  `commentContent` text NOT NULL,
  `isRead` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

#
# Data for table "comment_record"
#

INSERT INTO `comment_record` VALUES (59,0,1578903116,2,2,'2020-01-13 16:12',0,'����������',0),(60,59,1578903116,2,2,'2020-01-13 16:12',0,'�¸¸¸¸¹�',0),(61,0,1578903116,2,2,'2020-02-09 12:14',0,'222',0);

#
# Structure for table "daily_speech"
#

DROP TABLE IF EXISTS `daily_speech`;
CREATE TABLE `daily_speech` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `mood` varchar(20) NOT NULL,
  `picsUrl` text,
  `publishDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "daily_speech"
#


#
# Structure for table "feedback"
#

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `feedbackContent` text NOT NULL,
  `contactInfo` varchar(255) DEFAULT NULL,
  `personId` int(11) NOT NULL,
  `feedbackDate` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "feedback"
#


#
# Structure for table "friendlink"
#

DROP TABLE IF EXISTS `friendlink`;
CREATE TABLE `friendlink` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blogger` varchar(40) NOT NULL,
  `url` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

#
# Data for table "friendlink"
#

INSERT INTO `friendlink` VALUES (1,'naget��С��','https://naget.github.io'),(2,'Li Pan\'s ����','http://www.lipan.xyz'),(3,'�����׸��˲���','http://www.csxll.top'),(4,'SAn Blog','https://sanii.cn'),(5,'�������ĳ���Գ','http://www.liuzhaopo.top'),(6,'Mr_���н�','https://www.zengzhongjie.com'),(7,'ȥ��CTO','https://www.qdcto.com'),(8,'Face2Object','https://www.bossding.com.cn'),(9,'С������','https://www.celess.cn'),(10,'��֮��','http://fzhiy.com');

#
# Structure for table "leave_message_likes_record"
#

DROP TABLE IF EXISTS `leave_message_likes_record`;
CREATE TABLE `leave_message_likes_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pageName` varchar(255) NOT NULL,
  `pId` int(11) NOT NULL,
  `likerId` int(11) NOT NULL,
  `likeDate` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

#
# Data for table "leave_message_likes_record"
#

INSERT INTO `leave_message_likes_record` VALUES (15,'categories',43,2,'2019-12-25 20:42'),(16,'categories',44,2,'2019-12-25 20:42'),(17,'archives',45,2,'2019-12-25 20:43');

#
# Structure for table "leave_message_record"
#

DROP TABLE IF EXISTS `leave_message_record`;
CREATE TABLE `leave_message_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pageName` varchar(255) NOT NULL,
  `pId` int(11) NOT NULL DEFAULT '0',
  `answererId` int(11) NOT NULL,
  `respondentId` int(11) NOT NULL,
  `leaveMessageDate` varchar(255) NOT NULL,
  `likes` int(11) NOT NULL,
  `leaveMessageContent` text NOT NULL,
  `isRead` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

#
# Data for table "leave_message_record"
#

INSERT INTO `leave_message_record` VALUES (43,'categories',0,2,2,'2019-12-25 20:42',1,'111',0),(44,'categories',0,2,2,'2019-12-25 20:42',1,'222',0),(45,'archives',0,2,2,'2019-12-25 20:43',1,'�¸�˵��',0),(46,'categories',44,2,2,'2020-01-07 11:20',0,'����',0),(47,'categories',44,2,2,'2020-01-07 11:21',0,'111',0);

#
# Structure for table "privateword"
#

DROP TABLE IF EXISTS `privateword`;
CREATE TABLE `privateword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `privateWord` varchar(255) NOT NULL,
  `publisherId` varchar(255) NOT NULL,
  `replierId` varchar(255) DEFAULT NULL,
  `replyContent` varchar(255) DEFAULT NULL,
  `publisherDate` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

#
# Data for table "privateword"
#

INSERT INTO `privateword` VALUES (8,'���Ļ�����','1','0',NULL,'2018-09-19 14:13:32');

#
# Structure for table "reward"
#

DROP TABLE IF EXISTS `reward`;
CREATE TABLE `reward` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fundRaiser` varchar(30) NOT NULL,
  `fundRaisingSources` varchar(50) NOT NULL,
  `fundraisingPlace` varchar(50) NOT NULL,
  `rewardMoney` float NOT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  `rewardDate` datetime NOT NULL,
  `rewardUrl` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

#
# Data for table "reward"
#

INSERT INTO `reward` VALUES (1,'��**��','���ں�����','���Ѻ����㡤�������Ķ���',20,'��','2019-07-04 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563121018.jpeg'),(2,'��**��','���ں�����','��������ͯ�ػ���',20,'��','2019-07-04 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563121088.jpeg'),(3,'�ζ��','���ں�����','��Ѱ�İ���ݹ���֮����',50,'��','2019-07-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563121089.jpg'),(4,'����150***5531','���ں�����','���Ѻ����㡤�������Ķ���',1,'��','2019-07-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563121351.jpeg'),(5,'��*��','���ں�����','���Ѻ����㡤�������Ķ���',5,'��','2019-07-06 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563121423.jpeg'),(6,'��','���ں�����','����֢�¶�������',20,'��','2019-07-07 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563121481.jpeg'),(7,'˧�����˿�','΢���տ���','�����Ķ�ͯ�ġ��ġ�����',10,'�ǳ�:˧�����˿�','2019-07-15 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563204301.jpeg'),(8,'��*��','΢���տ���','��������Ů�¶���ħ��',20,'��','2019-07-15 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563204303.jpg'),(9,'��**��','΢���տ���','���߲ʼ��ڡ���ůͯ�ġ�',10,'��','2019-07-16 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-16/1563233973.jpeg'),(10,'������152***062','΢���տ���','��ƶ���¶�������',20,'���Լ������ļ���Ŷ�������Щ����ÿ���˵������أ���Ȼ���࣬���ٳɶ���','2019-07-16 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-16/1563234105.jpeg'),(11,'*��','΢���տ���','���ÿ�����ʹҽ·�мҡ�',10,'��','2019-07-16 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-16/1563234206.jpeg'),(12,'����������ɭ','���ʾ���','���Ѻ����㡤�������Ķ���',100,'ֻϣ����δ�������Լ�����ʱ�ᱻ������������Դ�','2019-08-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-08-05/1565013073.png'),(13,'����������ɭ','���ʾ���','���󲡽��������ƻ���',100,'��ʵ������绹��ͦ��ů��','2019-09-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-09-05/1567697682.png'),(14,'Mr_V','΢���տ���','����ս�ϱ��ػ��ƻ���',20,'ϣ������һֱ����Լ�������','2019-09-25 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-09-25/1569377202.png'),(15,'����.','���ں�����','������ս��Ů����ѧ�Ρ�',20,'��','2019-11-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-11-06/1573023447.png'),(16,'����֮��','���ں�����','����֢�¶�������',5,'��','2019-11-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-11-06/1573023509.png'),(17,'��**��','���ں�����','����Ѫ����ͯ�����ƻ���',20,'��','2019-11-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-11-06/1573023579.png'),(18,'��**��','���ں�����','����ƶ�����˰���������',10,'��','2019-11-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-11-06/1573023651.png'),(19,'��Ĭ�ǽ�*','���ں�����','��������Ҫŭ�ŵ�������',2,'��','2019-11-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-11-06/1573023698.png');

#
# Structure for table "role"
#

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "role"
#

INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN'),(3,'ROLE_SUPERADMIN');

#
# Structure for table "tags"
#

DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagName` varchar(255) NOT NULL,
  `tagSize` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

#
# Data for table "tags"
#

INSERT INTO `tags` VALUES (1,'��ʸ���',15),(4,'SpringBoot',17),(28,'����',20),(29,'1111',12),(30,'ԭ��',12);

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `gender` char(255) NOT NULL,
  `trueName` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `personalBrief` varchar(255) DEFAULT NULL,
  `avatarImgUrl` text NOT NULL,
  `recentlyLanded` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'19940790216','�ź���','a3caed36f0fe5a01e5f144db8927235e','male','�ź���','1997-07-05','1125694337@qq.com','','http://127.0.0.1:8811/static/img/usericon.jpg','2020-01-07 16:07:21'),(2,'18573903136','������','e10adc3949ba59abbe56e057f20f883e','male','������',NULL,NULL,NULL,'http://127.0.0.1:8811/static/img/usericon.jpg\0','2020-01-04 18:44:36');

#
# Structure for table "user_role"
#

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `User_id` int(11) NOT NULL,
  `Role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user_role"
#

INSERT INTO `user_role` VALUES (1,1),(1,2),(1,3),(2,1),(2,3),(2,2);

#
# Structure for table "visitor"
#

DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `visitorNum` bigint(20) NOT NULL,
  `pageName` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

#
# Data for table "visitor"
#

INSERT INTO `visitor` VALUES (1,3228,'totalVisitor'),(2,1032,'visitorVolume'),(3,42,'article/1532884460'),(5,57,'article/1533196734'),(22,1,'article/1576386814'),(23,1,'article/1577178320'),(24,1,'article/1578134716'),(25,1,'article/1578134740'),(26,1,'article/1578138654'),(27,1,'article/1578278648'),(28,0,'article/1578280157'),(29,0,'article/1578364674'),(30,0,'article/1578384244'),(31,0,'article/1578903116');
