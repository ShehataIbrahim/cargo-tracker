package com.streams.tracker.booking.controller;

import com.streams.tracker.booking.controller.request.RouteCargoRequest;
import com.streams.tracker.booking.domain.aggregate.BookingId;
import com.streams.tracker.booking.internal.assembler.RouteCargoCommandAssembler;
import com.streams.tracker.booking.internal.command.CargoBookingCommandService;
import com.streams.tracker.shared.exception.BaseBusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/route")
public class RoutingController {
    private final CargoBookingCommandService cargoBookingCommandService;

    public RoutingController(CargoBookingCommandService cargoBookingCommandService) {
        this.cargoBookingCommandService = cargoBookingCommandService;
    }

    @PostMapping
    @ResponseBody
    public BookingId routeCargo(@RequestBody RouteCargoRequest routeCargoRequest) throws BaseBusinessException {
        log.info("route cargo > {}", routeCargoRequest);
        cargoBookingCommandService.assignRouteToCargo(RouteCargoCommandAssembler.toCommand(routeCargoRequest));
        return new BookingId(routeCargoRequest.getBookingId());
    }
}
