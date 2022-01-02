package com.streams.tracker.booking.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCargoCommand {
    private String bookingId;
    private int bookingAmount;
    private String originLocation;
    private String destLocation;
    private Date destArrivalDeadline;
}
