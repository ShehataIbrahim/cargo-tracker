package com.streams.tracker.booking.controller;


import com.streams.tracker.booking.domain.dto.CargoDTO;
import com.streams.tracker.booking.internal.query.CargoQueryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookingController {
    private final CargoQueryService cargoQueryService;

    public BookingController(CargoQueryService cargoQueryService) {
        this.cargoQueryService = cargoQueryService;
    }

    @GetMapping("/findCargo")
    @ResponseBody
    public CargoDTO findByBookingId(@RequestParam("bookingId") String bookingId) {
        return cargoQueryService.find(bookingId);
    }
}
