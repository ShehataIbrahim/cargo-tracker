# Handling Microservice

## Brief

This MS handles all handling related operations

---

## Setup

### Database details:

#### schema name: *handlingdb*

#### Username: *handling*

#### password: *handling*

### Database Script:

	CREATE SCHEMA IF NOT EXISTS `handlingdb` DEFAULT CHARACTER SET utf8 ;
	USE `handlingdb`;
	CREATE TABLE IF NOT EXISTS `handling_activity` (
	  `id` int(11) NOT NULL AUTO_INCREMENT,
	  `event_completion_time` timestamp NULL DEFAULT NULL,
	  `event_type` varchar(225) DEFAULT NULL,
	  `booking_id` varchar(20) DEFAULT NULL,
	  `voyage_number` varchar(100) DEFAULT NULL,
	  `location` varchar(100) DEFAULT NULL,
	  PRIMARY KEY (`id`)
	);

### Run And Test

Run the jar using *java -jar* command you can navigate the service via Swagger
UI [http://localhost:{randomPort}/booking/swagger-ui/#/](http://localhost:{randomPort}/booking/swagger-ui/#/)

you can also use this [postman collection](postman/Handling.postman_collection.json)
Test the service using the below requests in sequence

    {
        "bookingId": "{{bookingId}}",
        "unLocode": "PLKRK",
        "handlingType": "RECEIVE",
        "completionTime": "2022-01-01",
        "voyageNumber": ""
    }
    
    {
        "bookingId": "{{bookingId}}",
        "unLocode": "PLKRK",
        "handlingType": "LOAD",
        "completionTime": "2022-01-02",
        "voyageNumber": "0001V"
    }
    
    {
        "bookingId": "{{bookingId}}",
        "unLocode": "PLWRO",
        "handlingType": "UNLOAD",
        "completionTime": "2022-01-02",
        "voyageNumber": "0001V"
    }
    
    {
        "bookingId": "{{bookingId}}",
        "unLocode": "PLWRO",
        "handlingType": "LOAD",
        "completionTime": "2022-01-02",
        "voyageNumber": "0004V"
    }
    
    {
        "bookingId": "{{bookingId}}",
        "unLocode": "PLWAW",
        "handlingType": "UNLOAD",
        "completionTime": "2022-01-03",
        "voyageNumber": "0004V"
    }
    
    {
        "bookingId": "{{bookingId}}",
        "unLocode": "PLWAW",
        "handlingType": "CUSTOMS",
        "completionTime": "2022-01-03",
        "voyageNumber": ""
    }
    
    {
        "bookingId": "{{bookingId}}",
        "unLocode": "PLKRK",
        "handlingType": "CLAIM",
        "completionTime": "2022-01-04",
        "voyageNumber": ""
    }

### [Back](../README.md)