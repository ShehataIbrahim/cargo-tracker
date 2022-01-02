package com.streams.tracker.booking.controller;


import com.streams.tracker.booking.domain.aggregates.BookingId;
import com.streams.tracker.booking.domain.aggregates.Cargo;
import com.streams.tracker.booking.internal.mapper.BookCargoCommandMapper;
import com.streams.tracker.booking.controller.request.BookCargoRequest;
import com.streams.tracker.booking.internal.command.CargoBookingCommandService;
import com.streams.tracker.booking.internal.query.CargoQueryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/book")
public class BookingController {
    private final CargoQueryService cargoQueryService;

    private final CargoBookingCommandService cargoBookingCommandService;

    public BookingController(CargoQueryService cargoQueryService, CargoBookingCommandService cargoBookingCommandService) {
        this.cargoQueryService = cargoQueryService;
        this.cargoBookingCommandService = cargoBookingCommandService;
    }

    @GetMapping("/findCargo")
    @ResponseBody
    public Cargo findByBookingId(@RequestParam("bookingId") String bookingId) {
        return cargoQueryService.find(bookingId);
    }

    @PostMapping
    @ResponseBody
    public BookingId book(@RequestBody BookCargoRequest bookCargoRequest) {
        log.info("booking cargo > {}", bookCargoRequest);
        return cargoBookingCommandService.book(
                BookCargoCommandMapper.toCommand(bookCargoRequest));
    }
}
