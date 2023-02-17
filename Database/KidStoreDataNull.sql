-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: kidstore
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `id_authorities` int NOT NULL AUTO_INCREMENT,
  `name_authorities` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_authorities`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `idBill` int NOT NULL AUTO_INCREMENT,
  `dateAdd` datetime DEFAULT NULL,
  `deliveryDate` datetime DEFAULT NULL,
  `StatusPay` varchar(255) DEFAULT NULL,
  `idCustomer` int NOT NULL,
  `endow` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `address` varchar(999) DEFAULT NULL,
  PRIMARY KEY (`idBill`),
  KEY `F_Customer_Bill_idx` (`idCustomer`),
  CONSTRAINT `F_Customer_Bill` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`idCustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_detail`
--

DROP TABLE IF EXISTS `bill_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_detail` (
  `idBill_detail` int NOT NULL AUTO_INCREMENT,
  `idBill` int NOT NULL,
  `idProduct` int NOT NULL,
  `nameProduct` varchar(999) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `node` varchar(999) DEFAULT NULL,
  PRIMARY KEY (`idBill_detail`),
  KEY `F_DetailBill_Bill_idx` (`idBill`),
  KEY `F_Product_DetailBill_idx` (`idProduct`),
  CONSTRAINT `F_DetailBill_Bill` FOREIGN KEY (`idBill`) REFERENCES `bill` (`idBill`),
  CONSTRAINT `F_Product_DetailBill` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_detail`
--

