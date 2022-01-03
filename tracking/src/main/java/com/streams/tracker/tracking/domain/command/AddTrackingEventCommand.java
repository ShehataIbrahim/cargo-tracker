package com.streams.tracker.tracking.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTrackingEventCommand {
    private String bookingId;
    private Date eventTime;
    private String eventType;
    private String location;
    private String voyageNumber;

}
