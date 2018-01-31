

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_number` varchar(20) DEFAULT NULL,
  `date_open` date DEFAULT NULL,
  `date_close` date DEFAULT NULL,
  `summa` float DEFAULT NULL,
  `currency` varchar(3) DEFAULT NULL,
  `account_type` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE `currency_kurs` (
  `currency` varchar(3) DEFAULT NULL,
  `sale` float DEFAULT NULL,
  `cost` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `surname` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  `passport_number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

CREATE TABLE `dict_dep` (
  `deposit_id` int(11) NOT NULL AUTO_INCREMENT,
  `deposit_name` varchar(30) DEFAULT NULL,
  `currency` varchar(3) DEFAULT NULL,
  `min_summa` float DEFAULT NULL,
  `percent` float DEFAULT NULL,
  `period` int(11) DEFAULT NULL,
  `is_reversal` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`deposit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `dict_payment` (
  `payment_name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_payment` date DEFAULT NULL,
  `bank_code` varchar(11) DEFAULT NULL,
  `reseiver_account` varchar(34) DEFAULT NULL,
  `currency` varchar(3) DEFAULT NULL,
  `summa` float DEFAULT NULL,
  `payment_name` varchar(100) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  `card_number` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `account_id` (`account_id`),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8; 

