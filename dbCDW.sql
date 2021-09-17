-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: dacnpm
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Thu Duc','VN','Linh Xuân','123'),(2,'San Jose','US','1 Main St',NULL),(3,'San Jose','US','1 Main St',NULL),(4,'Linh ','Xuan','TP HCM','111'),(5,'TP HCM','Vietnam','TP HCM','83833'),(6,'San Jose','US','1 Main St',NULL),(7,'San Jose','US','1 Main St',NULL),(8,'San Jose','US','1 Main St',NULL),(9,'HCM','VN','Bình Định','123'),(10,'TP HCM','Vietnam','Lin','83833'),(11,'TP HCM','Vietnam','TP HCM','83833');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `shoe_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK84q63uua41n9yoe9lrc7c4y3n` (`shoe_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'AIR JORDAN',1),(2,'NIKE',11),(3,'CONVERSE',21),(4,'ADIDAS',31);
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qty` int(11) NOT NULL,
  `size` varchar(255) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `shoe_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKen9v41ihsnhcr0i7ivsd7i84c` (`order_id`),
  KEY `FKcjdaq1x2gmkdvvarc2yrivwye` (`shoe_id`),
  KEY `FKjnaj4sjyqjkr4ivemf9gb25w` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (1,1,'8',1,40,2),(2,1,'9',2,40,2),(3,2,'9',2,42,2),(4,1,'7',3,41,2),(5,1,'6',3,13,2),(6,4,'6',4,39,3),(7,3,'7',5,39,2),(8,1,'7',6,35,3),(9,1,'6',7,39,2),(10,5,'6',8,35,4),(11,5,'9',8,10,4),(12,4,'6',9,39,4),(13,1,'8',10,36,2),(15,1,'6',11,41,2),(17,2,'7',11,41,2),(18,1,'6',12,41,2),(19,1,'8',13,38,2);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `shoe_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrpojua1vj6vlkvmg92kfvldci` (`shoe_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Male',1),(2,'FeMale',2),(3,'Male',3);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `payments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_name` varchar(255) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `cvc` int(11) NOT NULL,
  `expiry_month` int(11) NOT NULL,
  `expiry_year` int(11) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKadbu3dk1vxmqt9fwwebphdd8c` (`order_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,NULL,'44553',12345,1,2020,'ĐAO QUANG NHAT','discover',3),(2,'John Doe','95131',0,0,0,'DoeJohn','Paypal',4),(3,'John Doe','95131',0,0,0,'DoeJohn','Paypal',5),(4,NULL,'123',1111,1,2020,'Dung','visa',6),(5,NULL,'1111',1111,1,2020,'1111','amex',7),(6,'John Doe','95131',0,0,0,'DoeJohn','Paypal',8),(7,'John Doe','95131',0,0,0,'DoeJohn','Paypal',9),(8,'John Doe','95131',0,0,0,'DoeJohn','Paypal',10),(9,NULL,'Ok ok',1123,1,2020,'Ok ','discover',11),(10,NULL,'123577',111,1,2020,'ABcds','visa',12),(11,NULL,'11233',111,1,2020,'232323','amex',13);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Admin'),(2,'User');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping`
--

DROP TABLE IF EXISTS `shipping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `shipping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `receiver` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj6ayrlwisv2n1yalmjxjlu62p` (`address_id`),
  KEY `FKlh4uncaukk0s3poj5pmv9cm9u` (`order_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping`
--

LOCK TABLES `shipping` WRITE;
/*!40000 ALTER TABLE `shipping` DISABLE KEYS */;
INSERT INTO `shipping` VALUES (1,'Dao Quang Nhat',1,3),(2,'Doe',2,4),(3,'Doe',3,5),(4,'Dung',4,6),(5,'11111',5,7),(6,'Doe',6,8),(7,'Doe',7,9),(8,'Doe',8,10),(9,'Quang Dũng',9,11),(10,'Dung',10,12),(11,'quang',11,13);
/*!40000 ALTER TABLE `shipping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoe`
--

DROP TABLE IF EXISTS `shoe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `shoe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `des` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `stock` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoe`
--

