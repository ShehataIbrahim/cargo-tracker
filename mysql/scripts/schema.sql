CREATE SCHEMA IF NOT EXISTS `bookingdb` DEFAULT CHARACTER SET utf8 ;
USE `bookingdb`;
CREATE USER 'booking'@'%' IDENTIFIED BY 'booking';
GRANT ALL PRIVILEGES ON bookingdb.* TO 'booking'@'%';
CREATE TABLE IF NOT EXISTS `cargo` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `booking_id` VARCHAR(20) NOT NULL,
    `transport_status` VARCHAR(100) NOT NULL,
    `routing_status` VARCHAR(100) NOT NULL,
    `spec_origin_id` VARCHAR(20) DEFAULT NULL,
    `spec_destination_id` VARCHAR(20) DEFAULT NULL,
    `spec_arrival_deadline` DATE DEFAULT NULL,
    `origin_id` VARCHAR(20) DEFAULT NULL,
    `booking_amount` INT(11) NOT NULL,
    `handling_event_id` INT(11) DEFAULT NULL,
    `next_expected_location_id` VARCHAR(20) DEFAULT NULL,
    `next_expected_handling_event_type` VARCHAR(20) DEFAULT NULL,
    `next_expected_voyage_id` VARCHAR(20) DEFAULT NULL,
    `last_known_location_id` VARCHAR(20) DEFAULT NULL,
    `current_voyage_number` VARCHAR(100) DEFAULT NULL,
    `last_handling_event_id` INT(11) DEFAULT NULL,
    `last_handling_event_type` VARCHAR(20) DEFAULT NULL,
    `last_handling_event_location` VARCHAR(20) DEFAULT NULL,
    `last_handling_event_voyage` VARCHAR(20) DEFAULT NULL,
    PRIMARY KEY (`ID`)
);

CREATE TABLE IF NOT EXISTS `leg` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `load_time` TIMESTAMP NULL DEFAULT NULL,
    `unload_time` TIMESTAMP NULL DEFAULT NULL,
    `load_location_id` VARCHAR(20) DEFAULT NULL,
    `unload_location_id` VARCHAR(20) DEFAULT NULL,
    `voyage_number` VARCHAR(100) DEFAULT NULL,
    `cargo_id` INT(11) DEFAULT NULL,
    PRIMARY KEY (`ID`)
);
    
CREATE TABLE IF NOT EXISTS `location` (
    `id` INT(11) DEFAULT NULL,
    `name` VARCHAR(50) DEFAULT NULL,
    `unlocode` VARCHAR(100) DEFAULT NULL
);
    
    
CREATE SCHEMA IF NOT EXISTS `handlingdb` DEFAULT CHARACTER SET utf8 ;
USE `handlingdb`;
CREATE USER 'handling'@'%' IDENTIFIED BY 'handling';
GRANT ALL PRIVILEGES ON handlingdb.* TO 'handling'@'%';
CREATE TABLE IF NOT EXISTS `handling_activity` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `event_completion_time` TIMESTAMP NULL DEFAULT NULL,
    `event_type` VARCHAR(225) DEFAULT NULL,
    `booking_id` VARCHAR(20) DEFAULT NULL,
    `voyage_number` VARCHAR(100) DEFAULT NULL,
    `location` VARCHAR(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
    
    
CREATE SCHEMA IF NOT EXISTS `routingdb` DEFAULT CHARACTER SET utf8 ;
    USE `routingdb`;
    CREATE USER 'routing'@'%' IDENTIFIED BY 'routing';
    GRANT ALL PRIVILEGES ON routingdb.* TO 'routing'@'%';
	
	CREATE TABLE IF NOT EXISTS `voyage` (
    `Id` INT(11) NOT NULL AUTO_INCREMENT,
    `voyage_number` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`Id`)
);
    
CREATE TABLE IF NOT EXISTS `carrier_movement` (
    `Id` INT(11) NOT NULL AUTO_INCREMENT,
    `arrival_location_id` VARCHAR(100) DEFAULT NULL,
    `departure_location_id` VARCHAR(100) DEFAULT NULL,
    `voyage_id` INT(11) DEFAULT NULL,
    `arrival_date` DATE DEFAULT NULL,
    `departure_date` DATE DEFAULT NULL,
    PRIMARY KEY (`Id`)
);
    
insert voyage (Id,voyage_number) values(1,'0001V');
insert voyage (Id,voyage_number) values(2,'0002V');
insert voyage (Id,voyage_number) values(3,'0003V');
insert voyage (Id,voyage_number) values(4,'0004V');

insert into carrier_movement (arrival_location_id,departure_location_id,voyage_id,arrival_date,departure_date) 		values ('PLKRK','PLWAW',1,'2022-01-08','2022-01-03');
insert into carrier_movement (arrival_location_id,departure_location_id,voyage_id,arrival_date,departure_date) 		values ('PLKRK','EGCAI',2,'2022-01-10','2022-01-04');
insert into carrier_movement (arrival_location_id,departure_location_id,voyage_id,arrival_date,departure_date) 		values ('EGALX','PLWRO',3,'2022-01-12','2022-01-05');
insert into carrier_movement (arrival_location_id,departure_location_id,voyage_id,arrival_date,departure_date) 		values ('PLWRO','PLWAW',4,'2022-01-14','2022-01-07');


CREATE SCHEMA IF NOT EXISTS `trackingdb` DEFAULT CHARACTER SET utf8 ;
USE `trackingdb`;
CREATE USER 'tracking'@'%' IDENTIFIED BY 'tracking';
GRANT ALL PRIVILEGES ON trackingdb.* TO 'tracking'@'%';
CREATE TABLE IF NOT EXISTS `tracking_activity` (
    `Id` INT(11) NOT NULL AUTO_INCREMENT,
    `tracking_number` VARCHAR(20) NOT NULL,
    `booking_id` VARCHAR(20) DEFAULT NULL,
    PRIMARY KEY (`Id`)
);
	
CREATE TABLE IF NOT EXISTS `tracking_handling_events` (
    `Id` INT(11) NOT NULL AUTO_INCREMENT,
    `tracking_id` INT(11) DEFAULT NULL,
    `event_type` VARCHAR(225) DEFAULT NULL,
    `event_time` TIMESTAMP NULL DEFAULT NULL,
    `location_id` VARCHAR(100) DEFAULT NULL,
    `voyage_number` VARCHAR(20) DEFAULT NULL,
    PRIMARY KEY (`Id`)
);

