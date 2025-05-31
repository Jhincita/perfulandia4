-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.42 - MySQL Community Server - GPL
-- Server OS:                    Linux
-- HeidiSQL Version:             12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for perfulandia
CREATE DATABASE IF NOT EXISTS `perfulandia` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `perfulandia`;

-- Dumping structure for table perfulandia.cart
CREATE TABLE IF NOT EXISTS `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table perfulandia.cart: ~0 rows (approximately)

-- Dumping structure for table perfulandia.cart_item
CREATE TABLE IF NOT EXISTS `cart_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cart_id` int NOT NULL,
  `product_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cart_id` (`cart_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `cart_item_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `cart_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `cart_item_chk_1` CHECK ((`quantity` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table perfulandia.cart_item: ~0 rows (approximately)

-- Dumping structure for table perfulandia.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table perfulandia.category: ~4 rows (approximately)
INSERT INTO `category` (`id`, `name`) VALUES
	(1, 'fresh'),
	(2, 'citric'),
	(3, 'gourmand'),
	(4, 'floral');

-- Dumping structure for table perfulandia.category_products
CREATE TABLE IF NOT EXISTS `category_products` (
  `category_id` bigint NOT NULL,
  `products_id` bigint NOT NULL,
  UNIQUE KEY `UKfdnk3mk70n1rc08vw1cj65kqw` (`products_id`),
  CONSTRAINT `FKe9irm5a62pmolhvr468cip3v3` FOREIGN KEY (`products_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table perfulandia.category_products: ~0 rows (approximately)

-- Dumping structure for table perfulandia.login
CREATE TABLE IF NOT EXISTS `login` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table perfulandia.login: ~1 rows (approximately)
INSERT INTO `login` (`id`, `username`, `email`, `token`) VALUES
	(1, 'jojin', 'jojin@jmail.com', '4444');

-- Dumping structure for table perfulandia.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_date` datetime DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table perfulandia.orders: ~0 rows (approximately)

-- Dumping structure for table perfulandia.order_product
CREATE TABLE IF NOT EXISTS `order_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `buy_price` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `order_product_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `order_product_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table perfulandia.order_product: ~0 rows (approximately)

-- Dumping structure for table perfulandia.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `price` int NOT NULL,
  `category_id` int DEFAULT NULL,
  `ml` int DEFAULT NULL,
  `inventory` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table perfulandia.product: ~13 rows (approximately)
INSERT INTO `product` (`id`, `name`, `brand`, `notes`, `price`, `category_id`, `ml`, `inventory`) VALUES
	(2, 'Paradoxe', 'Prada', 'bergamota, mandarina, neroli, jazmin, flor de azahar del naranjo', 100, 4, 100, 0),
	(3, 'Philosykos', 'Diptyque', 'higuera, hoja de higuera, madera, coco', 120, 1, 75, 0),
	(4, 'Tam Dao', 'Diptyque', 'sándalo, ciprés, cedro, especias', 130, 1, 75, 0),
	(5, 'Do Son', 'Diptyque', 'tuberosa, flor de azahar del naranjo, jazmín, almizcle', 135, 4, 75, 0),
	(6, 'Gypsy Water', 'Byredo', 'bergamota, enebro, incienso, vainilla, sándalo', 190, 1, 100, 0),
	(7, 'Bal d\'Afrique', 'Byredo', 'bergamota, flor de azahar, violeta, vetiver, almizcle', 195, 2, 100, 0),
	(8, 'Blanche', 'Byredo', 'rosa blanca, peonía, almizcle, sándalo', 185, 4, 100, 0),
	(9, 'Montale Chocolate Greedy', 'Montale', 'Chocolate, caramelo, vainilla, café', 130, 3, 100, 0),
	(10, 'Montale Intense Cafe', 'Montale', 'Café, rosa, vainilla, almizcle blanco', 120, 3, 100, 0),
	(11, 'Montale Aoud Chocolate', 'Montale', 'Chocolate, oud, ámbar, vainilla', 140, 3, 100, 0),
	(12, 'Love, Don\'t Be Shy', 'Kilian', 'Naranja, jazmín, vainilla, caramelo', 180, 3, 50, 0),
	(13, 'Black Phantom', 'Kilian', 'Café, ron, caramelo, almizcle', 190, 3, 50, 0),
	(14, 'Good Girl Gone Bad', 'Kilian', 'Jazmín, nardos, almizcle blanco', 175, 3, 50, 0);

-- Dumping structure for table perfulandia.session_token
CREATE TABLE IF NOT EXISTS `session_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table perfulandia.session_token: ~4 rows (approximately)
INSERT INTO `session_token` (`id`, `token`, `is_active`) VALUES
	(1, '4444', 1),
	(2, 'b9fgemmll7h', 1),
	(3, 'c07iu85xk7d', 1),
	(4, '2d430h8cb0b', 1);

-- Dumping structure for table perfulandia.stock
CREATE TABLE IF NOT EXISTS `stock` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `quantity` int NOT NULL DEFAULT '0',
  `low_stock_threshold` int NOT NULL DEFAULT '10',
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_id` (`product_id`),
  CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table perfulandia.stock: ~0 rows (approximately)

-- Dumping structure for table perfulandia.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table perfulandia.users: ~6 rows (approximately)
INSERT INTO `users` (`id`, `username`, `email`, `password`) VALUES
	(1, 'jojito', 'jojito@jmail.com', 'jojopass4'),
	(2, 'yanfri', 'yanfri@jmail.com', 'yanfripass4'),
	(3, 'pancito', 'pancito@jmail.com', 'pancitopass4'),
	(4, 'lestat', 'lestat@jmail.com', 'lestatpass4'),
	(5, 'genocidio', 'genocidio@jmail.com', 'genocidiopass4'),
	(6, 'tototiramisu', 'tototiramisu@jmail.com', 'tototiramisupass4');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
