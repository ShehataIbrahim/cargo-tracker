package com.streams.tracker.booking.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteCargoCommand {
    private String cargoBookingId;
}
