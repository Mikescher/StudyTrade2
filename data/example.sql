-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server Version:               5.6.16 - MySQL Community Server (GPL)
-- Server Betriebssystem:        Win32
-- HeidiSQL Version:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Exportiere Datenbank Struktur für studytrade
DROP DATABASE IF EXISTS `studytrade`;
CREATE DATABASE IF NOT EXISTS `studytrade` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `studytrade`;


-- Exportiere Struktur von Tabelle studytrade.articles
DROP TABLE IF EXISTS `articles`;
CREATE TABLE IF NOT EXISTS `articles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  `condition` int(11) DEFAULT NULL,
  `place` varchar(45) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle studytrade.articles: ~0 rows (ungefähr)
DELETE FROM `articles`;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
INSERT INTO `articles` (`id`, `name`, `price`, `condition`, `place`, `seller_id`, `pictures`, `description`) VALUES
	(1, 'test', 123.00, 2, 'KA', 1, 'null', 'hello world'),
	(2, 'blub', 0.00, 0, 'OG', 2, 'null', 'im a tex'),
	(3, 'foo', 42.00, 1, 'KA', 2, 'null', 'lalal'),
	(5, 'SwimmingPool', 999.99, 2, 'Thaiti', 4, 'null', 'Very Cheap. Buy now. Wow');
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;


-- Exportiere Struktur von Tabelle studytrade.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `forename` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `nickname` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `university` varchar(45) NOT NULL,
  `studydirection` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `passwordhash` char(40) NOT NULL,
  `activated` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle studytrade.users: ~0 rows (ungefähr)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `forename`, `lastname`, `nickname`, `city`, `university`, `studydirection`, `mail`, `passwordhash`, `activated`) VALUES
	(1, 'Mike pw=test', 'Schwörer', 'Mikescher', 'Karlsruhe', 'DH KA', 'Informatik', 'mailport@mikescher.de', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', 1),
	(2, 'Timo pw=blub', 'Höting', 'Timöting', 'Bielefeld', 'DH KA', 'Informatik', 'timo@timo.timo', 'afad13cf1941af4ad3101bdf30f087f7dfe27c99', 1),
	(3, 'Max pw=foo', 'Bielemeier', 'MBiel', 'Offenburg', 'DH FR', 'Informatik', 'kripo.köln@brd.de', '0beec7b5ea3f0fdbc95d0dd47f3c5bc275da8a33', 0),
	(4, 'Armin pw=meh', 'Benz', 'Benz1n', 'Zell A.H.', 'DH KA', 'BWL', 'a@b.c', '26c4202eb475d02864b40827dfff11a14657aa41', 1),
	(5, 'Max pw=123', 'Mustermann', 'MaMu', 'Zunsweier', 'Uni Offenburg', 'Hartz IV', 'nananananana@gmx.us', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
