-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: schooldb
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Doru Chete','1 Mai, nr. 5','cdorug@gmail.com',21),(2,'George Miscunescu','Independentei, nr. 26','g.micsu@gmail.com',21),(3,'Florin Pop','Viilor, nr. 17','florin.p23@gmail.com',42),(4,'Victor Popescu','1 Decembrie, nr. 77','victor.p88@gmail.com',34),(5,'Stefan Ionescu','Marasti, nr. 14','stef.ionescu@gmail.com',41),(6,'Sorin Dumitrescu','Parcului, nr. 2','sorin.dum@gmail.com',55),(7,'Paul Sorescu','Traian, nr. 18','paul.s982@gmail.com',46),(8,'Ioan Petrescu','Somesului, nr. 87','petrescu.i@gmail.com',48),(9,'Tudor Niculescu','Teiului, nr. 66','tudor.nic@gmail.com',51),(10,'Laura Ionescu','Kogalniceanu, nr. 82','laura.i22@gmail.com',27),(11,'Mihaela Popescu','Unirii, nr. 8','mihaela.p31@gmail.com',44),(12,'Ada Florescu','Republicii, nr. 22','ada.f22@yahoo.com',43),(13,'Dan Georgescu','Plopilor, nr. 32','d.georgescu21@yahoo.com',41),(14,'Marina Moisescu','Mihai Viteazu, nr. 8','marina.m55@gmai.com',38),(15,'George Georgescu','Mesteacanului, nr. 9','george.g99@gmail.com',22),(16,'Ioan Bibescu','Gutaiului, nr. 9','bibescu.ioan23@gmail.com',62),(17,'Andrei Retean','Mehedinti, nr. 92','andrei.r87@gmail.com',36),(18,'Andreia Radulescu','Bistritei, nr. 66','andreia.r90@gmail.com',32),(19,'Dan Niculescu','24 Ianuarie, nr. 6','dan.nic23@gmail.com',42),(20,'Ioan Constantinescu','1 Mai, nr. 6','i.constantinescu22@gmail.com',55);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL,
  `clientId` int NOT NULL,
  `productId` int NOT NULL,
  `orderedQuantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_clientid_idx` (`clientId`),
  KEY `fk_productid_idx` (`productId`),
  CONSTRAINT `fk_clientid` FOREIGN KEY (`clientId`) REFERENCES `client` (`id`),
  CONSTRAINT `fk_productid` FOREIGN KEY (`productId`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,2,1),(2,1,1,1),(3,1,9,1),(4,1,9,1),(5,1,8,1),(6,1,8,1),(7,6,1,1),(8,5,1,2),(9,1,8,1),(12,6,3,2),(13,4,6,2),(14,9,5,2),(15,1,12,2),(16,13,13,1),(17,14,13,2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `productName` varchar(45) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Crime and Punishment',29,51),(2,'Ulysses',9,25),(3,'Don Quixote',0,60),(4,'The Great Gatsby',5,55),(5,'Moby Dick',9,40),(6,'War and Peace',0,20),(7,'The Brothers Karamazov',1,80),(8,'Anna Karenina',0,45),(9,'Heart of Darkness',1,75),(10,'1984',5,42),(11,'The Grapes of Wrath',4,92),(12,'To Kill a Mockingbird',8,82),(13,'Around the World in 80 Days',5,40),(14,'For Whom The Bell Tolls',5,50);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'schooldb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-05 20:36:09
