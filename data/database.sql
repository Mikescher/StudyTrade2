-- Exportiere Datenbank Struktur für studytrade
CREATE DATABASE IF NOT EXISTS `studytrade` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `studytrade`;


CREATE TABLE IF NOT EXISTS `articles` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  `condition` int(11) DEFAULT NULL,
  `place` varchar(45) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  `description` text NOT NULL,
  `article_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
DELETE FROM `articles`;
INSERT INTO `articles` (`article_id`, `name`, `price`, `condition`, `place`, `seller_id`, `pictures`, `description`, `article_timestamp`) VALUES
	(1, 'test', 123.00, 2, 'KA', 1, 'null', 'hello world', '2014-05-08 18:36:42'),
	(2, 'blub', 0.00, 0, 'OG', 2, 'null', 'im a tex', '2014-05-08 18:36:42'),
	(3, 'foo', 42.00, 1, 'KA', 2, 'null', 'lalal', '2014-05-08 18:36:42'),
	(5, 'SwimmingPool', 999.99, 2, 'Thaiti', 4, 'null', 'Very Cheap. Buy now. Wow', '2014-05-08 18:36:42');

CREATE TABLE IF NOT EXISTS `messages` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) NOT NULL,
  `target_id` int(11) NOT NULL,
  `message_header` varchar(50) NOT NULL DEFAULT '0',
  `message_text` varchar(50) NOT NULL DEFAULT '0',
  `message_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
DELETE FROM `messages`;
INSERT INTO `messages` (`message_id`, `sender_id`, `target_id`, `message_header`, `message_text`, `message_timestamp`) VALUES
	(1, 1, 4, 'header', 'Hello Armin this is Mike', '2014-05-08 18:51:17'),
	(2, 1, 4, 'header', 'Hello Armin this is Mike, again', '2014-05-08 18:51:27'),
	(3, 1, 2, 'lalalalalalalalalaaaaa', 'Hallo Timo, was GÄÄÄÄÄÄÄÄHT', '2014-05-08 18:55:47');

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `forename` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `nickname` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `university` varchar(45) NOT NULL,
  `studydirection` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `passwordhash` char(40) NOT NULL,
  `activated` int(11) NOT NULL,
  `comment` varchar(50) DEFAULT NULL,
  `user_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
DELETE FROM `users`;
INSERT INTO `users` (`user_id`, `forename`, `lastname`, `nickname`, `city`, `university`, `studydirection`, `mail`, `passwordhash`, `activated`, `comment`, `user_timestamp`) VALUES
	(1, 'Mike', 'Schwörer', 'Mikescher', 'Karlsruhe', 'DH KA', 'Informatik', 'mailport@mikescher.de', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', 1, 'pw=test', '2014-05-08 18:28:52'),
	(2, 'Timo', 'Höting', 'Timöting', 'Bielefeld', 'DH KA', 'Informatik', 'timo@timo.timo', 'afad13cf1941af4ad3101bdf30f087f7dfe27c99', 1, 'pw=blub', '2014-05-08 18:28:52'),
	(3, 'Max', 'Bielemeier', 'MBiel', 'Offenburg', 'DH FR', 'Informatik', 'kripo.köln@brd.de', '0beec7b5ea3f0fdbc95d0dd47f3c5bc275da8a33', 0, 'pw=foo', '2014-05-08 18:28:52'),
	(4, 'Armin', 'Benz', 'Benz1n', 'Zell A.H.', 'DH KA', 'BWL', 'a@b.c', '26c4202eb475d02864b40827dfff11a14657aa41', 1, 'pw=meh', '2014-05-08 18:28:52'),
	(5, 'Max', 'Mustermann', 'MaMu', 'Zunsweier', 'Uni Offenburg', 'Hartz IV', 'nananananana@gmx.us', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 1, 'pw=123', '2014-05-08 18:28:52');
