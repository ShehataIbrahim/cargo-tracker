# cargo-tracker

The application is implemented as DDD implementation using Spring Boot
The implementation is a microservices based architecture using the below technologies:
- Spring Boot
- Spring Cloud streams for microservices choreography infrastructure
- RabbitMQ as message broker
- Spring Data for data management
- Eureka Server as Discovery service

## How to import
> #####Import as either gradle project or Intellij Project "From Existing Sources"
## How to setup
- Get a running instance from mysql (either local installation - VM or docker )
    - for docker instance you can
      > docker run -p 3306:3306 --name root -e MYSQL_ROOT_PASSWORD=root -d mysql:latest
    - in that case you need to create user for each microservice
- Get a running instance of RabbitMQ ...you can also have a docker container running
  >docker run -d --hostname localhost --name my-tracker -p 15672:15672 rabbitmq:3-management

- ###Setup each Microservice separately
    ####[Booking](booking/README.md)
    ####[Handling](handling/README.md)
    ####[Routing](routing/README.md)
    ####[Tracking](tracking/README.md)
    ####[Service Discovery](service-discovery/README.md)