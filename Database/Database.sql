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
  `name_authorities` varchar(45) NOT NULL,
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
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `idCarts` int NOT NULL AUTO_INCREMENT,
  `idProduct` int NOT NULL,
  `quantity` int DEFAULT NULL,
  `price_total` double DEFAULT NULL,
  PRIMARY KEY (`idCarts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `idCategorie` int NOT NULL AUTO_INCREMENT,
  `nameCategorie` varchar(999) NOT NULL,
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
-- Table structure for table `colors_products`
--

DROP TABLE IF EXISTS `colors_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colors_products` (
  `idcolors_products` int NOT NULL AUTO_INCREMENT,
  `idProduct` int NOT NULL,
  `color` varchar(200) NOT NULL,
  PRIMARY KEY (`idcolors_products`),
  KEY `corlor_Product_f_idx` (`idProduct`),
  CONSTRAINT `corlor_Product_f` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colors_products`
--

LOCK TABLES `colors_products` WRITE;
/*!40000 ALTER TABLE `colors_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `colors_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `idcomment` int NOT NULL AUTO_INCREMENT,
  `Content` varchar(999) DEFAULT NULL,
  `idCustomer` int NOT NULL,
  `idProduct` int NOT NULL,
  PRIMARY KEY (`idcomment`),
  KEY `F_Customer_Comment_idx` (`idCustomer`),
  KEY `F_Product_Comment_idx` (`idProduct`),
  CONSTRAINT `F_Customer_Comment` FOREIGN KEY (`idCustomer`) REFERENCES `customers` (`idCustomer`),
  CONSTRAINT `F_Product_Comment` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `idCustomer` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `password` varchar(999) NOT NULL,
  `Name` varchar(999) DEFAULT NULL,
  `Address` varchar(999) DEFAULT NULL,
  `Email` varchar(255) NOT NULL,
  `NumberPhone` varchar(10) NOT NULL,
  `id_role_member` int DEFAULT NULL,
  `isDelete` varchar(1) DEFAULT NULL,
  `isActive` varchar(1) DEFAULT NULL,
  `creare_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `image` varchar(999) DEFAULT NULL,
  PRIMARY KEY (`idCustomer`),
  KEY `F_rold_member_idx` (`id_role_member`),
  CONSTRAINT `F_role_member` FOREIGN KEY (`id_role_member`) REFERENCES `role_nembers` (`id_role_nember`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'leminhlongit','bdMaYE4qGgQwgql4mNjEmaihy6U=','Lê Minh Long','ấp Phú Thuận, xã Châu Hòa, huyện Giồng Trôm, tỉnh Bến Tre','leminhlongit@gmail.com','0374781483',NULL,'0','1','2023-03-03 12:03:54',NULL);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail_import_coupons`
--

DROP TABLE IF EXISTS `detail_import_coupons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detail_import_coupons` (
  `idDetail_import_coupon` int NOT NULL AUTO_INCREMENT,
  `idImportCoupon` int NOT NULL,
  `idProduct` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`idDetail_import_coupon`),
  KEY `F_import_coupon_idx` (`idImportCoupon`),
  KEY `F_idProduct_idx` (`idProduct`),
  CONSTRAINT `F_idProduct` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`),
  CONSTRAINT `F_import_coupon` FOREIGN KEY (`idImportCoupon`) REFERENCES `import_coupons` (`idImportCoupon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_import_coupons`
--

LOCK TABLES `detail_import_coupons` WRITE;
/*!40000 ALTER TABLE `detail_import_coupons` DISABLE KEYS */;
/*!40000 ALTER TABLE `detail_import_coupons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discounts`
--

DROP TABLE IF EXISTS `discounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discounts` (
  `idDiscount` int NOT NULL AUTO_INCREMENT,
  `name_discount` varchar(999) DEFAULT NULL,
  `description` text,
  `code_Discount` varchar(999) DEFAULT NULL,
  `percentage` double DEFAULT NULL,
  `Minimum_product_quantity` int DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `Status` int DEFAULT NULL,
  `idProduct` int DEFAULT NULL,
  PRIMARY KEY (`idDiscount`),
  KEY `f_dis_count_idx` (`idProduct`),
  CONSTRAINT `f_dis_count` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discounts`
--

LOCK TABLES `discounts` WRITE;
/*!40000 ALTER TABLE `discounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `discounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `google_logins`
--

DROP TABLE IF EXISTS `google_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `google_logins` (
  `idgoogle_login` int NOT NULL,
  `name` varchar(999) DEFAULT NULL,
  `email` varchar(999) DEFAULT NULL,
  `idCustomer` int NOT NULL,
  `image` varchar(999) DEFAULT NULL,
  PRIMARY KEY (`idgoogle_login`),
  KEY `F_login_google_idx` (`idCustomer`),
  CONSTRAINT `F_login_google` FOREIGN KEY (`idCustomer`) REFERENCES `customers` (`idCustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `google_logins`
--

LOCK TABLES `google_logins` WRITE;
/*!40000 ALTER TABLE `google_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `google_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images_products`
--

DROP TABLE IF EXISTS `images_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images_products` (
  `idImageProduct` int NOT NULL AUTO_INCREMENT,
  `idProduct` int NOT NULL,
  `Image` varchar(999) DEFAULT NULL,
  PRIMARY KEY (`idImageProduct`),
  KEY `F_idProduct_idx` (`idProduct`),
  CONSTRAINT `F_imageProduct` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images_products`
--

LOCK TABLES `images_products` WRITE;
/*!40000 ALTER TABLE `images_products` DISABLE KEYS */;
INSERT INTO `images_products` VALUES (1,1,'vaylienthankids1.webp'),(2,1,'vaylienthankids2.webp'),(3,1,'vaylienthankids3.webp'),(4,1,'vaylienthankids4.webp'),(5,1,'vaylienthankids5.webp'),(6,1,'vaylienthankids6.webp'),(7,2,'giaythethaobuocday_F56_1.webp'),(8,2,'giaythethaobuocday_F56_2.webp'),(9,2,'giaythethaobuocday_F56_3.webp'),(10,2,'giaythethaobuocday_F56_4.webp'),(11,2,'giaythethaobuocday_F56_5.webp'),(12,2,'giaythethaobuocday_F56_6.webp'),(13,3,'kinhthoitranggap_1.webp'),(14,3,'kinhthoitranggap_2.webp'),(15,3,'kinhthoitranggap_3.webp'),(16,3,'kinhthoitranggap_4.webp'),(17,3,'kinhthoitranggap_5.webp'),(18,4,'damdutiecchobe_XK2_1.webp'),(19,4,'damdutiecchobe_XK2_2.webp'),(20,4,'damdutiecchobe_XK2_3.webp'),(21,4,'damdutiecchobe_XK2_4.webp'),(22,4,'damdutiecchobe_XK2_5.webp'),(23,5,'vailienthankids_1.webp'),(24,5,'vailienthankids_2.webp'),(25,5,'vailienthankids_3.webp'),(26,5,'vailienthankids_4.webp'),(27,5,'vailienthankids_5.webp'),(28,6,'tattreem_1.webp'),(29,6,'tattreem_2.webp'),(30,6,'tattreem_3.webp'),(31,6,'tattreem_4.webp'),(32,6,'tattreem_5.webp'),(33,7,'vaylienthankids_02_1.webp'),(34,7,'vaylienthankids_02_2.webp'),(35,7,'vaylienthankids_02_3.webp'),(36,7,'vaylienthankids_02_4.webp'),(37,7,'vaylienthankids_02_5.webp');
/*!40000 ALTER TABLE `images_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `import_coupons`
--

DROP TABLE IF EXISTS `import_coupons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `import_coupons` (
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
-- Dumping data for table `import_coupons`
--

LOCK TABLES `import_coupons` WRITE;
/*!40000 ALTER TABLE `import_coupons` DISABLE KEYS */;
/*!40000 ALTER TABLE `import_coupons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `idProduct` int NOT NULL,
  `quantity` int NOT NULL,
  `date_add` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idProduct`),
  CONSTRAINT `Finven_Product` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_facebooks`
--

DROP TABLE IF EXISTS `login_facebooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_facebooks` (
  `idlogin_facebook` int NOT NULL,
  `name` varchar(999) DEFAULT NULL,
  `email` varchar(999) DEFAULT NULL,
  `numberphone` varchar(10) DEFAULT NULL,
  `idCustomer` int NOT NULL,
  `image` varchar(999) DEFAULT NULL,
  PRIMARY KEY (`idlogin_facebook`),
  KEY `F_Custommer_idx` (`idCustomer`),
  CONSTRAINT `F_Custommer` FOREIGN KEY (`idCustomer`) REFERENCES `customers` (`idCustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_facebooks`
--

LOCK TABLES `login_facebooks` WRITE;
/*!40000 ALTER TABLE `login_facebooks` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_facebooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logs` (
  `idLog` int NOT NULL AUTO_INCREMENT,
  `level` int DEFAULT NULL,
  `ip` varchar(999) DEFAULT NULL,
  `idCustomer` int DEFAULT NULL,
  `src` varchar(999) DEFAULT NULL,
  `content` varchar(999) DEFAULT NULL,
  `create_add` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`idLog`),
  KEY `flog_idx` (`idCustomer`),
  CONSTRAINT `flog_cus` FOREIGN KEY (`idCustomer`) REFERENCES `customers` (`idCustomer`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
INSERT INTO `logs` VALUES (1,0,'0:0:0:0:0:0:0:1',NULL,'Login','','2023-03-04 12:33:20',0),(2,2,'0:0:0:0:0:0:0:1',NULL,'Login LOGIN FALSE','LOGIN FALSE: USER - leminhlong@gmail.com','2023-03-04 12:33:24',0),(3,2,'0:0:0:0:0:0:0:1',NULL,'Login LOGIN FALSE','LOGIN FALSE: USER - leminhlong@gmail.com','2023-03-04 12:34:04',0);
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id_orders` int NOT NULL AUTO_INCREMENT,
  `dateAdd` datetime DEFAULT NULL,
  `deliveryDate` datetime DEFAULT NULL,
  `StatusPay` varchar(255) DEFAULT NULL,
  `idCustomer` int NOT NULL,
  `endow` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `address` varchar(999) DEFAULT NULL,
  PRIMARY KEY (`id_orders`),
  KEY `F_Customer_Bill_idx` (`idCustomer`),
  CONSTRAINT `F_Customer_Bill` FOREIGN KEY (`idCustomer`) REFERENCES `customers` (`idCustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_details`
--

DROP TABLE IF EXISTS `orders_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_details` (
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
  CONSTRAINT `F_DetailBill_Bill` FOREIGN KEY (`idBill`) REFERENCES `orders` (`id_orders`),
  CONSTRAINT `F_Product_DetailBill` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_details`
--

LOCK TABLES `orders_details` WRITE;
/*!40000 ALTER TABLE `orders_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders_details` ENABLE KEYS */;
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
  `isActive` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`idProducer`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producers`
--

LOCK TABLES `producers` WRITE;
/*!40000 ALTER TABLE `producers` DISABLE KEYS */;
INSERT INTO `producers` VALUES (1,'Gap','https://www.gap.com/',NULL,'1'),(2,'Caters','https://www.carters.com/',NULL,'1'),(3,'UNIQLO','https://www.uniqlo.com',NULL,'1');
/*!40000 ALTER TABLE `producers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `idProduct` int NOT NULL AUTO_INCREMENT,
  `nameProduct` varchar(999) DEFAULT NULL,
  `priceProduct` double DEFAULT NULL,
  `description` varchar(999) DEFAULT NULL,
  `id_view` int DEFAULT NULL,
  `id_star` double DEFAULT NULL,
  `Comment` varchar(999) DEFAULT NULL,
  `idSupplier` double NOT NULL,
  `idProducer` int NOT NULL,
  `idCategorie` int NOT NULL,
  `isActive` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`idProduct`),
  KEY `F_idSupplier_idx` (`idSupplier`),
  KEY `F_idProducer_idx` (`idProducer`),
  KEY `F_idCategorie_idx` (`idCategorie`),
  CONSTRAINT `F_idCategorie` FOREIGN KEY (`idCategorie`) REFERENCES `categories` (`idCategorie`),
  CONSTRAINT `F_idProducer` FOREIGN KEY (`idProducer`) REFERENCES `producers` (`idProducer`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Váy liên thân KIDS - KF5',250000,'Sản phẩm có 4 size, phù hợp cho bé gái từ 8 tháng đến 2 tuổi. Thích hợp cho các bé mặc đi học, đi chơi hay dạo phố cùng gia đình... Là món quà đầy ý nghĩa mà mẹ dành cho bé',NULL,NULL,NULL,1,1,11,'1'),(2,'Giày Thể Thao Buộc Dây - F56',450000,'Giày thể thao buộc dây cho bé trai và bé gái G19 được thiết kế rất khỏe khoắn, đẹp mắt phù hợp với sự phát triển hệ vận động của trẻ.  Size: 21 - 25 Màu sắc: Xanh Chất liệu: Da PU mềm, đế dẻo chống trơn trượt tuyệt đối Xuất xứ: Việt Nam Mũi...',NULL,NULL,NULL,1,1,5,'1'),(3,'Kính thời trang Gap',325000,'Với thiết kế thời trang, sành điệu và cá tính kính mắt  của thương hiệu Caters sẽ giúp cho đôi mắt của bạn thêm cuốn hút và đẹp hơn mỗi ngày.Kính mắt Caters 8021 có gọng được làm từ nhựa cao cấp, chắc chắn, có độ cong vừa phải, ôm sát khuôn...',NULL,NULL,NULL,2,2,13,'1'),(4,'Đầm dự tiệc cho bé - XK2',250000,'Sản phẩm có 4 size, phù hợp cho bé gái từ 8 tháng đến 2 tuổi. Thích hợp cho các bé mặc đi học, đi chơi hay dạo phố cùng gia đình... Là món quà đầy ý nghĩa mà mẹ dành cho bé',NULL,NULL,NULL,3,1,11,'1'),(5,'Váy liên thân KIDS',220000,'Sản phẩm có 4 size, phù hợp cho bé gái từ 8 tháng đến 2 tuổi. Thích hợp cho các bé mặc đi học, đi chơi hay dạo phố cùng gia đình... Là món quà đầy ý nghĩa mà mẹ dành cho bé',NULL,NULL,NULL,3,1,11,'1'),(6,'Tất trẻ em UNIQLO',25000,'Thời trang tất hàn quốc xuất khẩu Beauty Chipi chuyên bán lẻ, bán sỉ, bán nguyên lô Thời trang xuất khẩu Việt Nam Xuất Khấu. mẫu mã hết sức đẹp và phù hợp với lứa tuổi cho bé từ 1-12 tuổi. Các sản phẩm của chúng tôi được lựa chọn...',NULL,NULL,NULL,4,3,13,'1'),(7,'Váy liên thân KIDS-02',250000,'Sản phẩm có 4 size, phù hợp cho bé gái từ 8 tháng đến 2 tuổi. Thích hợp cho các bé mặc đi học, đi chơi hay dạo phố cùng gia đình... Là món quà đầy ý nghĩa mà mẹ dành cho bé',NULL,NULL,NULL,4,1,11,'1');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
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
  `note` varchar(999) DEFAULT NULL,
  PRIMARY KEY (`idrole_member_authorities`),
  KEY `F_authorities_idx` (`id_authorities`),
  CONSTRAINT `F_authorities` FOREIGN KEY (`id_authorities`) REFERENCES `authorities` (`id_authorities`),
  CONSTRAINT `F_rold_member_1` FOREIGN KEY (`idrole_member_authorities`) REFERENCES `role_nembers` (`id_role_nember`)
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
-- Table structure for table `role_nembers`
--

DROP TABLE IF EXISTS `role_nembers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_nembers` (
  `id_role_nember` int NOT NULL AUTO_INCREMENT,
  `Name_role` varchar(255) DEFAULT NULL,
  `Endow` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_role_nember`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_nembers`
--

LOCK TABLES `role_nembers` WRITE;
/*!40000 ALTER TABLE `role_nembers` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_nembers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sizes_products`
--

DROP TABLE IF EXISTS `sizes_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sizes_products` (
  `idsizes_products` int NOT NULL AUTO_INCREMENT,
  `idProduct` int NOT NULL,
  `size` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idsizes_products`),
  KEY `f_size_product_idx` (`idProduct`),
  CONSTRAINT `f_size_product` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sizes_products`
--

LOCK TABLES `sizes_products` WRITE;
/*!40000 ALTER TABLE `sizes_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `sizes_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stars`
--

DROP TABLE IF EXISTS `stars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stars` (
  `idstar` int NOT NULL AUTO_INCREMENT,
  `idCustomer` int NOT NULL,
  `idProduct` int NOT NULL,
  `NumberStar` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idstar`),
  KEY `F_Customer_Star_idx` (`idCustomer`),
  KEY `F_Product_Star_idx` (`idProduct`),
  CONSTRAINT `F_Customer_Star` FOREIGN KEY (`idCustomer`) REFERENCES `customers` (`idCustomer`),
  CONSTRAINT `F_Product_Star` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stars`
--

LOCK TABLES `stars` WRITE;
/*!40000 ALTER TABLE `stars` DISABLE KEYS */;
/*!40000 ALTER TABLE `stars` ENABLE KEYS */;
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
-- Table structure for table `views`
--

DROP TABLE IF EXISTS `views`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `views` (
  `idView` int NOT NULL AUTO_INCREMENT,
  `idProduct` int NOT NULL,
  `Status` varchar(999) DEFAULT NULL,
  `idCustomer` int NOT NULL,
  PRIMARY KEY (`idView`),
  KEY `F_Custommer_View_idx` (`idCustomer`),
  KEY `F_Product_View_idx` (`idProduct`),
  CONSTRAINT `F_Custommer_View` FOREIGN KEY (`idCustomer`) REFERENCES `customers` (`idCustomer`),
  CONSTRAINT `F_Product_View` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `views`
--

LOCK TABLES `views` WRITE;
/*!40000 ALTER TABLE `views` DISABLE KEYS */;
/*!40000 ALTER TABLE `views` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-04 12:38:27
