/*
SQLyog Ultimate v8.55 
MySQL - 5.1.39-community : Database - seleniumkeyword
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`seleniumkeyword` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `seleniumkeyword`;

/*Table structure for table `project_mst` */

DROP TABLE IF EXISTS `project_mst`;

CREATE TABLE `project_mst` (
  `project_id` varchar(10) NOT NULL,
  `project_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `project_mst` */

insert  into `project_mst`(`project_id`,`project_name`) values ('PRJ001','Leave Manager');

/*Table structure for table `testcase_mst` */

DROP TABLE IF EXISTS `testcase_mst`;

CREATE TABLE `testcase_mst` (
  `testcase_id` varchar(10) NOT NULL,
  `priority` int(1) DEFAULT NULL,
  `module_name` varchar(100) DEFAULT NULL,
  `test_designed_by` varchar(200) DEFAULT NULL,
  `testcase_title` varchar(255) DEFAULT NULL,
  `testcase_description` varchar(800) DEFAULT NULL,
  `testcase_preexecute` varchar(100) DEFAULT NULL COMMENT 'comma seperated Test case Ids which should be executed before this test case',
  `project_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`testcase_id`),
  KEY `FK_testcase_mst` (`project_id`),
  CONSTRAINT `FK_testcase_mst` FOREIGN KEY (`project_id`) REFERENCES `project_mst` (`project_id`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `testcase_mst` */

insert  into `testcase_mst`(`testcase_id`,`priority`,`module_name`,`test_designed_by`,`testcase_title`,`testcase_description`,`testcase_preexecute`,`project_id`) values ('TST001',1,NULL,'Naresh','Test Login','Test Login with LDAP',NULL,'PRJ001'),('TST002',0,'PAYMENT',NULL,'Test','Test','',NULL);

/*Table structure for table `testcase_steps_mst` */

DROP TABLE IF EXISTS `testcase_steps_mst`;

CREATE TABLE `testcase_steps_mst` (
  `testcase_id` varchar(10) DEFAULT NULL,
  `sequence_no` int(4) NOT NULL,
  `keyword` varchar(100) DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `input_data` varchar(200) DEFAULT NULL,
  `teststep_id` varchar(10) NOT NULL,
  `object_name` varchar(100) DEFAULT NULL,
  `object_type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`teststep_id`),
  KEY `FK_testcase_steps_mst` (`testcase_id`),
  CONSTRAINT `FK_testcase_steps_mst` FOREIGN KEY (`testcase_id`) REFERENCES `testcase_mst` (`testcase_id`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `testcase_steps_mst` */

insert  into `testcase_steps_mst`(`testcase_id`,`sequence_no`,`keyword`,`description`,`input_data`,`teststep_id`,`object_name`,`object_type`) values ('TST001',1,'GOTOURL',NULL,'https://rally1.rallydev.com/slm/login.op','TST001',NULL,NULL),('TST001',2,'SETTEXT',NULL,'naresh.mahajan@omniscient.co.in','TST002','j_username','ID');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