LOCK TABLES `shoe` WRITE;
/*!40000 ALTER TABLE `shoe` DISABLE KEYS */;
INSERT INTO `shoe` VALUES (1,NULL,'Adidas-Human-Race-NMD-Pharrell-x-Chanel-Black','Adidas-Human-Race-NMD-Pharrell-x-Chanel-Black.jpeg',25,20),(2,NULL,'adidas-NMD-Hu-Pharrell-Mother-Product','adidas-NMD-Hu-Pharrell-Mother-Product.jpg',25,20),(3,NULL,'Adidas-NMD-R1-Japan-Triple-White-Product','Adidas-NMD-R1-Japan-Triple-White-Product.jpg',23,20),(4,NULL,'adidas-Ultra-Boost-4-Game-of-Thrones-White-Walkers-1','adidas-Ultra-Boost-4-Game-of-Thrones-White-Walkers-1.jpg',45,20),(5,NULL,'Adidas-Yeezy-Boost-350-V2-Core-Black-Red-Product','Adidas-Yeezy-Boost-350-V2-Core-Black-Red-Product.jpg',76,20),(6,NULL,'adidas-Yeezy-Boost-350-V2-Lundmark-Product','adidas-Yeezy-Boost-350-V2-Lundmark-Product.jpg',34,20),(7,NULL,'adidas-Yeezy-Boost-500-Bone-White','adidas-Yeezy-Boost-500-Bone-White.jpg',32,20),(8,NULL,'Adidas-Yeezy-Powerphase-Calabasas-Grey-Product','Adidas-Yeezy-Powerphase-Calabasas-Grey-Product.jpg',43,20),(9,NULL,'Adidas-Yeezy-Wave-Runner-700-Solid-Grey-Product','Adidas-Yeezy-Wave-Runner-700-Solid-Grey-Product.jpg',65,20),(10,'','Adidas-ZX-5000-Undftd-Bape-camo1','news-5-thumnails.jpg',87,15),(11,NULL,'Air-Jordan-1-Low-SB-UNC-v2','Air-Jordan-1-Low-SB-UNC-v2.jpg',231,20),(12,NULL,'Air-Jordan-1-Retro-High-Satin-Black-Toe','Air-Jordan-1-Retro-High-Satin-Black-Toe.jpg',545,20),(13,NULL,'Air-Jordan-1-Retro-High-Travis-Scott-Product','Air-Jordan-1-Retro-High-Travis-Scott-Product.jpg',754,19),(14,NULL,'Air-Jordan-4-Retro-Cool-Grey-2019-Product','Air-Jordan-4-Retro-Cool-Grey-2019-Product.jpg',22,20),(15,NULL,'Air-Jordan-11-Retro-Low-Snake-Navy-2019-Product','Air-Jordan-11-Retro-Low-Snake-Navy-2019-Product.jpg',434,20),(16,NULL,'Air-Jordan-12-Retro-Fiba-2019','Air-Jordan-12-Retro-Fiba-2019.jpeg',65,20),(17,NULL,'Asics-Gel-Lyte-III-Afew-Koi','Asics-Gel-Lyte-III-Afew-Koi.jpg',21,20),(18,NULL,'Asics-Gel-Lyte-III-Afew-X-Beams-Orange-Koi-Product','Asics-Gel-Lyte-III-Afew-X-Beams-Orange-Koi-Product.jpg',23,20),(19,NULL,'Asics-Gel-Lyte-III-Concepts-Boston-Tea-Party','Asics-Gel-Lyte-III-Concepts-Boston-Tea-Party.jpg',65,20),(20,NULL,'Asics-Gel-Lyte-III-Concepts-Three-Lies','Asics-Gel-Lyte-III-Concepts-Three-Lies.jpg',54,20),(21,NULL,'Asics-Gel-Lyte-III-Kith-Grand-Opening','Asics-Gel-Lyte-III-Kith-Grand-Opening.jpg',7,20),(22,NULL,'Asics-Gel-Lyte-III-Ronnie-Fieg-Super-Green','Asics-Gel-Lyte-III-Ronnie-Fieg-Super-Green.jpg',6,20),(23,NULL,'Asics-Gel-Lyte-III-Saint-Alfred-Olive-Birch','Asics-Gel-Lyte-III-Saint-Alfred-Olive-Birch.jpg',87,20),(24,NULL,'Converse-Chuck-Taylor-All-Star-70s-Hi-BHM-2019-All-Over-Print-Product','Converse-Chuck-Taylor-All-Star-70s-Hi-BHM-2019-All-Over-Print-Product.jpg',34,20),(25,NULL,'Converse-Chuck-Taylor-All-Star-70s-Hi-Brain-Dead-Product','Converse-Chuck-Taylor-All-Star-70s-Hi-Brain-Dead-Product.jpg',312,20),(26,NULL,'Converse-Chuck-Taylor-All-Star-70s-Hi-Comme-des-Garcons-PLAY-White-Product','Converse-Chuck-Taylor-All-Star-70s-Hi-Comme-des-Garcons-PLAY-White-Product.jpg',21,20),(27,NULL,'Converse-Chuck-Taylor-All-Star-70s-Hi-Kith-Coca-Cola-Denim-2019','Converse-Chuck-Taylor-All-Star-70s-Hi-Kith-Coca-Cola-Denim-2019.jpeg',43,20),(28,NULL,'Converse-Chuck-Taylor-All-Star-70s-Hi-Kith-Navy-Product','Converse-Chuck-Taylor-All-Star-70s-Hi-Kith-Navy-Product.jpg',54,20),(29,NULL,'Converse-Chuck-Taylor-All-Star-70s-Hi-Off-White-Product','Converse-Chuck-Taylor-All-Star-70s-Hi-Off-White-Product.jpg',65,20),(30,NULL,'Converse-Chuck-Taylor-All-Star-70s-Hi-Undercover-New-Warriors-White-Product','Converse-Chuck-Taylor-All-Star-70s-Hi-Undercover-New-Warriors-White-Product.jpg',76,20),(31,NULL,'Converse-Chuck-Taylor-All-Star-Hi-Off-White-Product','Converse-Chuck-Taylor-All-Star-Hi-Off-White-Product.jpg',98,20),(32,NULL,'Converse-One-Star-Ox-Golf-Le-Fleur-Industrial-Pack-Blue-Product','Converse-One-Star-Ox-Golf-Le-Fleur-Industrial-Pack-Blue-Product.jpg',32,20),(33,NULL,'Converse-One-Star-Ox-Tyler-The-Creator-Golf-Le-Fleur-Jade-Lime-Product','Converse-One-Star-Ox-Tyler-The-Creator-Golf-Le-Fleur-Jade-Lime-Product.jpg',43,20),(34,NULL,'Converse-Run-Star-Hike-Hi-JW-Anderson-Black-Product','Converse-Run-Star-Hike-Hi-JW-Anderson-Black-Product.jpg',54,20),(35,NULL,'Nike-Adapt-BB-Mag-Product','Nike-Adapt-BB-Mag-Product.jpg',65,14),(36,NULL,'Nike-Air-Force-1-Low-Off-White-Black-White-Product','Nike-Air-Force-1-Low-Off-White-Black-White-Product.jpg',76,19),(37,NULL,'Nike-Air-Max-1-97-Sean-Wotherspoon-NA-Product','Nike-Air-Max-1-97-Sean-Wotherspoon-NA-Product.jpg',12,20),(38,NULL,'Nike-Air-Max-1-Tinker-Sketch','Nike-Air-Max-1-Tinker-Sketch.jpg',23,19),(39,NULL,'Nike-Air-Max-90-Off-White-Product','Nike-Air-Max-90-Off-White-Product.jpg',56,8),(40,NULL,'Nike-Air-Presto-Off-White-White-2018-Product','Nike-Air-Presto-Off-White-White-2018-Product.jpg',76,13),(41,NULL,'Nike-Blazer-High-sacai-Snow-Beach-Product','Nike-Blazer-High-sacai-Snow-Beach-Product.jpg',43,14),(42,NULL,'Nike-Cortez-Stranger-Things-Independence-Day-Pack','Nike-Cortez-Stranger-Things-Independence-Day-Pack.jpeg',56,8),(43,NULL,'Nike-Kyrie-5-Spongebob-Product','Nike-Kyrie-5-Spongebob-Product.jpg',87,20),(44,NULL,'Nike-SB-Dunk-Low-Parra-Product','Nike-SB-Dunk-Low-Parra-Product.jpg',32,20),(45,NULL,'Vans-OG-Sk8-Hi-Odd-Future-Donut-Product','Vans-OG-Sk8-Hi-Odd-Future-Donut-Product.jpg',213,20),(46,NULL,'Vans-Old-Skool-Colorblock-Multi-Product','Vans-Old-Skool-Colorblock-Multi-Product.jpg',213,20),(47,NULL,'Vans-Old-Skool-David-Bowie-Aladdin-Sane-Product','Vans-Old-Skool-David-Bowie-Aladdin-Sane-Product.jpg',213,17),(48,NULL,'Vans-Old-Skool-Marvel-What-The-Avengers-Product','Vans-Old-Skool-Marvel-What-The-Avengers-Product.jpg',45,20),(49,NULL,'Vans-Sk8-Hi-Checkerboard-Cap-1','Vans-Sk8-Hi-Checkerboard-Cap-1.jpg',76,17),(50,NULL,'Vans-Sk8-Hi-Deconstructed-Black-Product','Vans-Sk8-Hi-Deconstructed-Black-Product.jpg',87,11);
/*!40000 ALTER TABLE `shoe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size`
--

DROP TABLE IF EXISTS `size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `size` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) DEFAULT NULL,
  `shoe_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2q1nuv76iyj2kb03v7hbb1atj` (`shoe_id`)
) ENGINE=MyISAM AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES (1,'7',1),(2,'8',1),(3,'9',1),(4,'10',1),(5,'6',2),(6,'7',2),(7,'8',2),(8,'9',2),(9,'6',3),(10,'6',3),(11,'7',3),(12,'8',3),(13,'9',3),(14,'6',4),(15,'7',4),(16,'8',5),(17,'9',5),(18,'6',6),(19,'7',6),(20,'8',7),(21,'9',7),(22,'6',7),(23,'7',7),(24,'8',8),(25,'9',8),(26,'6',9),(27,'7',9),(28,'8',10),(29,'9',10),(30,'6',11),(31,'7',11),(32,'8',12),(33,'9',12),(34,'6',13),(35,'7',13),(36,'8',14),(37,'9',14),(38,'6',15),(39,'7',15),(40,'8',16),(41,'9',16),(42,'6',17),(43,'7',17),(44,'8',18),(45,'9',18),(46,'6',19),(47,'7',19),(48,'8',20),(49,'9',20),(50,'6',21),(51,'7',21),(52,'8',22),(53,'9',22),(54,'6',23),(55,'7',23),(56,'8',24),(57,'9',24),(58,'6',24),(59,'7',25),(60,'8',25),(61,'9',26),(62,'6',26),(63,'7',27),(64,'8',28),(65,'9',28),(66,'6',29),(67,'7',29),(68,'8',30),(69,'9',30),(70,'6',31),(71,'7',31),(72,'8',32),(73,'9',32),(74,'6',33),(75,'7',33),(76,'8',34),(77,'9',34),(78,'6',35),(79,'7',35),(80,'8',36),(81,'9',36),(82,'7',37),(83,'8',38),(84,'9',38),(85,'6',39),(86,'7',39),(87,'8',40),(88,'9',40),(89,'6',41),(90,'7',41),(91,'8',42),(92,'9',42),(93,'6',43),(94,'7',43),(95,'8',44),(96,'9',44),(97,'6',45),(98,'7',45),(99,'8',46),(100,'9',46),(101,'7',47),(102,'8',48),(103,'9',48),(104,'6',49),(105,'7',49),(106,'8',50),(107,'9',50);
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tokens` (
  `token_id` bigint(20) NOT NULL,
  `confirmation_token` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`token_id`),
  KEY `FKgh73xiagbl0no2bm4i7q29isu` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokens`
--

LOCK TABLES `tokens` WRITE;
/*!40000 ALTER TABLE `tokens` DISABLE KEYS */;
/*!40000 ALTER TABLE `tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKddefmvbrws3hvl5t0hnnsv8ox` (`address_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,NULL,'another',_binary '','$2a$10$CAqHK2m200XW8hX1pz/IWOLHE/gQHIIceMCRxA7XUgZSQ4DQlp8Ka',NULL,'admin',NULL),(2,'quangnhat.251199@gmail.com','Đào Quang Nhật','male',_binary '','$2a$10$CAqHK2m200XW8hX1pz/IWOLHE/gQHIIceMCRxA7XUgZSQ4DQlp8Ka','0977376754','nhat',NULL),(3,'quangnhat@gmail.com','Nhật chứ ai','female',_binary '','$2a$10$CAqHK2m200XW8hX1pz/IWOLHE/gQHIIceMCRxA7XUgZSQ4DQlp8Ka','1234567890','nhat1',NULL),(4,'quangnhat@gmail.com','Nhật chứ ai','another',_binary '','$2a$10$CAqHK2m200XW8hX1pz/IWOLHE/gQHIIceMCRxA7XUgZSQ4DQlp8Ka','1234567891','nhat2',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_order`
--

DROP TABLE IF EXISTS `user_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_order` (
  `id` bigint(20) NOT NULL,
  `order_date` datetime DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `order_total` decimal(19,2) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `shipping_date` datetime DEFAULT NULL,
  `shipping_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `payments_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK43fa78qvypbsl3cosexxwmpti` (`payments_id`),
  KEY `FK1hx9xau7q5xwgxjtq63lr7eeg` (`shipping_id`),
  KEY `FKj86u1x7csa8yd68ql2y1ibrou` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_order`
--

LOCK TABLES `user_order` WRITE;
/*!40000 ALTER TABLE `user_order` DISABLE KEYS */;
INSERT INTO `user_order` VALUES (3,'2021-06-11 00:00:00','Done',797.00,'Paid','2021-06-16 00:00:00',1,2,1),(4,'2021-06-11 00:00:00','Done',224.00,'Paid','2021-06-16 00:00:00',2,3,2),(5,'2021-06-13 00:00:00','Done',168.00,'Paid','2021-06-18 00:00:00',3,2,3),(6,'2021-06-13 00:00:00','Done',65.00,'Paid','2021-06-18 00:00:00',4,3,4),(7,'2021-06-13 00:00:00','In Progress',56.00,'Unpaid','2021-06-18 00:00:00',5,2,5),(8,'2021-06-13 00:00:00','To Ship',760.00,'Paid','2021-06-18 00:00:00',6,4,6),(9,'2021-06-13 00:00:00','To Ship',224.00,'Paid','2021-06-18 00:00:00',7,4,7),(10,'2021-06-13 00:00:00','To Ship',76.00,'Paid','2021-06-18 00:00:00',8,2,8),(11,'2021-06-15 00:00:00','In Progress',129.00,'Unpaid','2021-06-20 00:00:00',9,2,9),(12,'2021-06-15 00:00:00','In Progress',43.00,'Unpaid','2021-06-20 00:00:00',10,2,10),(13,'2021-06-15 00:00:00','In Progress',23.00,'Unpaid','2021-06-20 00:00:00',11,2,11);
/*!40000 ALTER TABLE `user_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-30 15:39:03
