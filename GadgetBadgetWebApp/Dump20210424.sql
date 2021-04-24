-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: accountsdb
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `accountID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`accountID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'ravindu','vikum','ravindusundeepa100@gmail.com','ravindu1234');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carddetails`
--

DROP TABLE IF EXISTS `carddetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carddetails` (
  `accountId` int(11) NOT NULL,
  `cardType` varchar(45) DEFAULT NULL,
  `nameOnCard` varchar(45) DEFAULT NULL,
  `cardNo` varchar(45) DEFAULT NULL,
  `expireDate` varchar(45) DEFAULT NULL,
  `cvv` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`accountId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carddetails`
--

LOCK TABLES `carddetails` WRITE;
/*!40000 ALTER TABLE `carddetails` DISABLE KEYS */;
INSERT INTO `carddetails` VALUES (1,'credit','A R V S Fernando','4123 2345 2467 3598','09/25','667'),(2,'credit','P D U Shanon','4134 4975 6745 3593','08/25','564');
/*!40000 ALTER TABLE `carddetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shippingdetails`
--

DROP TABLE IF EXISTS `shippingdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shippingdetails` (
  `accountID` int(11) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `userName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address1` varchar(200) DEFAULT NULL,
  `address2` varchar(200) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zipCode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`accountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shippingdetails`
--

LOCK TABLES `shippingdetails` WRITE;
/*!40000 ALTER TABLE `shippingdetails` DISABLE KEYS */;
INSERT INTO `shippingdetails` VALUES (1,'Ravindu','Vikum','RavinduSandeepa','ravindusundeepa100@gmail.com','3/64 paris perera road ja-ela','4/7A samagi mw kandawaththa maeliya ja-ela','srilanka','western','11350'),(2,'Udith','Shanon','UdithShanon','shanonpdu@gmail.com','4/56 balasuriya mw kandana','3/23D rajamawatha road kanuwana ja-ela','srilanka','western','11320');
/*!40000 ALTER TABLE `shippingdetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-24 12:10:11
