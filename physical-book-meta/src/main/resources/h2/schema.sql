CREATE TABLE `t_book`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reader` varchar(255),
  `isbn` varchar(255),
  `title` varchar(255),
  `author` varchar(255),
  `description` varchar(255),
  PRIMARY KEY (`id`)
);
