
GRANT ALL PRIVILEGES ON db_lightning.* TO lightning@'%' IDENTIFIED BY 'pass123';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS db_lightning;

CREATE TABLE IF NOT EXISTS `db_lightning`.`tb_sensitive_word` 
(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    word varchar(32),
    type int,
    UNIQUE KEY `sword_word_type`(`word`, `type`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `db_lightning`.`tb_sensitive_trend`
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	word varchar(32),
	type int,
	timestamp varchar(32),
	value int,
	UNIQUE KEY `strend_word_type_timestamp`(`word`, `type`, `timestamp`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `db_lightning`.`tb_topic` (
      id INTEGER PRIMARY KEY AUTO_INCREMENT,
      topic_id INTEGER,
      topic_name varchar(64),
      type int,
      words_json varchar(1024),
      start_date varchar(32),
      end_date varchar(32),
      percent int,
      UNIQUE KEY `ttopic_topic_id`(`topic_id`),
      UNIQUE KEY `ttopic_topic_name_type`(`topic_name`, `type`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `db_lightning`.`tb_topic_trend` (
     id INTEGER PRIMARY KEY AUTO_INCREMENT,
     topic_id int,
     timestamp varchar(32),
     value int,
     UNIQUE KEY `ttrend_topic_id_timestamp` (`topic_id`, `timestamp`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
