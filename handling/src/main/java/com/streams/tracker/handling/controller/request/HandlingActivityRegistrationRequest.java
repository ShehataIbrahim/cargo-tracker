package com.streams.tracker.handling.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HandlingActivityRegistrationRequest {

    private String bookingId;
    private String voyageNumber;
    private String unLocode;
    private String handlingType;
    private Date completionTime;

}
