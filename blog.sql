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

INSERT INTO `archives` VALUES (1,'2018年07月'),(2,'2018年08月'),(5,'2020年01月');

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

INSERT INTO `article` VALUES (30,1578384244,'曾梦瑶','曾梦瑶','测试','111','测试,原创','原创','111','2020-01-07','2020-01-07','http://127.0.0.1:8811/article/1578384244','111...',1,0,1578903116),(31,1578903116,'曾梦瑶','曾梦瑶','测试','123\n123\n123\n123\n123\n`xxxxxxxxxxxxxxxxxxxxx`','1111,原创','原创','我的故事','2020-01-13','2020-01-16','http://127.0.0.1:8811/article/1578903116','123123123123123xxxxxxxxxxxxxxxxxxxxx...',0,1578384244,0);

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

INSERT INTO `categories` VALUES (1,'我的故事'),(2,'SpringBoot'),(5,'111');

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

INSERT INTO `comment_record` VALUES (59,0,1578903116,2,2,'2020-01-13 16:12',0,'反反复复付',0),(60,59,1578903116,2,2,'2020-01-13 16:12',0,'嘎嘎嘎嘎嘎过',0),(61,0,1578903116,2,2,'2020-02-09 12:14',0,'222',0);

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

INSERT INTO `friendlink` VALUES (1,'naget的小屋','https://naget.github.io'),(2,'Li Pan\'s 博客','http://www.lipan.xyz'),(3,'陈晓雷个人博客','http://www.csxll.top'),(4,'SAn Blog','https://sanii.cn'),(5,'会打篮球的程序猿','http://www.liuzhaopo.top'),(6,'Mr_曾中杰','https://www.zengzhongjie.com'),(7,'去当CTO','https://www.qdcto.com'),(8,'Face2Object','https://www.bossding.com.cn'),(9,'小海博客','https://www.celess.cn'),(10,'枫之羽','http://fzhiy.com');

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

INSERT INTO `leave_message_record` VALUES (43,'categories',0,2,2,'2019-12-25 20:42',1,'111',0),(44,'categories',0,2,2,'2019-12-25 20:42',1,'222',0),(45,'archives',0,2,2,'2019-12-25 20:43',1,'嘎嘎说的',0),(46,'categories',44,2,2,'2020-01-07 11:20',0,'请求',0),(47,'categories',44,2,2,'2020-01-07 11:21',0,'111',0);

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

INSERT INTO `privateword` VALUES (8,'悄悄话测试','1','0',NULL,'2018-09-19 14:13:32');

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

INSERT INTO `reward` VALUES (1,'海**依','公众号赞赏','《搜狐焦点·拯救先心儿》',20,'无','2019-07-04 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563121018.jpeg'),(2,'依**贝','公众号赞赏','《困境儿童关怀》',20,'无','2019-07-04 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563121088.jpeg'),(3,'涛多多','公众号赞赏','《寻四百万份光明之爱》',50,'无','2019-07-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563121089.jpg'),(4,'华仔150***5531','公众号赞赏','《搜狐焦点·拯救先心儿》',1,'无','2019-07-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563121351.jpeg'),(5,'邓*燕','公众号赞赏','《搜狐焦点·拯救先心儿》',5,'无','2019-07-06 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563121423.jpeg'),(6,'梦','公众号赞赏','《重症孤儿救助》',20,'无','2019-07-07 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563121481.jpeg'),(7,'帅到被人砍','微信收款码','《先心儿童的“心”生》',10,'昵称:帅到被人砍','2019-07-15 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563204301.jpeg'),(8,'曹*玉','微信收款码','《花季少女勇斗病魔》',20,'无','2019-07-15 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-15/1563204303.jpg'),(9,'海**依','微信收款码','《七彩假期·情暖童心》',10,'无','2019-07-16 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-16/1563233973.jpeg'),(10,'卫朝美152***062','微信收款码','《贫困孤儿助养》',20,'可以加入你的募捐团队吗？做这些事是每个人的梦想呢！虽然不多，积少成多嘛','2019-07-16 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-16/1563234105.jpeg'),(11,'*王','微信收款码','《让口罩天使医路有家》',10,'无','2019-07-16 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-07-16/1563234206.jpeg'),(12,'张张张张先森','工资捐赠','《搜狐焦点·拯救先心儿》',100,'只希望在未来我们自己有难时会被这个世界温柔以待','2019-08-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-08-05/1565013073.png'),(13,'张张张张先森','工资捐赠','《大病紧急救助计划》',100,'其实这个世界还是挺温暖的','2019-09-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-09-05/1567697682.png'),(14,'Mr_V','微信收款码','《抗战老兵关怀计划》',20,'希望你能一直坚持自己的梦想','2019-09-25 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-09-25/1569377202.png'),(15,'乐意.','公众号赞赏','《点亮战地女孩上学梦》',20,'无','2019-11-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-11-06/1573023447.png'),(16,'醉翁之意','公众号赞赏','《重症孤儿救助》',5,'无','2019-11-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-11-06/1573023509.png'),(17,'海**依','公众号赞赏','《白血病儿童康复计划》',20,'无','2019-11-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-11-06/1573023579.png'),(18,'依**贝','公众号赞赏','《助贫困老人安放余生》',10,'无','2019-11-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-11-06/1573023651.png'),(19,'沉默是金*','公众号赞赏','《我们想要怒放的生命》',2,'无','2019-11-05 00:00:00','https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/rewardRecord/2019-11-06/1573023698.png');

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

INSERT INTO `tags` VALUES (1,'随笔感悟',15),(4,'SpringBoot',17),(28,'测试',20),(29,'1111',12),(30,'原创',12);

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

INSERT INTO `user` VALUES (1,'19940790216','张海洋','a3caed36f0fe5a01e5f144db8927235e','male','张海洋','1997-07-05','1125694337@qq.com','','http://127.0.0.1:8811/static/img/usericon.jpg','2020-01-07 16:07:21'),(2,'18573903136','曾梦瑶','e10adc3949ba59abbe56e057f20f883e','male','曾梦瑶',NULL,NULL,NULL,'http://127.0.0.1:8811/static/img/usericon.jpg\0','2020-01-04 18:44:36');

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
