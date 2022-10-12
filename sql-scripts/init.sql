-- Adminer 4.8.1 MySQL 8.0.31 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

USE `HQLiDS`;

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `post` (`id`, `name`) VALUES
(1,	'test'),
(2,	'asdf'),
(3,	'test3');

-- 2022-10-12 13:43:59
