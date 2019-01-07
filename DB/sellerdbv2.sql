-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 10.0.10.41    Database: sellerdbv2
-- ------------------------------------------------------
-- Server version	5.6.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `_sequence`
--

DROP TABLE IF EXISTS `_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_sequence` (
  `seq_name` varchar(50) NOT NULL,
  `seq_group` varchar(10) NOT NULL,
  `seq_val` int(10) NOT NULL,
  PRIMARY KEY (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_sequence`
--

LOCK TABLES `_sequence` WRITE;
/*!40000 ALTER TABLE `_sequence` DISABLE KEYS */;
/*!40000 ALTER TABLE `_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_sequence_barcode`
--

DROP TABLE IF EXISTS `_sequence_barcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_sequence_barcode` (
  `id` int(11) NOT NULL,
  `contry_code` varchar(3) DEFAULT NULL COMMENT '- Nhóm 1: Từ trái sang phải, hai hoặc ba chữ số đầu là mã số về quốc gia (vùng lãnh thổ)',
  `company_code` varchar(6) DEFAULT NULL COMMENT '- Nhóm 2: Tiếp theo gồm bốn, năm hoặc sáu chữ số là mã số về doanh nghiệp.',
  `product_code_count` int(5) DEFAULT NULL COMMENT '- Nhóm 3: Tiếp theo gồm năm, bốn hoặc ba chữ số là mã số về hàng hóa.\n\n-tạo tự động +1 vi du 1,2,3,4,5',
  `product_length` int(2) DEFAULT NULL COMMENT 'độ dài mã +1 prodcut vd 00001 ,00002',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_sequence_barcode`
--

LOCK TABLES `_sequence_barcode` WRITE;
/*!40000 ALTER TABLE `_sequence_barcode` DISABLE KEYS */;
INSERT INTO `_sequence_barcode` VALUES (1,'692','0002',15,5);
/*!40000 ALTER TABLE `_sequence_barcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_about`
--

DROP TABLE IF EXISTS `tb_about`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_about` (
  `id` varchar(10) NOT NULL,
  `title` varchar(245) DEFAULT NULL,
  `description` text,
  `logo` varchar(45) DEFAULT NULL,
  `creator` varchar(45) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_about`
--

LOCK TABLES `tb_about` WRITE;
/*!40000 ALTER TABLE `tb_about` DISABLE KEYS */;
INSERT INTO `tb_about` VALUES ('about-001','GIỚI THIỆU','Seller','dfg','u11','2017-01-01 00:00:00','<p style=\"margin-top: 0px; margin-bottom: 10px; line-height: 1.75; color: #888888; font-family: Roboto, sans-serif; background-color: #ffffff; text-align: justify;\">Tốc độ ph&aacute;t triển nhanh ch&oacute;ng của c&aacute;c mạng viễn th&ocirc;ng c&ugrave;ng sự ph&aacute;t triển c&ocirc;ng nghệ ng&agrave;y c&agrave;ng cao trong những năm gần đ&acirc;y<img style=\"vertical-align: middle; float: right; height: 111px; width: 166px;\" src=\"http://shop.vivmall.vn/Svmall/upload/ckeditor/IMG_4205.jpg\" alt=\"\" />&nbsp;đang tạo ra một k&ecirc;nh b&aacute;n h&agrave;ng l&yacute; tưởng cho nhiều doanh nghiệp, v&agrave; thuận lợi cho Qu&yacute; kh&aacute;ch h&agrave;ng c&oacute; nhu cầu mua sắm tại nh&agrave;.&nbsp;V&igrave; thế, ch&uacute;ng t&ocirc;i đ&atilde; thiết lập một k&ecirc;nh mua sắm trực tuyến mang t&ecirc;n Vivmall (Vietnam Internet Virtual Mall) với đa dạng sản phẩm chất lượng v&agrave; gi&aacute; cả ph&ugrave; hợp.</p>\r\n<p style=\"margin-top: 0px; margin-bottom: 10px; line-height: 1.75; color: #888888; font-family: Roboto, sans-serif; background-color: #ffffff; text-align: justify;\">Từ c&aacute;c d&ograve;ng sản phẩm được gia c&ocirc;ng từ gỗ dừa như đồ d&ugrave;ng nh&agrave; bếp, đ&egrave;n ngủ&hellip; với thiết kế tinh tế, độc đ&aacute;o tạo kh&ocirc;ng gian ấm c&uacute;ng cho gia đ&igrave;nh nh&agrave; bạn.</p>\r\n<p style=\"margin-top: 0px; margin-bottom: 10px; line-height: 1.75; color: #888888; font-family: Roboto, sans-serif; background-color: #ffffff; text-align: justify;\"><img style=\"vertical-align: middle; float: left; height: 111px; width: 168px;\" src=\"http://shop.vivmall.vn/Svmall/upload/ckeditor/maybay.jpg\" alt=\"\" /></p>\r\n<p style=\"margin-top: 0px; margin-bottom: 10px; line-height: 1.75; color: #888888; font-family: Roboto, sans-serif; background-color: #ffffff; text-align: justify;\">Đến c&aacute;c sản phẩm c&ocirc;ng nghệ cao như loa Bluetooth với &acirc;m thanh trung thực, sống động; đồng hồ LED với d&acirc;y đeo l&agrave;m bằng cao su mềm, dẻo v&agrave; bền, nhiều m&agrave;u sắc thời trang.</p>\r\n<p style=\"margin-top: 0px; margin-bottom: 10px; line-height: 1.75; color: #888888; font-family: Roboto, sans-serif; background-color: #ffffff; text-align: justify;\">Ngo&agrave;i ra ch&uacute;ng t&ocirc;i c&ograve;n cung cấp tay cầm c&oacute; thể chơi được h&acirc;̀u h&ecirc;́t các game tr&ecirc;n h&ecirc;̣ đi&ecirc;̀u hành Android và iOS, m&aacute;y bay điều khiển từ xa được trang bị camera HD để c&oacute; h&igrave;nh ảnh v&agrave; video trong khi đang bay&hellip;</p>\r\n<p style=\"margin-top: 0px; margin-bottom: 10px; line-height: 1.75; color: #888888; font-family: Roboto, sans-serif; background-color: #ffffff; text-align: justify;\">&nbsp;</p>\r\n<p style=\"margin-top: 0px; margin-bottom: 10px; line-height: 1.75; color: #888888; font-family: Roboto, sans-serif; background-color: #ffffff; text-align: justify;\"><span style=\"font-weight: bold;\">Mua sắm dễ d&agrave;ng v&agrave; thuận tiện:</span></p>\r\n<p style=\"margin-top: 0px; margin-bottom: 10px; line-height: 1.75; color: #888888; font-family: Roboto, sans-serif; background-color: #ffffff; text-align: justify;\">Kh&ocirc;ng c&ograve;n phải lo kẹt xe, đ&ocirc;ng đ&uacute;c v&agrave; xếp h&agrave;ng d&agrave;i chờ đợi! Giờ đ&acirc;y, bạn c&oacute; thể mua sắm bất cứ khi n&agrave;o, ở bất cứ đ&acirc;u tr&ecirc;n m&aacute;y t&iacute;nh v&agrave; điện thoại di động của m&igrave;nh.</p>\r\n<p style=\"margin-top: 0px; margin-bottom: 10px; line-height: 1.75; color: #888888; font-family: Roboto, sans-serif; background-color: #ffffff; text-align: justify;\">Với dịch vụ chuyển h&agrave;ng nhanh ch&oacute;ng v&agrave; đ&aacute;ng tin cậy, bạn chỉ cần ngồi thư gi&atilde;n tại nh&agrave; v&agrave; chờ nhận h&agrave;ng.</p>\r\n<p style=\"margin-top: 0px; margin-bottom: 10px; line-height: 1.75; color: #888888; font-family: Roboto, sans-serif; background-color: #ffffff; text-align: justify;\"><span style=\"font-weight: bold;\">Bảo đảm về chất lượng v&agrave; t&iacute;nh x&aacute;c thực:</span></p>\r\n<p style=\"margin-top: 0px; margin-bottom: 10px; line-height: 1.75; color: #888888; font-family: Roboto, sans-serif; background-color: #ffffff; text-align: justify;\">Tất cả c&aacute;c sản phẩm được giao dịch tr&ecirc;n Vivmall đều được đảm bảo l&agrave; sản phẩm ch&iacute;nh h&atilde;ng, mới, kh&ocirc;ng khiếm khuyết hay hư hỏng.</p>\r\n<p style=\"margin-top: 0px; margin-bottom: 10px; line-height: 1.75; color: #888888; font-family: Roboto, sans-serif; background-color: #ffffff; text-align: justify;\"><span style=\"font-weight: bold;\">MỤC TI&Ecirc;U VIVMALL:</span></p>\r\n<ul style=\"margin-bottom: 10px; color: #888888; font-family: Roboto, sans-serif; text-align: center; background-color: #ffffff;\">\r\n<li style=\"text-align: justify;\">Chất lượng, Uy t&iacute;n v&agrave; Chuy&ecirc;n nghiệp.</li>\r\n<li style=\"text-align: justify;\">Mang đến cho kh&aacute;ch h&agrave;ng sự tin tưởng v&agrave; h&agrave;i l&ograve;ng khi mua sắm tại nh&agrave;.</li>\r\n<li style=\"text-align: justify;\">K&ecirc;nh cung cấp th&ocirc;ng tin v&agrave; tư vấn sản phẩm tốt nhất cho kh&aacute;ch h&agrave;ng.</li>\r\n<li style=\"text-align: justify;\">Mở rộng phạm vi b&aacute;n h&agrave;ng tr&ecirc;n to&agrave;n quốc.</li>\r\n</ul>\r\n<p style=\"margin-top: 0px; margin-bottom: 10px; line-height: 1.75; color: #888888; font-family: Roboto, sans-serif; background-color: #ffffff; text-align: justify;\">Vivmall lu&ocirc;n cập nhật những sản phẩm đa dạng mẫu m&atilde; đ&aacute;p ứng thị hiếu ti&ecirc;u d&ugrave;ng của Qu&yacute; kh&aacute;ch, ch&uacute;ng t&ocirc;i cam kết về chất lượng sản phẩm v&agrave; lu&ocirc;n lu&ocirc;n phục vụ kh&aacute;ch h&agrave;ng một c&aacute;ch tốt nhất!</p>');
/*!40000 ALTER TABLE `tb_about` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_contacts`
--

DROP TABLE IF EXISTS `tb_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_contacts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `subject` varchar(245) DEFAULT NULL,
  `content` text,
  `phone` varchar(15) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_contacts`
--

LOCK TABLES `tb_contacts` WRITE;
/*!40000 ALTER TABLE `tb_contacts` DISABLE KEYS */;
INSERT INTO `tb_contacts` VALUES (32,'u1','u1@gmail.com','u1','cotent.....','111111','addresss','2017-07-12 09:27:49'),(33,'a','a@gmail.com','address','content','111111','aaa','2017-07-12 09:34:00'),(34,'u2','u2@gmail.com','u2..........','u2 content','111111','address','2017-07-12 09:42:38');
/*!40000 ALTER TABLE `tb_contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product`
--

DROP TABLE IF EXISTS `tb_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product` (
  `id` varchar(13) NOT NULL,
  `ProductName` varchar(245) DEFAULT NULL,
  `FeatureImage` varchar(245) DEFAULT NULL,
  `ProductDes` varchar(245) DEFAULT NULL,
  `SellPrice` decimal(11,2) DEFAULT NULL,
  `Seller` varchar(245) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product`
--

LOCK TABLES `tb_product` WRITE;
/*!40000 ALTER TABLE `tb_product` DISABLE KEYS */;
INSERT INTO `tb_product` VALUES ('6920002000126','bbbbbbb','2017-07-13_vietnam.png','<p>bbbbbbbbbbbbbbbb</p>',0.00,'admin@gmail.com',1),('8930001000704','Coconut mask','no_image.jpg','sfsfsf',10.00,'admin@gmail.com',1);
/*!40000 ALTER TABLE `tb_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_roles`
--

DROP TABLE IF EXISTS `tb_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_roles` (
  `id` varchar(40) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_roles`
--

LOCK TABLES `tb_roles` WRITE;
/*!40000 ALTER TABLE `tb_roles` DISABLE KEYS */;
INSERT INTO `tb_roles` VALUES ('ROLE_ADMIN','admin'),('ROLE_BUYER','buyer'),('ROLE_SELLER','seller');
/*!40000 ALTER TABLE `tb_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(60) DEFAULT NULL,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `RegisterDate` datetime DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES ('admin@gmail.com','$2a$10$eo3fwsiPzAd6VmPM6F3nhOJqA3WtDazEaIqkGdtNieWEd4.yQRPdO','admin first name','admin last name','2011-01-01 00:00:00','asd','02112',1,'2011-04-04 00:00:00'),('agentcn@gmail.com','$2a$10$mRj07msfFWYYxTCWxHUBGOR0tthp4vPJO6YwN17CtkX9HvmC68aSi','agentcn','agentcn','2017-01-01 00:00:00','cn','552525',1,'2017-07-12 10:39:09'),('member@gmail.com','$2a$10$brP2XaYG59iECHuuNYSQDeJoXk8B0Fi41.US73usIYiHCr4cYmoye','member first name','member last name','2015-12-27 00:00:00','asf','145997',1,'2011-04-04 00:00:00'),('test3654@gmail.com','$2a$10$fyonAqsZFfz5Ac6IuaUktOS38RLbAmU2omn0i7G.OyG6g4wLk12c.','fgdfg','sdsad','2017-01-01 00:00:00','345345','234234',1,'2017-07-12 09:02:13');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_roles`
--

DROP TABLE IF EXISTS `tb_user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `role` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_roles`
--

LOCK TABLES `tb_user_roles` WRITE;
/*!40000 ALTER TABLE `tb_user_roles` DISABLE KEYS */;
INSERT INTO `tb_user_roles` VALUES (3,'admin@gmail.com','ROLE_SELLER'),(4,'admin@gmail.com','ROLE_ADMIN'),(18,'member@gmail.com','ROLE_SELLER'),(23,'test3654@gmail.com','ROLE_SELLER'),(24,'test3654@gmail.com','ROLE_SELLER'),(25,'test3654@gmail.com','ROLE_SELLER'),(26,'agentcn@gmail.com','ROLE_SELLER'),(27,'test1@gmail.com','ROLE_ADMIN');
/*!40000 ALTER TABLE `tb_user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_users`
--

DROP TABLE IF EXISTS `tb_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(60) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `company_address` varchar(100) DEFAULT NULL,
  `company_phone` varchar(15) DEFAULT NULL,
  `company_image` varchar(100) DEFAULT NULL,
  `company_license` varchar(100) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_users`
--

LOCK TABLES `tb_users` WRITE;
/*!40000 ALTER TABLE `tb_users` DISABLE KEYS */;
INSERT INTO `tb_users` VALUES ('admin@gmail.com','$2a$10$cawlpCiWIK7RpnrEc19dzuMXGnCez927GNQ2tV/IuIQedI.ceIXoG','company name','adress','phone','admin.jpg','license.jpg','2017-07-11 16:42:43',1),('member@gmail.com','$2a$10$Wsv.YGd6388D5bVGmkOLzuRefnZTVLpuw71xquI1Tqw8JNQ3I.Pjq','company name','adress','phone','admin.jpg','license.jpg','2017-07-11 16:42:43',1);
/*!40000 ALTER TABLE `tb_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'sellerdbv2'
--
/*!50003 DROP FUNCTION IF EXISTS `getNextCustomSeq` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`vinhsang`@`%` FUNCTION `getNextCustomSeq`(
    sSeqName VARCHAR(50),
    sSeqGroup VARCHAR(10)
) RETURNS varchar(20) CHARSET utf8
BEGIN
    DECLARE nLast_val INT; 
 
    SET nLast_val =  (SELECT seq_val
                          FROM _sequence
                          WHERE seq_name = sSeqName
                                AND seq_group = sSeqGroup);
    IF nLast_val IS NULL THEN
        SET nLast_val = 1;
        INSERT INTO _sequence (seq_name,seq_group,seq_val)
        VALUES (sSeqName,sSeqGroup,nLast_Val);
    ELSE
        SET nLast_val = nLast_val + 1;
        UPDATE _sequence SET seq_val = nLast_val
        WHERE seq_name = sSeqName AND seq_group = sSeqGroup;
    END IF; 
 
    SET @ret = (SELECT concat(sSeqGroup,'-',lpad(nLast_val,11,'0')));
    RETURN @ret;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `getNextProductBarcodeWithoutChecksumDigit` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`vinhsang`@`%` FUNCTION `getNextProductBarcodeWithoutChecksumDigit`(
) RETURNS varchar(12) CHARSET utf8
BEGIN
DECLARE nLast_val INT; 
DECLARE nContry_val varchar(3);
DECLARE nCompany_val varchar(6);
declare nProduct_length int;

select  contry_code, company_code, product_code_count, product_length
into    nContry_val,nCompany_val,nLast_val,nProduct_length
from _sequence_barcode where id=1;
  IF nLast_val IS NULL THEN
        SET nLast_val = 1;
        INSERT INTO _sequence_barcode (id,contry_code, company_code, product_code_count, product_length)
        VALUES (1,'893','0001',1,5);
    ELSE
        SET nLast_val = nLast_val + 1;
        UPDATE _sequence_barcode SET product_code_count = nLast_val
        WHERE id=1;
    END IF; 

	SET @ret = (SELECT concat(nContry_val,nCompany_val,lpad(nLast_val,nProduct_length,'0')));
    RETURN @ret;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `SPLIT_STR` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`vinhsang`@`%` FUNCTION `SPLIT_STR`(
  x VARCHAR(255),
  delim VARCHAR(12),
  pos INT
) RETURNS varchar(255) CHARSET utf8
RETURN REPLACE(SUBSTRING(SUBSTRING_INDEX(x, delim, pos),
       LENGTH(SUBSTRING_INDEX(x, delim, pos -1)) + 1),
       delim, '') ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-09 10:31:49
