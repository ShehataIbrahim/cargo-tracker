package com.streams.tracker.booking.domain.view;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@ApiModel("Save ")
public class CargoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "bookingId can not null")
    private String bookingId;

    @NotNull(message = "transportStatus can not null")
    private String transportStatus;

    @NotNull(message = "routingStatus can not null")
    private String routingStatus;

    private String specOriginId;

    private String specDestinationId;

    private LocalDate specArrivalDeadline;

    private String originId;

    @NotNull(message = "bookingAmount can not null")
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
