
DROP TABLE if exists `cash`;
DROP TABLE if exists `package`;
DROP TABLE if exists `receiver`;
DROP TABLE if exists `cash_report`;
DROP TABLE if exists `user`;
DROP TABLE if exists `event`;

-- test.event definition


CREATE TABLE `event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_date` date DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- test.cash definition
CREATE TABLE `cash` (
  `id` int NOT NULL AUTO_INCREMENT,
  `total_amount` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- test.package definition

CREATE TABLE `package` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `event_id` int NOT NULL,
  `qty` int DEFAULT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test.receiver definition
CREATE TABLE `receiver` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `event_id` int NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `approved` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- test.cashReport definition
CREATE TABLE `cash_report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prev_cash` varchar(255) DEFAULT NULL,
  `after_cash` varchar(255) DEFAULT NULL,
  `transaction` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- test.user definition
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO test.`user`
(id, username, password, `role`, created_date)
VALUES(1, 'admin', 'admin', 'admin', '2020-01-01 00:00:00.0');
