# Routing Microservice

## Brief

This MS handles all cargo routing related operations

---

## Setup

### Database details:

#### schema name: *routingdb*

#### Username: *routing*

#### password: *routing*

### Database Script:

    CREATE SCHEMA IF NOT EXISTS `routingdb` DEFAULT CHARACTER SET utf8 ;
    USE `routingdb`;
	
	
	CREATE TABLE IF NOT EXISTS `voyage` (
  	`Id` int(11) NOT NULL AUTO_INCREMENT,
  	`voyage_number` varchar(20) NOT NULL,
  	PRIMARY KEY (`Id`)
	);
    
    CREATE TABLE IF NOT EXISTS `carrier_movement` (
	  `Id` int(11) NOT NULL AUTO_INCREMENT,
	  `arrival_location_id` varchar(100) DEFAULT NULL,
	  `departure_location_id` varchar(100) DEFAULT NULL,
	  `voyage_id` int(11) DEFAULT NULL,
	  `arrival_date` date DEFAULT NULL,
	  `departure_date` date DEFAULT NULL,
	  PRIMARY KEY (`Id`)
	);

_Sample data in order to properly test the service_

    insert voyage (Id,voyage_number) values(1,'0001V');
	insert voyage (Id,voyage_number) values(2,'0002V');
	insert voyage (Id,voyage_number) values(3,'0003V');
	insert voyage (Id,voyage_number) values(4,'0004V');

	insert into carrier_movement (arrival_location_id,departure_location_id,voyage_id,arrival_date,departure_date) 		values ('PLKRK','PLWAW',1,'2022-01-08','2022-01-03');
	insert into carrier_movement (arrival_location_id,departure_location_id,voyage_id,arrival_date,departure_date) 		values ('PLKRK','EGCAI',2,'2022-01-10','2022-01-04');
	insert into carrier_movement (arrival_location_id,departure_location_id,voyage_id,arrival_date,departure_date) 		values ('EGALX','PLWRO',3,'2022-01-12','2022-01-05');
	insert into carrier_movement (arrival_location_id,departure_location_id,voyage_id,arrival_date,departure_date) 		values ('PLWRO','PLWAW',4,'2022-01-14','2022-01-07');

No RabbitMQ specific configuration ... as per architecture this doesn't produce/consume any events

---

### Run And Test

Run the jar using *java -jar* command you can navigate the service via Swagger
UI http://localhost:7260/routing/swagger-ui/#/

*GET* http://localhost:7260/routing/optimalRoute?deadline=NA&destination=PLWAW&origin=PLKRK

### [Back](../README.md)