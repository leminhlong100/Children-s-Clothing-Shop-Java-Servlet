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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Giày thể thao bé trai'),(2,'Set đồ bé trai'),(3,'Giày thể thao bé gái'),(4,'Set đồ bé gái'),(5,'Giày/Dép bé trai'),(6,'Giày/Dép bé gái'),(7,'Áo bé trai'),(8,'Áo bé gái'),(9,'Quần bé trai'),(10,'Quần bé gái'),(11,'Đầm\\Váy'),(12,'Đồ chơi trẻ em'),(13,'Đồ cho cả bé gái và bé trai');
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
  `isDelete` varchar(1) DEFAULT NULL,
  `isActice` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`idCustomer`),
  KEY `F_rold_member_idx` (`id_role_member`),
  CONSTRAINT `F_role_member` FOREIGN KEY (`id_role_member`) REFERENCES `role_nember` (`id_role_nember`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'leminhlongit','bdMaYE4qGgQwgql4mNjEmaihy6U=','Lê Minh Long','ấp Phú Thuận, xã Châu Hòa, huyện Giồng Trôm, tỉnh Bến Tre','leminhlongit@gmail.com','0374781483',NULL,'0','1');
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
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imageproduct`
--

LOCK TABLES `imageproduct` WRITE;
/*!40000 ALTER TABLE `imageproduct` DISABLE KEYS */;
INSERT INTO `imageproduct` VALUES (1,1,'vaylienthankids1.webp'),(2,1,'vaylienthankids2.webp'),(3,1,'vaylienthankids3.webp'),(4,1,'vaylienthankids4.webp'),(5,1,'vaylienthankids5.webp'),(6,1,'vaylienthankids6.webp'),(7,2,'giaythethaobuocday_F56_1.webp'),(8,2,'giaythethaobuocday_F56_2.webp'),(9,2,'giaythethaobuocday_F56_3.webp'),(10,2,'giaythethaobuocday_F56_4.webp'),(11,2,'giaythethaobuocday_F56_5.webp'),(12,2,'giaythethaobuocday_F56_6.webp'),(13,3,'kinhthoitranggap_1.webp'),(14,3,'kinhthoitranggap_2.webp'),(15,3,'kinhthoitranggap_3.webp'),(16,3,'kinhthoitranggap_4.webp'),(17,3,'kinhthoitranggap_5.webp'),(18,4,'damdutiecchobe_XK2_1.webp'),(19,4,'damdutiecchobe_XK2_2.webp'),(20,4,'damdutiecchobe_XK2_3.webp'),(21,4,'damdutiecchobe_XK2_4.webp'),(22,4,'damdutiecchobe_XK2_5.webp'),(23,5,'vailienthankids_1.webp'),(24,5,'vailienthankids_2.webp'),(25,5,'vailienthankids_3.webp'),(26,5,'vailienthankids_4.webp'),(27,5,'vailienthankids_5.webp'),(28,6,'tattreem_1.webp'),(29,6,'tattreem_2.webp'),(30,6,'tattreem_3.webp'),(31,6,'tattreem_4.webp'),(32,6,'tattreem_5.webp'),(33,7,'vaylienthankids_02_1.webp'),(34,7,'vaylienthankids_02_2.webp'),(35,7,'vaylienthankids_02_3.webp'),(36,7,'vaylienthankids_02_4.webp'),(37,7,'vaylienthankids_02_5.webp'),(38,8,'somikesocthoitrangchobe_03_1.webp'),(39,8,'somikesocthoitrangchobe_03_2.webp'),(40,8,'somikesocthoitrangchobe_03_3.webp'),(41,8,'somikesocthoitrangchobe_03_4.webp'),(42,8,'somikesocthoitrangchobe_03_5.webp'),(43,9,'somikesocthoitrangchobe_03_2.webp'),(44,9,'somikesocthoitrangchobe_03_1.webp'),(45,9,'somikesocthoitrangchobe_03_4.webp'),(46,9,'somikesocthoitrangchobe_03_3.webp'),(47,9,'somikesocthoitrangchobe_03_5.webp'),(48,10,'somikesocthoitrangchobe_03_3.webp'),(49,10,'somikesocthoitrangchobe_03_4.webp'),(50,10,'somikesocthoitrangchobe_03_2.webp'),(51,10,'somikesocthoitrangchobe_03_5.webp'),(52,10,'somikesocthoitrangchobe_03_1.webp'),(53,11,'giaythethaobuocday_05_1.webp'),(54,11,'giaythethaobuocday_05_2.webp'),(55,11,'giaythethaobuocday_05_3.webp'),(56,11,'giaythethaobuocday_05_4.webp'),(57,11,'giaythethaobuocday_05_5.webp'),(58,11,'giaythethaobuocday_05_6.webp'),(59,12,'giaythethaobuocday_05_2.webp'),(60,12,'giaythethaobuocday_05_4.webp'),(61,12,'giaythethaobuocday_05_5.webp'),(62,12,'giaythethaobuocday_05_1.webp'),(63,12,'giaythethaobuocday_05_6.webp'),(64,13,'kinhthoitranggap_2.webp'),(65,13,'kinhthoitranggap_4.webp'),(66,13,'kinhthoitranggap_1.webp'),(67,13,'kinhthoitranggap_3.webp'),(68,13,'kinhthoitranggap_5.webp');
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
  `isActice` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`idProducer`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producers`
