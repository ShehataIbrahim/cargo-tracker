package com.streams.tracker.booking.domain.view;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@ApiModel("Retrieve by query ")
public class CargoQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String bookingId;

    private String transportStatus;

    private String routingStatus;

    private String specOriginId;

    private String specDestinationId;

    private LocalDate specArrivalDeadline;

    private String originId;

    private Integer bookingAmount;

    private Integer handlingEventId;

    private String nextExpectedLocationId;

    private String nextExpectedHandlingEventType;

    private String nextExpectedVoyageId;

    private String lastKnownLocationId;

    private String currentVoyageNumber;

    private Integer lastHandlingEventId;

    private String lastHandlingEventType;

    private String lastHandlingEventLocation;

    private String lastHandlingEventVoyage;

}
