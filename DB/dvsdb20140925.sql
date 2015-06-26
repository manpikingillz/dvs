CREATE DATABASE  IF NOT EXISTS `dvsdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dvsdb`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: dvsdb
-- ------------------------------------------------------
-- Server version	5.5.14

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
-- Table structure for table `bond`
--

DROP TABLE IF EXISTS `bond`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bond` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `issuerCode` varchar(30) NOT NULL COMMENT 'Code of the Government or Company that is issuing the bond',
  `ISIN` varchar(45) NOT NULL COMMENT 'Internation Security Identification Number',
  `shortName` varchar(30) NOT NULL,
  `COUNTRY_OF_INITIAL_ISSUE` int(11) NOT NULL,
  `parValue` float NOT NULL,
  `quantityIssued` float NOT NULL,
  `CURRENCY_TYPE` int(11) NOT NULL,
  `dateIssued` date NOT NULL,
  `dateExpire` date NOT NULL,
  `couponRate` float NOT NULL,
  `dateFirstCoupon` date NOT NULL,
  `dateFinalCoupon` date NOT NULL,
  `term` int(11) NOT NULL,
  `BOND_TYPE` int(11) NOT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ISIN_UNIQUE` (`ISIN`),
  UNIQUE KEY `shortName_UNIQUE` (`shortName`),
  KEY `fk_BOND_COUNTRY1_idx` (`COUNTRY_OF_INITIAL_ISSUE`),
  KEY `fk_BOND_CURRENCY_TYPE1_idx` (`CURRENCY_TYPE`),
  KEY `fk_BOND_BOND_TYPE1_idx` (`BOND_TYPE`),
  CONSTRAINT `fk_BOND_BOND_TYPE1` FOREIGN KEY (`BOND_TYPE`) REFERENCES `bond_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_BOND_COUNTRY1` FOREIGN KEY (`COUNTRY_OF_INITIAL_ISSUE`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_BOND_CURRENCY_TYPE1` FOREIGN KEY (`CURRENCY_TYPE`) REFERENCES `currency_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bond`
--

LOCK TABLES `bond` WRITE;
/*!40000 ALTER TABLE `bond` DISABLE KEYS */;
INSERT INTO `bond` VALUES (1,'GOU','94345345','GOU',5,342,2342,3,'2014-08-17','2015-11-13',42,'2014-08-17','2015-04-08',1,3,NULL);
/*!40000 ALTER TABLE `bond` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bond_daily_price`
--

DROP TABLE IF EXISTS `bond_daily_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bond_daily_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `BOND` int(11) NOT NULL,
  `tradedYield` float DEFAULT NULL,
  `dirtyPrice` float DEFAULT NULL,
  `cleanPrice` float DEFAULT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_BOND_DAILY_PRICE_BOND1_idx` (`BOND`),
  CONSTRAINT `fk_BOND_DAILY_PRICE_BOND1` FOREIGN KEY (`BOND`) REFERENCES `bond` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bond_daily_price`
--

LOCK TABLES `bond_daily_price` WRITE;
/*!40000 ALTER TABLE `bond_daily_price` DISABLE KEYS */;
INSERT INTO `bond_daily_price` VALUES (1,1,52300,450000,540000,NULL),(6,1,5345,5345600,6700,NULL),(7,1,63466,345345,63454,NULL),(14,1,5345,5345600,6700,NULL);
/*!40000 ALTER TABLE `bond_daily_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bond_type`
--

DROP TABLE IF EXISTS `bond_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bond_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bondType` varchar(45) NOT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bondType_UNIQUE` (`bondType`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bond_type`
--

LOCK TABLES `bond_type` WRITE;
/*!40000 ALTER TABLE `bond_type` DISABLE KEYS */;
INSERT INTO `bond_type` VALUES (3,'Government Bond',NULL),(4,'Corporate Bond',NULL);
/*!40000 ALTER TABLE `bond_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `countryName` varchar(45) NOT NULL,
  `countryShortName` varchar(10) DEFAULT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `countryName_UNIQUE` (`countryName`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (5,'Uganda','UG','2014-08-12 22:48:13'),(6,'Kenya','KE','2014-08-12 22:48:13'),(7,'Tanzania','TZ','2014-08-12 22:48:13');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency_type`
--

DROP TABLE IF EXISTS `currency_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `currencyTypeShortName` varchar(10) NOT NULL,
  `currencyTypeLongName` varchar(45) DEFAULT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `currencyTypeShortName_UNIQUE` (`currencyTypeShortName`),
  UNIQUE KEY `currencyTypeLongName_UNIQUE` (`currencyTypeLongName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency_type`
--

LOCK TABLES `currency_type` WRITE;
/*!40000 ALTER TABLE `currency_type` DISABLE KEYS */;
INSERT INTO `currency_type` VALUES (3,'UGX','Uganda Shillings',NULL),(4,'USD','United States Dollar',NULL);
/*!40000 ALTER TABLE `currency_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_duration_category`
--

DROP TABLE IF EXISTS `data_duration_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_duration_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dataDurationCategory` varchar(45) NOT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sellCategoryName_UNIQUE` (`dataDurationCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_duration_category`
--

LOCK TABLES `data_duration_category` WRITE;
/*!40000 ALTER TABLE `data_duration_category` DISABLE KEYS */;
INSERT INTO `data_duration_category` VALUES (3,'Current Data',NULL),(4,'Historical Data',NULL);
/*!40000 ALTER TABLE `data_duration_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `personId` int(11) NOT NULL,
  `employeeNumber` varchar(25) NOT NULL,
  `JOBTITLE` int(11) NOT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`personId`),
  UNIQUE KEY `employeeNumber_UNIQUE` (`employeeNumber`),
  KEY `fk_EMPLOYEE_JOBTITLE1_idx` (`JOBTITLE`),
  CONSTRAINT `fk_EMPLOYEE_JOBTITLE1` FOREIGN KEY (`JOBTITLE`) REFERENCES `jobtitle` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_EMPLOYEE_USER1` FOREIGN KEY (`personId`) REFERENCES `user` (`personId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (14,'USE014',3,'2014-08-17 20:21:11'),(15,'4',2,'2014-08-17 21:25:06');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_has_instrument`
--

DROP TABLE IF EXISTS `employee_has_instrument`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_has_instrument` (
  `personId` int(11) NOT NULL,
  `INSTRUMENT` int(11) NOT NULL,
  PRIMARY KEY (`personId`,`INSTRUMENT`),
  KEY `fk_EMPLOYEE_has_INSTRUMENT_INSTRUMENT1_idx` (`INSTRUMENT`),
  KEY `fk_EMPLOYEE_has_INSTRUMENT_EMPLOYEE1_idx` (`personId`),
  CONSTRAINT `fk_EMPLOYEE_has_INSTRUMENT_EMPLOYEE1` FOREIGN KEY (`personId`) REFERENCES `employee` (`personId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_EMPLOYEE_has_INSTRUMENT_INSTRUMENT1` FOREIGN KEY (`INSTRUMENT`) REFERENCES `instrument` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_has_instrument`
--

LOCK TABLES `employee_has_instrument` WRITE;
/*!40000 ALTER TABLE `employee_has_instrument` DISABLE KEYS */;
INSERT INTO `employee_has_instrument` VALUES (14,4);
/*!40000 ALTER TABLE `employee_has_instrument` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_is_assigned_to_instrument`
--

DROP TABLE IF EXISTS `employee_is_assigned_to_instrument`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_is_assigned_to_instrument` (
  `INSTRUMENT` int(11) NOT NULL,
  `EMPLOYEE` int(11) NOT NULL,
  PRIMARY KEY (`INSTRUMENT`,`EMPLOYEE`),
  CONSTRAINT `FK_EMPLOYEE_IS_ASSIGNED_TO_INSTRUMENT_INSTRUMENT` FOREIGN KEY (`INSTRUMENT`) REFERENCES `instrument` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_is_assigned_to_instrument`
--

LOCK TABLES `employee_is_assigned_to_instrument` WRITE;
/*!40000 ALTER TABLE `employee_is_assigned_to_instrument` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_is_assigned_to_instrument` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equity`
--

DROP TABLE IF EXISTS `equity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nameOfSecurity` varchar(100) NOT NULL,
  `shortName` varchar(10) NOT NULL,
  `ISIN` varchar(25) NOT NULL COMMENT 'International Security Identification Number',
  `issuerCode` varchar(10) NOT NULL,
  `FHLimit` float NOT NULL COMMENT 'Foreign Holding Limit - the maximum foreigners can hold on an  equity. Whenever people are buiyng shares, they state whether they are foreingers or locals and the maximum should not exceed what has been specified. This will be identified by the country',
  `COUNTRY_OF_INITIAL_ISSUE` int(11) DEFAULT NULL,
  `referencePrice` float NOT NULL COMMENT 'The same is IPO Price.',
  `quantityIssued` float NOT NULL COMMENT 'The shares issued during IPO',
  `dateAdded` date NOT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`nameOfSecurity`),
  UNIQUE KEY `shortName_UNIQUE` (`shortName`),
  UNIQUE KEY `ISIN_UNIQUE` (`ISIN`),
  KEY `fk_SECURITY_COUNTRY1_idx` (`COUNTRY_OF_INITIAL_ISSUE`),
  CONSTRAINT `fk_SECURITY_COUNTRY1` FOREIGN KEY (`COUNTRY_OF_INITIAL_ISSUE`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equity`
--

LOCK TABLES `equity` WRITE;
/*!40000 ALTER TABLE `equity` DISABLE KEYS */;
INSERT INTO `equity` VALUES (1,'BRITISH AMERICAN TOBACCO UGANDA','BATU','UG0000000022','BATU',0,5,0,0,'0007-10-08',NULL);
/*!40000 ALTER TABLE `equity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equity_daily_price`
--

DROP TABLE IF EXISTS `equity_daily_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equity_daily_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `EQUITY` int(11) NOT NULL,
  `EQUITY_MARKET_DAY` int(11) NOT NULL,
  `sharesTraded` int(11) NOT NULL,
  `turnOver` int(11) NOT NULL,
  `VWAP` int(11) NOT NULL,
  `low` int(11) NOT NULL,
  `high` int(11) NOT NULL,
  `outstandingBid` int(11) NOT NULL,
  `outstandingOffer` int(11) NOT NULL,
  `PERatio` float NOT NULL,
  `marketCap` float NOT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_EQUITY_DAILY_MARKET_SECURITY1_idx` (`EQUITY`),
  KEY `fk_EQUITY_DAILY_MARKET_EQUITY_MARKET_DAY1_idx` (`EQUITY_MARKET_DAY`),
  CONSTRAINT `fk_EQUITY_DAILY_MARKET_EQUITY_MARKET_DAY1` FOREIGN KEY (`EQUITY_MARKET_DAY`) REFERENCES `equity_market_day` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_EQUITY_DAILY_MARKET_SECURITY1` FOREIGN KEY (`EQUITY`) REFERENCES `equity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equity_daily_price`
--

LOCK TABLES `equity_daily_price` WRITE;
/*!40000 ALTER TABLE `equity_daily_price` DISABLE KEYS */;
INSERT INTO `equity_daily_price` VALUES (1,1,2,13900,120,120,50,70,450,450,450,30,NULL);
/*!40000 ALTER TABLE `equity_daily_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equity_market_day`
--

DROP TABLE IF EXISTS `equity_market_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equity_market_day` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateOfMarket` date NOT NULL,
  `ALSICurrent` float NOT NULL COMMENT 'Current Value of All Securities Index',
  `LSICurrent` float NOT NULL COMMENT 'Current Value of Local Securities Index',
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equity_market_day`
--

LOCK TABLES `equity_market_day` WRITE;
/*!40000 ALTER TABLE `equity_market_day` DISABLE KEYS */;
INSERT INTO `equity_market_day` VALUES (1,'0014-08-08',1755,276,NULL),(2,'0014-09-08',1778,289,NULL),(3,'2014-08-25',1540,569,NULL);
/*!40000 ALTER TABLE `equity_market_day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gender` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gender` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `gender_UNIQUE` (`gender`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES (4,'Female'),(3,'Male');
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instrument`
--

DROP TABLE IF EXISTS `instrument`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instrument` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instrumentName` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `securityTypeName_UNIQUE` (`instrumentName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instrument`
--

LOCK TABLES `instrument` WRITE;
/*!40000 ALTER TABLE `instrument` DISABLE KEYS */;
INSERT INTO `instrument` VALUES (3,'Bond'),(4,'Equity');
/*!40000 ALTER TABLE `instrument` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investor`
--

DROP TABLE IF EXISTS `investor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `investor` (
  `personId` int(11) NOT NULL,
  `address` varchar(100) NOT NULL,
  `INVESTOR_TYPE` int(11) NOT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`personId`),
  KEY `fk_INVESTOR_INVESTOR_TYPE1_idx` (`INVESTOR_TYPE`),
  CONSTRAINT `fk_INVESTOR_INVESTOR_TYPE1` FOREIGN KEY (`INVESTOR_TYPE`) REFERENCES `investor_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_INVESTOR_USER1` FOREIGN KEY (`personId`) REFERENCES `user` (`personId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investor`
--

LOCK TABLES `investor` WRITE;
/*!40000 ALTER TABLE `investor` DISABLE KEYS */;
INSERT INTO `investor` VALUES (12,'Bukoto Street Plot 67',3,'2014-08-12 22:52:51'),(13,'Kawempe Ttula',3,'2014-08-13 22:39:42');
/*!40000 ALTER TABLE `investor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investor_data_payment_detail`
--

DROP TABLE IF EXISTS `investor_data_payment_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `investor_data_payment_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `INVESTOR_GENERAL_DATA_REQUEST` int(11) NOT NULL,
  `dateOfPayment` date NOT NULL,
  `amountPaid` float NOT NULL,
  `paymentSlipNumber` varchar(45) NOT NULL,
  `paymentSlipAttachment` longblob,
  `bank` varchar(100) DEFAULT NULL,
  `bankBranch` varchar(100) DEFAULT NULL,
  `BRANCH_COUNTRY` int(11) NOT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_INVESTOR_DATA_PAYMENT_DETAIL_INVESTOR_GENERAL_DATA_REQUE_idx` (`INVESTOR_GENERAL_DATA_REQUEST`),
  KEY `fk_INVESTOR_DATA_PAYMENT_DETAIL_COUNTRY1_idx` (`BRANCH_COUNTRY`),
  CONSTRAINT `fk_INVESTOR_DATA_PAYMENT_DETAIL_COUNTRY1` FOREIGN KEY (`BRANCH_COUNTRY`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_INVESTOR_DATA_PAYMENT_DETAIL_INVESTOR_GENERAL_DATA_REQUEST1` FOREIGN KEY (`INVESTOR_GENERAL_DATA_REQUEST`) REFERENCES `investor_general_data_request` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investor_data_payment_detail`
--

LOCK TABLES `investor_data_payment_detail` WRITE;
/*!40000 ALTER TABLE `investor_data_payment_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `investor_data_payment_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investor_general_data_request`
--

DROP TABLE IF EXISTS `investor_general_data_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `investor_general_data_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personId` int(11) NOT NULL,
  `requestDate` date NOT NULL,
  `amount` float NOT NULL,
  `activeStatus` tinyint(1) DEFAULT '0' COMMENT '0 means  inactive and 1 means active. The investor can only access data that has been activated. The administrator holds the rights to activate the request',
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `paymentStatus` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_INVESTOR_GENERAL_DATA_REQUEST_PERSON1_idx` (`personId`),
  CONSTRAINT `fk_INVESTOR_GENERAL_DATA_REQUEST_PERSON1` FOREIGN KEY (`personId`) REFERENCES `person` (`personId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investor_general_data_request`
--

LOCK TABLES `investor_general_data_request` WRITE;
/*!40000 ALTER TABLE `investor_general_data_request` DISABLE KEYS */;
INSERT INTO `investor_general_data_request` VALUES (1,12,'2014-08-14',500000,1,'2014-08-13 22:31:44',1),(2,13,'2014-08-14',850000,0,'2014-08-13 22:40:55',0),(3,14,'2014-09-04',252453,0,NULL,NULL);
/*!40000 ALTER TABLE `investor_general_data_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investor_requested_data`
--

DROP TABLE IF EXISTS `investor_requested_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `investor_requested_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `INVESTOR_GENERAL_DATA_REQUEST` int(11) NOT NULL,
  `TYPE_OF_DATA_TO_SELL_has_DATA_DURATION_CATEGORY` int(11) NOT NULL,
  `activeStatus` tinyint(1) DEFAULT '1',
  `requestDate` date NOT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_INVESTOR_REQUESTED_DATA_INVESTOR_GENERAL_DATA_REQUEST1_idx` (`INVESTOR_GENERAL_DATA_REQUEST`),
  KEY `fk_INVESTOR_REQUESTED_DATA_TYPE_OF_DATA_TO_SELL_has_DATA_DU_idx` (`TYPE_OF_DATA_TO_SELL_has_DATA_DURATION_CATEGORY`),
  CONSTRAINT `fk_INVESTOR_REQUESTED_DATA_INVESTOR_GENERAL_DATA_REQUEST1` FOREIGN KEY (`INVESTOR_GENERAL_DATA_REQUEST`) REFERENCES `investor_general_data_request` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_INVESTOR_REQUESTED_DATA_TYPE_OF_DATA_TO_SELL_has_DATA_DURA1` FOREIGN KEY (`TYPE_OF_DATA_TO_SELL_has_DATA_DURATION_CATEGORY`) REFERENCES `type_of_data_to_sell_has_data_duration_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investor_requested_data`
--

LOCK TABLES `investor_requested_data` WRITE;
/*!40000 ALTER TABLE `investor_requested_data` DISABLE KEYS */;
INSERT INTO `investor_requested_data` VALUES (1,1,1,1,'2014-08-13','2014-08-13 22:34:41');
/*!40000 ALTER TABLE `investor_requested_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investor_type`
--

DROP TABLE IF EXISTS `investor_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `investor_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personType` varchar(45) NOT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `personType_UNIQUE` (`personType`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investor_type`
--

LOCK TABLES `investor_type` WRITE;
/*!40000 ALTER TABLE `investor_type` DISABLE KEYS */;
INSERT INTO `investor_type` VALUES (3,'Person','2014-08-12 22:52:09'),(4,'Company','2014-08-12 22:52:09');
/*!40000 ALTER TABLE `investor_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobtitle`
--

DROP TABLE IF EXISTS `jobtitle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobtitle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobtitle` varchar(45) NOT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `jobtitle_UNIQUE` (`jobtitle`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobtitle`
--

LOCK TABLES `jobtitle` WRITE;
/*!40000 ALTER TABLE `jobtitle` DISABLE KEYS */;
INSERT INTO `jobtitle` VALUES (2,'Administrator',NULL),(3,'Data Entrant',NULL);
/*!40000 ALTER TABLE `jobtitle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_upload`
--

DROP TABLE IF EXISTS `payment_upload`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_upload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paymentUploadName` varchar(100) DEFAULT NULL,
  `upload` longblob,
  `uploadPath` text,
  `INVESTOR_DATA_PAYMENT_DETAIL` int(11) NOT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_PAYMENT_UPLOADS_INVESTOR_DATA_PAYMENT_DETAIL1_idx` (`INVESTOR_DATA_PAYMENT_DETAIL`),
  CONSTRAINT `fk_PAYMENT_UPLOADS_INVESTOR_DATA_PAYMENT_DETAIL1` FOREIGN KEY (`INVESTOR_DATA_PAYMENT_DETAIL`) REFERENCES `investor_data_payment_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_upload`
--

LOCK TABLES `payment_upload` WRITE;
/*!40000 ALTER TABLE `payment_upload` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_upload` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `personId` int(11) NOT NULL AUTO_INCREMENT,
  `surname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `GENDER` int(11) NOT NULL,
  `phone` varchar(25) NOT NULL,
  `email` varchar(45) NOT NULL,
  `personTypeJPADescriminator` varchar(45) DEFAULT NULL,
  `COUNTRY` int(11) NOT NULL,
  PRIMARY KEY (`personId`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_PERSON_GENDER1_idx` (`GENDER`),
  KEY `fk_PERSON_COUNTRY1_idx` (`COUNTRY`),
  CONSTRAINT `fk_PERSON_COUNTRY1` FOREIGN KEY (`COUNTRY`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERSON_GENDER1` FOREIGN KEY (`GENDER`) REFERENCES `gender` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (12,'Twesigomwe','Gilbert',4,'0777184393','gilbert.gillz@gmail.com','Investor',5),(13,'Tumwijukye','Ambrose',4,'0789349382','tumwijukye.ambrose@gmail.com','Investor',5),(14,'Tumwekwase','Barnabas',3,'0784939293','barna@gmail.com','Employee',5),(15,'Frank','Guma',3,'5555','gumafrank@gmail.com','Employee',5);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_of_data_to_sell`
--

DROP TABLE IF EXISTS `type_of_data_to_sell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_of_data_to_sell` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeOfDataToSell` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `INSTRUMENT` int(11) NOT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `typeName_UNIQUE` (`typeOfDataToSell`),
  KEY `fk_TYPE_OF_DATA_INSTRUMENT1_idx` (`INSTRUMENT`),
  CONSTRAINT `fk_TYPE_OF_DATA_INSTRUMENT1` FOREIGN KEY (`INSTRUMENT`) REFERENCES `instrument` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_of_data_to_sell`
--

LOCK TABLES `type_of_data_to_sell` WRITE;
/*!40000 ALTER TABLE `type_of_data_to_sell` DISABLE KEYS */;
INSERT INTO `type_of_data_to_sell` VALUES (7,'ALSI',NULL,4,NULL),(8,'Market Reports',NULL,4,NULL),(9,'LSI',NULL,4,NULL),(10,'Publications',NULL,4,NULL);
/*!40000 ALTER TABLE `type_of_data_to_sell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_of_data_to_sell_has_data_duration_category`
--

DROP TABLE IF EXISTS `type_of_data_to_sell_has_data_duration_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_of_data_to_sell_has_data_duration_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE_OF_DATA_TO_SELL` int(11) NOT NULL,
  `DATA_DURATION_CATEGORY` int(11) NOT NULL,
  `price` float DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `data_price_unique` (`TYPE_OF_DATA_TO_SELL`,`DATA_DURATION_CATEGORY`),
  KEY `fk_TYPE_OF_DATA_TO_SELL_has_DATA_DURATION_CATEGORY_DATA_DUR_idx` (`DATA_DURATION_CATEGORY`),
  KEY `fk_TYPE_OF_DATA_TO_SELL_has_DATA_DURATION_CATEGORY_TYPE_OF__idx` (`TYPE_OF_DATA_TO_SELL`),
  CONSTRAINT `fk_TYPE_OF_DATA_TO_SELL_has_DATA_DURATION_CATEGORY_DATA_DURAT1` FOREIGN KEY (`DATA_DURATION_CATEGORY`) REFERENCES `data_duration_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TYPE_OF_DATA_TO_SELL_has_DATA_DURATION_CATEGORY_TYPE_OF_DA1` FOREIGN KEY (`TYPE_OF_DATA_TO_SELL`) REFERENCES `type_of_data_to_sell` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_of_data_to_sell_has_data_duration_category`
--

LOCK TABLES `type_of_data_to_sell_has_data_duration_category` WRITE;
/*!40000 ALTER TABLE `type_of_data_to_sell_has_data_duration_category` DISABLE KEYS */;
INSERT INTO `type_of_data_to_sell_has_data_duration_category` VALUES (1,8,4,52000,'Market Reports',NULL),(2,9,4,60000,'LSI Data',NULL),(4,10,3,76000,'12000',NULL),(5,7,4,56000,'',NULL),(7,7,3,8453,'',NULL);
/*!40000 ALTER TABLE `type_of_data_to_sell_has_data_duration_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `personId` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `enabledOrDisabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`personId`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  CONSTRAINT `fk_USER_PERSON1` FOREIGN KEY (`personId`) REFERENCES `person` (`personId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (12,'gilbert','49b191cf813fa62a2a280ca07b6812df23be3f3c2437604e7fcd2dee72f1f527',NULL,NULL),(13,'ambrose','8d6a0f2c415037eb8827b32135a9447fe91a66c1303fe86e6ebd3a637b9351d9',NULL,NULL),(14,'barna','',NULL,1),(15,'frank','77646f5a4f3166637627abe998e7a1470fe72d8b430f067dafa86263f1f23f94',NULL,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(45) NOT NULL,
  `autoTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rolename_UNIQUE` (`groupname`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` VALUES (4,'Administrator','2014-08-12 22:55:23'),(5,'DataEntrant','2014-08-12 22:55:23'),(6,'Investor','2014-08-12 22:55:23');
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_has_user_group`
--

DROP TABLE IF EXISTS `user_has_user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_has_user_group` (
  `personId` int(11) NOT NULL,
  `USER_GROUP_ID` int(11) NOT NULL,
  PRIMARY KEY (`personId`,`USER_GROUP_ID`),
  KEY `fk_USER_has_USER_GROUP_USER_GROUP1_idx` (`USER_GROUP_ID`),
  KEY `fk_USER_has_USER_GROUP_USER1_idx` (`personId`),
  CONSTRAINT `fk_USER_has_USER_GROUP_USER1` FOREIGN KEY (`personId`) REFERENCES `user` (`personId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_has_USER_GROUP_USER_GROUP1` FOREIGN KEY (`USER_GROUP_ID`) REFERENCES `user_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_user_group`
--

LOCK TABLES `user_has_user_group` WRITE;
/*!40000 ALTER TABLE `user_has_user_group` DISABLE KEYS */;
INSERT INTO `user_has_user_group` VALUES (12,4),(14,4),(15,4),(14,5),(15,5),(13,6),(14,6),(15,6);
/*!40000 ALTER TABLE `user_has_user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `viewusergroup`
--

DROP TABLE IF EXISTS `viewusergroup`;
/*!50001 DROP VIEW IF EXISTS `viewusergroup`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `viewusergroup` (
  `username` tinyint NOT NULL,
  `password` tinyint NOT NULL,
  `groupname` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'dvsdb'
--

--
-- Final view structure for view `viewusergroup`
--

/*!50001 DROP TABLE IF EXISTS `viewusergroup`*/;
/*!50001 DROP VIEW IF EXISTS `viewusergroup`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `viewusergroup` AS select `user`.`username` AS `username`,`user`.`password` AS `password`,`user_group`.`groupname` AS `groupname` from ((`user` join `user_has_user_group` on((`user_has_user_group`.`personId` = `user`.`personId`))) join `user_group` on((`user_has_user_group`.`USER_GROUP_ID` = `user_group`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-25 18:01:50
