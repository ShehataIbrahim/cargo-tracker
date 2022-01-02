package com.streams.tracker.booking.controller.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookCargoRequest {
    private int bookingAmount;
    private String originLocation;
    private String destLocation;
    private LocalDate destArrivalDeadline;
}
