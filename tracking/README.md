# Tracking Microservice

## Brief

This MS handles all tracking related operations It consumes CargoHandledEvent and CargoRoutedEvent events to build the
trace

---

## Setup

### Database details:

#### schema name: *trackingdb*

#### Username: *tracking*

#### password: *tracking*

### Database Script:

    CREATE SCHEMA IF NOT EXISTS `trackingdb` DEFAULT CHARACTER SET utf8 ;
    USE `trackingdb`;
    CREATE USER 'tracking'@'%' IDENTIFIED BY 'tracking';
    GRANT ALL PRIVILEGES ON trackingdb.* TO 'tracking'@'%';
    CREATE TABLE IF NOT EXISTS `tracking_activity` (
	  `Id` int(11) NOT NULL AUTO_INCREMENT,
	  `tracking_number` varchar(20) NOT NULL,
	  `booking_id` varchar(20) DEFAULT NULL,
	  PRIMARY KEY (`Id`)
	);
	
    CREATE TABLE IF NOT EXISTS `tracking_handling_events` (
	  `Id` int(11) NOT NULL AUTO_INCREMENT,
	  `tracking_id` int(11) DEFAULT NULL,
	  `event_type` varchar(225) DEFAULT NULL,
	  `event_time` timestamp NULL DEFAULT NULL,
	  `location_id` varchar(100) DEFAULT NULL,
	  `voyage_number` varchar(20) DEFAULT NULL,
	  PRIMARY KEY (`Id`)
	);

###[Back](../README.md)