--

LOCK TABLES `producers` WRITE;
/*!40000 ALTER TABLE `producers` DISABLE KEYS */;
INSERT INTO `producers` VALUES (1,'Gap','https://www.gap.com/',NULL,'1'),(2,'Caters','https://www.carters.com/',NULL,'1'),(3,'UNIQLO','https://www.uniqlo.com',NULL,'1'),(4,'KID-GAP','https://www.gap.com/',NULL,'1');
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
  `dateAdd` datetime DEFAULT NULL,
  `description` varchar(999) DEFAULT NULL,
  `quantity_available` int DEFAULT NULL,
  `view` int DEFAULT NULL,
  `Star` double DEFAULT NULL,
  `Comment` varchar(999) DEFAULT NULL,
  `products_sold` int DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `idSupplier` int NOT NULL,
  `idProducer` int NOT NULL,
  `idCategorie` int NOT NULL,
  `isActice` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`idProduct`),
  KEY `F_idSupplier_idx` (`idSupplier`),
  KEY `F_idProducer_idx` (`idProducer`),
  KEY `F_idCategorie_idx` (`idCategorie`),
  CONSTRAINT `F_idCategorie` FOREIGN KEY (`idCategorie`) REFERENCES `categories` (`idCategorie`),
  CONSTRAINT `F_idProducer` FOREIGN KEY (`idProducer`) REFERENCES `producers` (`idProducer`),
  CONSTRAINT `F_idSupplier` FOREIGN KEY (`idSupplier`) REFERENCES `suppliers` (`idSupplier`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Váy liên thân KIDS - KF5',250000,'2023-02-17 00:00:00','Sản phẩm có 4 size, phù hợp cho bé gái từ 8 tháng đến 2 tuổi. Thích hợp cho các bé mặc đi học, đi chơi hay dạo phố cùng gia đình... Là món quà đầy ý nghĩa mà mẹ dành cho bé',30,NULL,NULL,NULL,16,270000,1,1,11,'1'),(2,'Giày Thể Thao Buộc Dây - F56',450000,'2023-02-18 00:00:00','Giày thể thao buộc dây cho bé trai và bé gái G19 được thiết kế rất khỏe khoắn, đẹp mắt phù hợp với sự phát triển hệ vận động của trẻ.  Size: 21 - 25 Màu sắc: Xanh Chất liệu: Da PU mềm, đế dẻo chống trơn trượt tuyệt đối Xuất xứ: Việt Nam Mũi...',40,NULL,NULL,NULL,21,490000,1,1,5,'1'),(3,'Kính thời trang Gap',325000,'2023-02-18 00:00:00','Với thiết kế thời trang, sành điệu và cá tính kính mắt  của thương hiệu Caters sẽ giúp cho đôi mắt của bạn thêm cuốn hút và đẹp hơn mỗi ngày.Kính mắt Caters 8021 có gọng được làm từ nhựa cao cấp, chắc chắn, có độ cong vừa phải, ôm sát khuôn...',45,NULL,NULL,NULL,25,700000,2,2,13,'1'),(4,'Đầm dự tiệc cho bé - XK2',250000,'2023-02-18 00:00:00','Sản phẩm có 4 size, phù hợp cho bé gái từ 8 tháng đến 2 tuổi. Thích hợp cho các bé mặc đi học, đi chơi hay dạo phố cùng gia đình... Là món quà đầy ý nghĩa mà mẹ dành cho bé',70,NULL,NULL,NULL,37,275000,3,1,11,'1'),(5,'Váy liên thân KIDS',220000,'2023-02-18 00:00:00','Sản phẩm có 4 size, phù hợp cho bé gái từ 8 tháng đến 2 tuổi. Thích hợp cho các bé mặc đi học, đi chơi hay dạo phố cùng gia đình... Là món quà đầy ý nghĩa mà mẹ dành cho bé',45,NULL,NULL,NULL,28,275000,3,1,11,'1'),(6,'Tất trẻ em UNIQLO',25000,'2023-02-18 00:00:00','Thời trang tất hàn quốc xuất khẩu Beauty Chipi chuyên bán lẻ, bán sỉ, bán nguyên lô Thời trang xuất khẩu Việt Nam Xuất Khấu. mẫu mã hết sức đẹp và phù hợp với lứa tuổi cho bé từ 1-12 tuổi. Các sản phẩm của chúng tôi được lựa chọn...',45,NULL,NULL,NULL,43,30000,4,3,13,'1'),(7,'Váy liên thân KIDS-02',250000,'2023-02-18 00:00:00','Sản phẩm có 4 size, phù hợp cho bé gái từ 8 tháng đến 2 tuổi. Thích hợp cho các bé mặc đi học, đi chơi hay dạo phố cùng gia đình... Là món quà đầy ý nghĩa mà mẹ dành cho bé',100,NULL,NULL,NULL,2,275000,4,1,11,'1'),(8,'Sơ mi kẻ sọc thời trang cho bé-03',245000,'2023-02-19 00:00:00','Áo sơ mi cực đẹp cho bé trai diện kèm với quần jeans, quần kaki hay mix cùng áo pull là 3 gợi ý đơn giản mang lại phong cách thời trang sành điệu cho bé yêu đấy ạ! Chất vải Kate Oxford xuất dư cực đẹp, thoáng mát lên form...',50,NULL,NULL,NULL,26,280000,2,1,7,'1'),(9,'Sơ mi kẻ sọc thời trang cho bé - SK',245000,'2023-02-19 00:00:00','Áo sơ mi cực đẹp cho bé trai diện kèm với quần jeans, quần kaki hay mix cùng áo pull là 3 gợi ý đơn giản mang lại phong cách thời trang sành điệu cho bé yêu đấy ạ! Áo sơ mi kẻ sọc cho bé trai cá tính và khỏe...',70,NULL,NULL,NULL,38,280000,2,1,7,'1'),(10,'Sơ mi kẻ sọc thời trang cho bé',245000,'2023-02-19 00:00:00','- Kiểu áo sơ mi tay ngắn cực đẹp cho bé trai diện mùa thu đông vừa cá tính thời trang mà không lo bó sát, gây khó chịu cho bé. -Thiết kế tay ngắn, kể sọc mang đến cho bé trai vẻ ngoài cá tính và khỏe khoắn. - Áo với...',50,NULL,NULL,NULL,45,300000,3,1,7,'1'),(11,'Giày Thể Thao Buộc Dây -05',450000,'2023-02-19 00:00:00','Giày thể thao buộc dây cho bé trai và bé gái G19 được thiết kế rất khỏe khoắn, đẹp mắt phù hợp với sự phát triển hệ vận động của trẻ.  Size: 21 - 25 Màu sắc: Xanh Chất liệu: Da PU mềm, đế dẻo chống trơn trượt tuyệt đối Xuất xứ: Việt Nam Mũi...',200,NULL,NULL,NULL,120,500000,4,4,6,'1'),(12,'Giày Thể Thao Buộc Dây24',450000,'2023-02-18 00:00:00','Giày thể thao buộc dây cho bé trai và bé gái G19 được thiết kế rất khỏe khoắn, đẹp mắt phù hợp với sự phát triển hệ vận động của trẻ.  Size: 21 - 25 Màu sắc: Xanh Chất liệu: Da PU mềm, đế dẻo chống trơn trượt tuyệt đối Xuất xứ: Việt Nam Mũi...',70,NULL,NULL,NULL,40,500000,4,3,13,'1'),(13,'Kính thời trang Caters',325000,'2023-02-19 00:00:00','Với thiết kế thời trang, sành điệu và cá tính kính mắt  của thương hiệu Caters sẽ giúp cho đôi mắt của bạn thêm cuốn hút và đẹp hơn mỗi ngày',10,NULL,NULL,NULL,4,700000,2,2,13,'1');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES (1,'Yame','yameshop@gmail.com','0374781482','1'),(2,'Hoho','hoho@gmail.com','0345689678','1'),(3,'Mani','manishop@gmail.com','0876371452','1'),(4,'Kiro','kiroshop@gmail.com','0376417242','1');
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

-- Dump completed on 2023-02-19 17:14:46
