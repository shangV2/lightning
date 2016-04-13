
-- 帐号: lightning:pass123 授权
GRANT ALL PRIVILEGES ON db_lightning.* TO lightning@'%' IDENTIFIED BY 'pass123';
FLUSH PRIVILEGES;
-- 创建数据库: db_lightning
CREATE DATABASE IF NOT EXISTS db_lightning;

-- 创建数据表: tb_crawler_website
CREATE TABLE IF NOT EXISTS `db_lightning`.`tb_crawler_website` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `website` varchar(32) NOT NULL,
  `website_type` int(11) DEFAULT NULL,
  `crawler_type` int(11) DEFAULT NULL,
  `url_type` int(11) DEFAULT NULL,
  `url_rule` varchar(2048) DEFAULT NULL,
  `content_type` int(11) DEFAULT NULL,
  `content_rule` varchar(2048) DEFAULT NULL,
  `seeds` varchar(1024) DEFAULT NULL,
  `crawler_num` int(11) DEFAULT NULL,
  `crawler_schedule` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `create_time` int(11), 
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_crawler_website_1website_2webtype` (`website`,`website_type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

