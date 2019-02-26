-- MySQL dump 10.13  Distrib 8.0.14, for Win64 (x86_64)
--
-- Host: localhost    Database: app_db
-- ------------------------------------------------------
-- Server version	8.0.14

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
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `announcement` (
  `AnnouncementID` int(11) NOT NULL AUTO_INCREMENT,
  `LeaderID` int(11) NOT NULL,
  `DateAndTime` datetime NOT NULL,
  `content` longtext,
  `GroupID` int(11) NOT NULL,
  PRIMARY KEY (`AnnouncementID`),
  KEY `LeaderID` (`LeaderID`),
  KEY `GroupID` (`GroupID`),
  CONSTRAINT `announcement_ibfk_1` FOREIGN KEY (`LeaderID`) REFERENCES `membership` (`MemberID`),
  CONSTRAINT `announcement_ibfk_2` FOREIGN KEY (`GroupID`) REFERENCES `record` (`GroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comment` (
  `CommentID` int(11) NOT NULL AUTO_INCREMENT,
  `MemberID` int(11) NOT NULL,
  `DateAndTime` datetime NOT NULL,
  `content` text,
  `announcementID` int(11) NOT NULL,
  PRIMARY KEY (`CommentID`),
  KEY `MemberID` (`MemberID`),
  KEY `announcementID` (`announcementID`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`MemberID`) REFERENCES `membership` (`MemberID`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`announcementID`) REFERENCES `announcement` (`AnnouncementID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `event` (
  `EventID` int(11) NOT NULL AUTO_INCREMENT,
  `DateAndTime` datetime NOT NULL,
  `eventName` varchar(255) NOT NULL,
  `LeaderID` int(11) NOT NULL,
  `Location` varchar(255) NOT NULL,
  `Description` text,
  PRIMARY KEY (`EventID`),
  KEY `LeaderID` (`LeaderID`),
  CONSTRAINT `event_ibfk_1` FOREIGN KEY (`LeaderID`) REFERENCES `membership` (`MemberID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membership`
--

DROP TABLE IF EXISTS `membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `membership` (
  `MemberID` int(11) NOT NULL AUTO_INCREMENT,
  `UserPrivileges` tinyint(1) NOT NULL,
  `UserID` int(11) NOT NULL,
  `GroupID` int(11) NOT NULL,
  PRIMARY KEY (`MemberID`),
  KEY `UserID` (`UserID`),
  KEY `GroupID` (`GroupID`),
  CONSTRAINT `membership_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `membership_ibfk_2` FOREIGN KEY (`GroupID`) REFERENCES `record` (`GroupID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membership`
--

LOCK TABLES `membership` WRITE;
/*!40000 ALTER TABLE `membership` DISABLE KEYS */;
INSERT INTO `membership` VALUES (13,1,8,6),(14,0,7,6),(15,1,7,7),(16,0,9,7),(17,1,9,8),(18,0,7,8),(19,0,8,8),(20,0,9,6),(21,0,9,6);
/*!40000 ALTER TABLE `membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poll repository`
--

DROP TABLE IF EXISTS `poll repository`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `poll repository` (
  `PollID` int(11) NOT NULL AUTO_INCREMENT,
  `LeaderID` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `ResponseOptions` int(11) DEFAULT NULL,
  `PollDescription` text,
  PRIMARY KEY (`PollID`),
  KEY `LeaderID` (`LeaderID`),
  CONSTRAINT `poll repository_ibfk_1` FOREIGN KEY (`LeaderID`) REFERENCES `membership` (`MemberID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poll repository`
--

LOCK TABLES `poll repository` WRITE;
/*!40000 ALTER TABLE `poll repository` DISABLE KEYS */;
/*!40000 ALTER TABLE `poll repository` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poll response`
--

DROP TABLE IF EXISTS `poll response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `poll response` (
  `ResponseID` int(11) NOT NULL AUTO_INCREMENT,
  `memberID` int(11) NOT NULL,
  `userResponse` int(11) NOT NULL,
  `DateAndTime` datetime NOT NULL,
  PRIMARY KEY (`ResponseID`),
  KEY `memberID` (`memberID`),
  CONSTRAINT `poll response_ibfk_1` FOREIGN KEY (`memberID`) REFERENCES `membership` (`MemberID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poll response`
--

LOCK TABLES `poll response` WRITE;
/*!40000 ALTER TABLE `poll response` DISABLE KEYS */;
/*!40000 ALTER TABLE `poll response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `record` (
  `GroupID` int(11) NOT NULL AUTO_INCREMENT,
  `GroupName` varchar(255) NOT NULL,
  `LeaderID` int(11) NOT NULL,
  `GroupDescription` text,
  PRIMARY KEY (`GroupID`),
  KEY `LeaderID` (`LeaderID`),
  CONSTRAINT `record_ibfk_1` FOREIGN KEY (`LeaderID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` VALUES (6,'Zach\'s Group',8,'Optional description for Zach\'s Group'),(7,'Stephen\'s Group',7,'Optional description for Stephen\'s Group'),(8,'Hannah\'s Group',9,'Optional description for Hannah\'s group');
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `request` (
  `RequestID` int(11) NOT NULL AUTO_INCREMENT,
  `GroupID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  PRIMARY KEY (`RequestID`),
  KEY `UserID` (`UserID`),
  KEY `GroupID` (`GroupID`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `request_ibfk_2` FOREIGN KEY (`GroupID`) REFERENCES `record` (`GroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `EmailAddress` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `RegistrationDate` date NOT NULL,
  `ProfilePicURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `EmailAddress` (`EmailAddress`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (7,'Stephen','Styffe','stephen.e.styffe@biola.edu','b@dp@$$w0rd','2019-02-22',NULL),(8,'Zach','Chester','zach.chester@biola.edu','h0rr1bl3p@$$w0rd','2019-02-18',NULL),(9,'Hannah','Kim','hannah.kim002@biola.edu','r1d1cul0u$p@$$w0rd','2019-02-23',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-25 19:54:30
