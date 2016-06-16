-- MySQL dump 10.13  Distrib 5.5.47, for Win64 (x86)
--
-- Host: localhost    Database: 11_DB
-- ------------------------------------------------------
-- Server version	5.5.47

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
-- Temporary table structure for view `ase_info`
--

DROP TABLE IF EXISTS `ase_info`;
/*!50001 DROP VIEW IF EXISTS `ase_info`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `ase_info` (
  `nom_net` tinyint NOT NULL,
  `tolerance` tinyint NOT NULL,
  `pb_id` tinyint NOT NULL,
  `ib_id` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredient` (
  `ingredient_id` int(11) NOT NULL,
  `ingredient_name` text,
  `supplier` text,
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ingredient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (1,'Lemon','Lemonland',1),(2,'Water','Waterland',1),(3,'Berry','The bush',1),(4,'Nuts','Nuttree',0),(5,'Soda','Fizzysprings',1);
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredientbatch`
--

DROP TABLE IF EXISTS `ingredientbatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredientbatch` (
  `ib_id` int(11) NOT NULL,
  `ingredient_id` int(11) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `recieveDate` date DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ib_id`),
  KEY `ingredient_id` (`ingredient_id`),
  CONSTRAINT `ingredientbatch_ibfk_1` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`ingredient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientbatch`
--

LOCK TABLES `ingredientbatch` WRITE;
/*!40000 ALTER TABLE `ingredientbatch` DISABLE KEYS */;
INSERT INTO `ingredientbatch` VALUES (1,2,1,'2003-03-15',1),(2,1,7.5,'2015-06-16',1),(3,2,0.1,'2001-01-13',0),(4,4,2,'1931-01-23',1),(5,3,1,'1990-09-08',1),(6,5,2,'2014-05-05',1);
/*!40000 ALTER TABLE `ingredientbatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `ingredientbatch_administration`
--

DROP TABLE IF EXISTS `ingredientbatch_administration`;
/*!50001 DROP VIEW IF EXISTS `ingredientbatch_administration`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `ingredientbatch_administration` (
  `ib_id` tinyint NOT NULL,
  `ingredient_name` tinyint NOT NULL,
  `ingredient_id` tinyint NOT NULL,
  `amount` tinyint NOT NULL,
  `active` tinyint NOT NULL,
  `recieveDate` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `number_components`
--

DROP TABLE IF EXISTS `number_components`;
/*!50001 DROP VIEW IF EXISTS `number_components`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `number_components` (
  `recipe_id` tinyint NOT NULL,
  `antal_Comp` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `number_done`
--

DROP TABLE IF EXISTS `number_done`;
/*!50001 DROP VIEW IF EXISTS `number_done`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `number_done` (
  `pb_id` tinyint NOT NULL,
  `antal_faerdige` tinyint NOT NULL,
  `recipe_id` tinyint NOT NULL,
  `startdate` tinyint NOT NULL,
  `endDate` tinyint NOT NULL,
  `status` tinyint NOT NULL,
  `active` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `operator`
--

DROP TABLE IF EXISTS `operator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operator` (
  `opr_id` int(11) NOT NULL,
  `opr_name` text,
  `cpr` text,
  `password` text,
  `role_id` int(11) NOT NULL,
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`opr_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `operator_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operator`
--

LOCK TABLES `operator` WRITE;
/*!40000 ALTER TABLE `operator` DISABLE KEYS */;
INSERT INTO `operator` VALUES (1,'Niels Matthiessen','1910948899','Qwer1234',1,1),(2,'Sofie Paludan Larsen','1910945588','Qwer1234',2,1),(3,'Casper Danielsen','1910942233','Qwer1234',3,1),(4,'Brian Christensen','1910943399','Qwer1234',4,1),(5,'Morten Due','1910941177','Qwer1234',3,0),(6,'Cecilie Lindberg','1910942222','Qwer1234',2,0);
/*!40000 ALTER TABLE `operator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productbatch`
--

DROP TABLE IF EXISTS `productbatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productbatch` (
  `pb_id` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `recipe_id` int(11) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`pb_id`),
  KEY `recipe_id` (`recipe_id`),
  CONSTRAINT `productbatch_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productbatch`
--

LOCK TABLES `productbatch` WRITE;
/*!40000 ALTER TABLE `productbatch` DISABLE KEYS */;
INSERT INTO `productbatch` VALUES (1,0,1,'2003-04-05',NULL,1),(2,2,2,'2006-06-16','2016-06-13',1),(3,0,2,'2005-06-07',NULL,1),(4,2,3,'2015-05-20','2016-06-06',1),(5,1,4,'2016-03-20',NULL,0);
/*!40000 ALTER TABLE `productbatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `productbatch_administration`
--

DROP TABLE IF EXISTS `productbatch_administration`;
/*!50001 DROP VIEW IF EXISTS `productbatch_administration`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `productbatch_administration` (
  `pb_id` tinyint NOT NULL,
  `recipe_id` tinyint NOT NULL,
  `recipe_name` tinyint NOT NULL,
  `antal_Comp` tinyint NOT NULL,
  `antal_faerdige` tinyint NOT NULL,
  `startdate` tinyint NOT NULL,
  `endDate` tinyint NOT NULL,
  `status` tinyint NOT NULL,
  `active` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `productbatchcomponent`
--

DROP TABLE IF EXISTS `productbatchcomponent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productbatchcomponent` (
  `pb_id` int(11) NOT NULL DEFAULT '0',
  `ib_id` int(11) NOT NULL DEFAULT '0',
  `tara` double DEFAULT NULL,
  `net` double DEFAULT NULL,
  `opr_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pb_id`,`ib_id`),
  KEY `ib_id` (`ib_id`),
  KEY `opr_id` (`opr_id`),
  CONSTRAINT `productbatchcomponent_ibfk_1` FOREIGN KEY (`pb_id`) REFERENCES `productbatch` (`pb_id`),
  CONSTRAINT `productbatchcomponent_ibfk_2` FOREIGN KEY (`ib_id`) REFERENCES `ingredientbatch` (`ib_id`),
  CONSTRAINT `productbatchcomponent_ibfk_3` FOREIGN KEY (`opr_id`) REFERENCES `operator` (`opr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productbatchcomponent`
--

LOCK TABLES `productbatchcomponent` WRITE;
/*!40000 ALTER TABLE `productbatchcomponent` DISABLE KEYS */;
INSERT INTO `productbatchcomponent` VALUES (2,5,0.9,2.9,2),(4,1,1,0.5,3),(4,6,1,2,3),(5,2,2,0.4,4);
/*!40000 ALTER TABLE `productbatchcomponent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipe` (
  `recipe_id` int(11) NOT NULL,
  `recipe_name` text,
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`recipe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,'Lemonwater',1),(2,'Pie',1),(3,'Sodawater',1),(4,'Berrywater',0);
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `recipe_administration`
--

DROP TABLE IF EXISTS `recipe_administration`;
/*!50001 DROP VIEW IF EXISTS `recipe_administration`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `recipe_administration` (
  `recipe_id` tinyint NOT NULL,
  `recipe_name` tinyint NOT NULL,
  `ingredient_id` tinyint NOT NULL,
  `ingredient_name` tinyint NOT NULL,
  `tolerance` tinyint NOT NULL,
  `nom_net` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `recipecomponent`
--

DROP TABLE IF EXISTS `recipecomponent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipecomponent` (
  `recipe_id` int(11) NOT NULL DEFAULT '0',
  `ingredient_id` int(11) NOT NULL DEFAULT '0',
  `nom_net` double DEFAULT NULL,
  `tolerance` double DEFAULT NULL,
  PRIMARY KEY (`recipe_id`,`ingredient_id`),
  KEY `ingredient_id` (`ingredient_id`),
  CONSTRAINT `recipecomponent_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`),
  CONSTRAINT `recipecomponent_ibfk_2` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`ingredient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipecomponent`
--

LOCK TABLES `recipecomponent` WRITE;
/*!40000 ALTER TABLE `recipecomponent` DISABLE KEYS */;
INSERT INTO `recipecomponent` VALUES (1,1,0.5,0.1),(1,2,5.1,0.2),(2,3,3,0.1),(3,2,2,0.2),(3,5,0.5,0.1),(4,2,5,0.1),(4,3,2,0.2),(4,4,1,0.2);
/*!40000 ALTER TABLE `recipecomponent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` text,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Administrator'),(2,'Farmaceut'),(3,'Værkfører'),(4,'Operatør');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `ase_info`
--

/*!50001 DROP TABLE IF EXISTS `ase_info`*/;
/*!50001 DROP VIEW IF EXISTS `ase_info`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ase_info` AS select `recipecomponent`.`nom_net` AS `nom_net`,`recipecomponent`.`tolerance` AS `tolerance`,`productbatch`.`pb_id` AS `pb_id`,`ingredientbatch`.`ib_id` AS `ib_id` from ((`productbatch` join `ingredientbatch` on((`productbatch`.`active` = `ingredientbatch`.`active`))) join `recipecomponent` on(((`productbatch`.`recipe_id` = `recipecomponent`.`recipe_id`) and (`ingredientbatch`.`ingredient_id` = `recipecomponent`.`ingredient_id`)))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `ingredientbatch_administration`
--

/*!50001 DROP TABLE IF EXISTS `ingredientbatch_administration`*/;
/*!50001 DROP VIEW IF EXISTS `ingredientbatch_administration`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ingredientbatch_administration` AS select `ingredientbatch`.`ib_id` AS `ib_id`,`ingredient`.`ingredient_name` AS `ingredient_name`,`ingredient`.`ingredient_id` AS `ingredient_id`,`ingredientbatch`.`amount` AS `amount`,`ingredientbatch`.`active` AS `active`,`ingredientbatch`.`recieveDate` AS `recieveDate` from (`ingredient` join `ingredientbatch` on((`ingredient`.`ingredient_id` = `ingredientbatch`.`ingredient_id`))) order by `ingredientbatch`.`ib_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `number_components`
--

/*!50001 DROP TABLE IF EXISTS `number_components`*/;
/*!50001 DROP VIEW IF EXISTS `number_components`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `number_components` AS select `recipecomponent`.`recipe_id` AS `recipe_id`,count(`recipecomponent`.`ingredient_id`) AS `antal_Comp` from `recipecomponent` group by `recipecomponent`.`recipe_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `number_done`
--

/*!50001 DROP TABLE IF EXISTS `number_done`*/;
/*!50001 DROP VIEW IF EXISTS `number_done`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `number_done` AS select `productbatch`.`pb_id` AS `pb_id`,count(`productbatchcomponent`.`pb_id`) AS `antal_faerdige`,`productbatch`.`recipe_id` AS `recipe_id`,`productbatch`.`startDate` AS `startdate`,`productbatch`.`endDate` AS `endDate`,`productbatch`.`status` AS `status`,`productbatch`.`active` AS `active` from (`productbatch` left join `productbatchcomponent` on((`productbatchcomponent`.`pb_id` = `productbatch`.`pb_id`))) group by `productbatch`.`pb_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `productbatch_administration`
--

/*!50001 DROP TABLE IF EXISTS `productbatch_administration`*/;
/*!50001 DROP VIEW IF EXISTS `productbatch_administration`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `productbatch_administration` AS select `number_done`.`pb_id` AS `pb_id`,`number_components`.`recipe_id` AS `recipe_id`,`recipe`.`recipe_name` AS `recipe_name`,`number_components`.`antal_Comp` AS `antal_Comp`,`number_done`.`antal_faerdige` AS `antal_faerdige`,`number_done`.`startdate` AS `startdate`,`number_done`.`endDate` AS `endDate`,`number_done`.`status` AS `status`,`number_done`.`active` AS `active` from ((`number_components` join `number_done` on((`number_components`.`recipe_id` = `number_done`.`recipe_id`))) left join `recipe` on((`number_components`.`recipe_id` = `recipe`.`recipe_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `recipe_administration`
--

/*!50001 DROP TABLE IF EXISTS `recipe_administration`*/;
/*!50001 DROP VIEW IF EXISTS `recipe_administration`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `recipe_administration` AS select `recipe`.`recipe_id` AS `recipe_id`,`recipe`.`recipe_name` AS `recipe_name`,`recipecomponent`.`ingredient_id` AS `ingredient_id`,`ingredient`.`ingredient_name` AS `ingredient_name`,`recipecomponent`.`tolerance` AS `tolerance`,`recipecomponent`.`nom_net` AS `nom_net` from ((`recipe` join `recipecomponent` on((`recipe`.`recipe_id` = `recipecomponent`.`recipe_id`))) join `ingredient` on((`recipecomponent`.`ingredient_id` = `ingredient`.`ingredient_id`))) */;
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

-- Dump completed on 2016-06-16 18:10:52
