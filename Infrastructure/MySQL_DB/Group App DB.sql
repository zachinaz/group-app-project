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
  `AnnouncementID` int(11) NOT NULL,
  `LeaderID` int(11) NOT NULL,
  `DateAndTime` datetime NOT NULL,
  `content` longtext,
  PRIMARY KEY (`AnnouncementID`),
  UNIQUE KEY `AnnouncementID` (`AnnouncementID`),
  KEY `LeaderID` (`LeaderID`),
  CONSTRAINT `announcement_ibfk_1` FOREIGN KEY (`LeaderID`) REFERENCES `membership` (`MemberID`)
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
  `CommentID` int(11) NOT NULL,
  `MemberID` int(11) NOT NULL,
  `DateAndTime` datetime NOT NULL,
  `content` text,
  PRIMARY KEY (`CommentID`),
  UNIQUE KEY `CommentID` (`CommentID`),
  KEY `MemberID` (`MemberID`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`MemberID`) REFERENCES `membership` (`MemberID`)
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
  `EventID` int(11) NOT NULL,
  `DateAndTime` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `LeaderID` int(11) NOT NULL,
  `Location` varchar(255) NOT NULL,
  `Description` text,
  PRIMARY KEY (`EventID`),
  UNIQUE KEY `EventID` (`EventID`),
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
  `MemberID` int(11) NOT NULL,
  `UserPrivileges` tinyint(1) NOT NULL,
  `UserID` int(11) NOT NULL,
  `GroupID` int(11) NOT NULL,
  PRIMARY KEY (`MemberID`),
  UNIQUE KEY `MemberID` (`MemberID`),
  KEY `UserID` (`UserID`),
  KEY `GroupID` (`GroupID`),
  CONSTRAINT `membership_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `membership_ibfk_2` FOREIGN KEY (`GroupID`) REFERENCES `record` (`GroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membership`
--

LOCK TABLES `membership` WRITE;
/*!40000 ALTER TABLE `membership` DISABLE KEYS */;
/*!40000 ALTER TABLE `membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poll repository`
--

DROP TABLE IF EXISTS `poll repository`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `poll repository` (
  `PollID` int(11) NOT NULL,
  `LeaderID` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `ResponseOptions` int(11) DEFAULT NULL,
  `PollDescription` text,
  PRIMARY KEY (`PollID`),
  UNIQUE KEY `PollID` (`PollID`),
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
  `ResponseID` int(11) NOT NULL,
  `memberID` int(11) NOT NULL,
  `userResponse` int(11) NOT NULL,
  `DateAndTime` datetime NOT NULL,
  PRIMARY KEY (`ResponseID`),
  UNIQUE KEY `ResponseID` (`ResponseID`),
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
  `GroupID` int(11) NOT NULL,
  `LeaderID` int(11) NOT NULL,
  `GroupName` varchar(255) NOT NULL,
  `LeaderName` varchar(255) DEFAULT NULL,
  `GroupDescription` text,
  PRIMARY KEY (`GroupID`),
  UNIQUE KEY `GroupID` (`GroupID`),
  KEY `LeaderID` (`LeaderID`),
  CONSTRAINT `record_ibfk_1` FOREIGN KEY (`LeaderID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `request` (
  `RequestID` int(11) NOT NULL,
  `GroupID` int(11) NOT NULL,
  `GroupName` varchar(255) NOT NULL,
  `UserID` int(11) NOT NULL,
  PRIMARY KEY (`RequestID`),
  UNIQUE KEY `RequestID` (`RequestID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
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
  `UserID` int(11) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `EmailAddress` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `RegistrationDate` date NOT NULL,
  `ProfilePicURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `UserID` (`UserID`),
  UNIQUE KEY `EmailAddress` (`EmailAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2019-02-22 11:39:36