LOCK TABLES `bill_detail` WRITE;
/*!40000 ALTER TABLE `bill_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `idCategorie` int NOT NULL AUTO_INCREMENT,
  `nameCategorie` varchar(999) DEFAULT NULL,
  PRIMARY KEY (`idCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `idcomment` int NOT NULL AUTO_INCREMENT,
  `Content` varchar(999) DEFAULT NULL,
  `idCustomer` int NOT NULL,
  `idProduct` int NOT NULL,
  PRIMARY KEY (`idcomment`),
  KEY `F_Customer_Comment_idx` (`idCustomer`),
  KEY `F_Product_Comment_idx` (`idProduct`),
  CONSTRAINT `F_Customer_Comment` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`idCustomer`),
  CONSTRAINT `F_Product_Comment` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `idCustomer` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(999) DEFAULT NULL,
  `Name` varchar(999) DEFAULT NULL,
  `Address` varchar(999) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `NumberPhone` varchar(10) NOT NULL,
  `id_role_member` int DEFAULT NULL,
  PRIMARY KEY (`idCustomer`),
  KEY `F_rold_member_idx` (`id_role_member`),
  CONSTRAINT `F_role_member` FOREIGN KEY (`id_role_member`) REFERENCES `role_nember` (`id_role_nember`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail_import_coupon`
--

DROP TABLE IF EXISTS `detail_import_coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detail_import_coupon` (
  `idDetail_import_coupon` int NOT NULL AUTO_INCREMENT,
  `idImportCoupon` int NOT NULL,
  `idProduct` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`idDetail_import_coupon`),
  KEY `F_import_coupon_idx` (`idImportCoupon`),
  KEY `F_idProduct_idx` (`idProduct`),
  CONSTRAINT `F_idProduct` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`),
  CONSTRAINT `F_import_coupon` FOREIGN KEY (`idImportCoupon`) REFERENCES `importcoupon` (`idImportCoupon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_import_coupon`
--

LOCK TABLES `detail_import_coupon` WRITE;
/*!40000 ALTER TABLE `detail_import_coupon` DISABLE KEYS */;
/*!40000 ALTER TABLE `detail_import_coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `google_login`
--

DROP TABLE IF EXISTS `google_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `google_login` (
  `idgoogle_login` int NOT NULL,
  `name` varchar(999) DEFAULT NULL,
  `email` varchar(999) DEFAULT NULL,
  `idCustomer` int NOT NULL,
  PRIMARY KEY (`idgoogle_login`),
  KEY `F_login_google_idx` (`idCustomer`),
  CONSTRAINT `F_login_google` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`idCustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `google_login`
--

LOCK TABLES `google_login` WRITE;
/*!40000 ALTER TABLE `google_login` DISABLE KEYS */;
/*!40000 ALTER TABLE `google_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imageproduct`
--

DROP TABLE IF EXISTS `imageproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imageproduct` (
  `idImageProduct` int NOT NULL AUTO_INCREMENT,
  `idProduct` int NOT NULL,
  `Image` varchar(999) DEFAULT NULL,
  PRIMARY KEY (`idImageProduct`),
  KEY `F_idProduct_idx` (`idProduct`),
  CONSTRAINT `F_imageProduct` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imageproduct`
--

LOCK TABLES `imageproduct` WRITE;
/*!40000 ALTER TABLE `imageproduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `imageproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `importcoupon`
--

DROP TABLE IF EXISTS `importcoupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `importcoupon` (
  `idImportCoupon` int NOT NULL AUTO_INCREMENT,
  `idSupplier` int NOT NULL,
  `date` datetime DEFAULT NULL,
  `Status` varchar(999) DEFAULT NULL,
  PRIMARY KEY (`idImportCoupon`),
  KEY `FSuppliers_idx` (`idSupplier`),
  CONSTRAINT `FSuppliers` FOREIGN KEY (`idSupplier`) REFERENCES `suppliers` (`idSupplier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `importcoupon`
--

LOCK TABLES `importcoupon` WRITE;
/*!40000 ALTER TABLE `importcoupon` DISABLE KEYS */;
/*!40000 ALTER TABLE `importcoupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_facebook`
--

DROP TABLE IF EXISTS `login_facebook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_facebook` (
  `idlogin_facebook` int NOT NULL,
  `name` varchar(999) DEFAULT NULL,
  `email` varchar(999) DEFAULT NULL,
  `numberphone` varchar(10) DEFAULT NULL,
  `idCustomer` int DEFAULT NULL,
  PRIMARY KEY (`idlogin_facebook`),
  KEY `F_Custommer_idx` (`idCustomer`),
  CONSTRAINT `F_Custommer` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`idCustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_facebook`
--

LOCK TABLES `login_facebook` WRITE;
/*!40000 ALTER TABLE `login_facebook` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_facebook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producers`
--

DROP TABLE IF EXISTS `producers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producers` (
  `idProducer` int NOT NULL AUTO_INCREMENT,
  `nameProducer` varchar(100) DEFAULT NULL,
  `Information` varchar(999) DEFAULT NULL,
  `Logo` varchar(999) DEFAULT NULL,
  PRIMARY KEY (`idProducer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producers`
--

LOCK TABLES `producers` WRITE;
/*!40000 ALTER TABLE `producers` DISABLE KEYS */;
/*!40000 ALTER TABLE `producers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `idProduct` int NOT NULL AUTO_INCREMENT,
  `nameProduct` varchar(999) DEFAULT NULL,
  `priceProduct` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `dateAdd` datetime DEFAULT NULL,
  `description` varchar(999) DEFAULT NULL,
  `quantity_available` int DEFAULT NULL,
  `view` int DEFAULT NULL,
  `Star` int DEFAULT NULL,
  `Comment` varchar(999) DEFAULT NULL,
  `products_sold` int DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `idSupplier` int NOT NULL,
  `idProducer` int NOT NULL,
  `idCategorie` int NOT NULL,
  `isActive` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`idProduct`),
  KEY `F_idSupplier_idx` (`idSupplier`),
  KEY `F_idProducer_idx` (`idProducer`),
  KEY `F_idCategorie_idx` (`idCategorie`),
  CONSTRAINT `F_idCategorie` FOREIGN KEY (`idCategorie`) REFERENCES `categories` (`idCategorie`),
  CONSTRAINT `F_idProducer` FOREIGN KEY (`idProducer`) REFERENCES `producers` (`idProducer`),
  CONSTRAINT `F_idSupplier` FOREIGN KEY (`idSupplier`) REFERENCES `suppliers` (`idSupplier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_member_authorities`
--

DROP TABLE IF EXISTS `role_member_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_member_authorities` (
  `idrole_member_authorities` int NOT NULL AUTO_INCREMENT,
  `id_authorities` int DEFAULT NULL,
  `node` varchar(999) DEFAULT NULL,
  PRIMARY KEY (`idrole_member_authorities`),
  KEY `F_authorities_idx` (`id_authorities`),
  CONSTRAINT `F_authorities` FOREIGN KEY (`id_authorities`) REFERENCES `authorities` (`id_authorities`),
  CONSTRAINT `F_rold_member_1` FOREIGN KEY (`idrole_member_authorities`) REFERENCES `role_nember` (`id_role_nember`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_member_authorities`
--

LOCK TABLES `role_member_authorities` WRITE;
/*!40000 ALTER TABLE `role_member_authorities` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_member_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_nember`
--

DROP TABLE IF EXISTS `role_nember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_nember` (
  `id_role_nember` int NOT NULL AUTO_INCREMENT,
  `Name_role` varchar(255) DEFAULT NULL,
  `Endow` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_role_nember`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_nember`
--

LOCK TABLES `role_nember` WRITE;
/*!40000 ALTER TABLE `role_nember` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_nember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `star`
--

DROP TABLE IF EXISTS `star`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `star` (
  `idassess` int NOT NULL AUTO_INCREMENT,
  `idCustomer` int NOT NULL,
  `idProduct` int NOT NULL,
  `NumberStar` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idassess`),
  KEY `F_Customer_Star_idx` (`idCustomer`),
  KEY `F_Product_Star_idx` (`idProduct`),
  CONSTRAINT `F_Customer_Star` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`idCustomer`),
  CONSTRAINT `F_Product_Star` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `star`
--

LOCK TABLES `star` WRITE;
/*!40000 ALTER TABLE `star` DISABLE KEYS */;
/*!40000 ALTER TABLE `star` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers` (
  `idSupplier` int NOT NULL AUTO_INCREMENT,
  `nameSupplier` varchar(999) DEFAULT NULL,
  `emailSupplier` varchar(255) DEFAULT NULL,
  `numberPhone` varchar(10) DEFAULT NULL,
  `isActive` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`idSupplier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view`
--

DROP TABLE IF EXISTS `view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `view` (
  `idView` int NOT NULL AUTO_INCREMENT,
  `idProduct` int NOT NULL,
  `Status` varchar(999) DEFAULT NULL,
  `idCustomer` int NOT NULL,
  PRIMARY KEY (`idView`),
  KEY `F_Custommer_View_idx` (`idCustomer`),
  KEY `F_Product_View_idx` (`idProduct`),
  CONSTRAINT `F_Custommer_View` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`idCustomer`),
  CONSTRAINT `F_Product_View` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view`
--

LOCK TABLES `view` WRITE;
/*!40000 ALTER TABLE `view` DISABLE KEYS */;
/*!40000 ALTER TABLE `view` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-17 15:42:42
