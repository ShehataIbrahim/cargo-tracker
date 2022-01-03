package com.streams.tracker.booking.controller.request;

import lombok.Data;

import java.util.Date;

@Data
public class BookCargoRequest {
    private int bookingAmount;
    private String originLocation;
    private String destLocation;
    private Date destArrivalDeadline;
}
