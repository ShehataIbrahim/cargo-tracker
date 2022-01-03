package com.streams.tracker.handling.domain.command;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class HandlingActivityRegistrationCommand {

    private Date completionTime;
    private String bookingId;
    private String voyageNumber;
    private String unLocode;
    private String handlingType;

    public HandlingActivityRegistrationCommand(String bookingId, String voyageNumber, String unLocode, String handlingType, Date completionTime) {
        this.setCompletionTime(completionTime);
        this.setBookingId(bookingId);
        this.setVoyageNumber(voyageNumber);
        this.setUnLocode(unLocode);
        this.setHandlingType(handlingType);
    }
}
