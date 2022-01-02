package com.streams.tracker.booking.internal.command;

import com.streams.tracker.booking.domain.aggregates.BookingId;
import com.streams.tracker.booking.domain.aggregates.Cargo;
import com.streams.tracker.booking.domain.command.BookCargoCommand;
import com.streams.tracker.booking.domain.command.RouteCargoCommand;
import com.streams.tracker.booking.domain.valueobjects.CargoRoute;
import com.streams.tracker.booking.infrastructure.repositories.CargoRepository;
import com.streams.tracker.booking.internal.integration.ExternalCargoRoutingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
public class CargoBookingCommandService {
    private final CargoRepository cargoRepository;
    private final ExternalCargoRoutingService externalCargoRoutingService;

    public CargoBookingCommandService(CargoRepository cargoRepository, ExternalCargoRoutingService externalCargoRoutingService) {
        this.cargoRepository = cargoRepository;
        this.externalCargoRoutingService = externalCargoRoutingService;
    }

    public BookingId book(BookCargoCommand command) {
        String random = UUID.randomUUID().toString().toUpperCase();
        command.setBookingId(random.substring(0, random.indexOf("-")));
        Cargo cargo = new Cargo(command);
        cargoRepository.save(cargo);
        return new BookingId(command.getBookingId());
    }

    public void assignRouteToCargo(RouteCargoCommand command) {
        Cargo cargo = cargoRepository.findByBookingId(
                new BookingId(command.getBookingId()));
        CargoRoute cargoRoute = externalCargoRoutingService
                .fetchRouteForSpecification(cargo.getRouteSpecification());

        cargo.assignToRoute(cargoRoute);
        cargoRepository.save(cargo);
    }

}
