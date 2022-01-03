package com.streams.tracker.tracking.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AssignTrackingNumberCommand {
    private String bookingId;
    private String trackingNumber;
}
