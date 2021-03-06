# Booking Microservice

## Brief

This MS handles all booking related operations

---

## Setup

### Database details:

#### schema name: *bookingdb*

#### Username: *booking*

#### password: *booking*

### Database Script:

    CREATE SCHEMA IF NOT EXISTS `bookingdb` DEFAULT CHARACTER SET utf8 ;
    USE `bookingdb`;
    CREATE USER 'booking'@'%' IDENTIFIED BY 'booking';
    GRANT ALL PRIVILEGES ON bookingdb.* TO 'booking'@'%';
    CREATE TABLE IF NOT EXISTS `cargo` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `booking_id` varchar(20) NOT NULL,
    `transport_status` varchar(100) NOT NULL,
    `routing_status` varchar(100) NOT NULL,
    `spec_origin_id` varchar(20) DEFAULT NULL,
    `spec_destination_id` varchar(20) DEFAULT NULL,
    `spec_arrival_deadline` date DEFAULT NULL,
    `origin_id` varchar(20) DEFAULT NULL,
    `booking_amount` int(11) NOT NULL,
    `handling_event_id` int(11) DEFAULT NULL,
    `next_expected_location_id` varchar(20) DEFAULT NULL,
    `next_expected_handling_event_type` varchar(20) DEFAULT NULL,
    `next_expected_voyage_id` varchar(20) DEFAULT NULL,
    `last_known_location_id` varchar(20) DEFAULT NULL,
    `current_voyage_number` varchar(100) DEFAULT NULL,
    `last_handling_event_id` int(11) DEFAULT NULL,
    `last_handling_event_type` varchar(20) DEFAULT NULL,
    `last_handling_event_location` varchar(20) DEFAULT NULL,
    `last_handling_event_voyage` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`ID`)
    ) ;

    CREATE TABLE IF NOT EXISTS `leg` (
	  `id` int(11) NOT NULL AUTO_INCREMENT,
	  `load_time` timestamp NULL DEFAULT NULL,
	  `unload_time` timestamp NULL DEFAULT NULL,
	  `load_location_id` varchar(20) DEFAULT NULL,
	  `unload_location_id` varchar(20) DEFAULT NULL,
	  `voyage_number` varchar(100) DEFAULT NULL,
	  `cargo_id` int(11) DEFAULT NULL,
	  PRIMARY KEY (`ID`)
	);
    
    CREATE TABLE IF NOT EXISTS `location` (
  	`id` int(11) DEFAULT NULL,
  	`name` varchar(50) DEFAULT NULL,
  	`unlocode` varchar(100) DEFAULT NULL
	);

### Run And Test
Run the jar using *java -jar* command you can navigate the service via Swagger
UI [http://localhost:{randomPort}/booking/swagger-ui/#/](http://localhost:{randomPort}/booking/swagger-ui/#/)

Test the service using the below request

###### Create Booking

*POST* [http://localhost:{randomPort}/booking/book](http://localhost:{randomPort}/booking/book)

JSON Body

    {
        "bookingAmount": 100,
        "originLocation": "PLKRK",
        "destLocation" : "EGCAI",
        "destArrivalDeadline" : "2022-01-02"
    }

This returns a unique "Booking Id" which should be used as universal identifier

###### Booking Routing

*POST* [http://localhost:{randomPort}/booking/route](http://localhost:{randomPort}/booking/route)

JSON Body

    {
      "bookingId": "<<BookingId>>"
    }

### [Back](../README.md)