package com.streams.tracker.booking.domain.valueobjects;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LastCargoHandledEvent {

    private Integer handlingEventId;
    @Transient
    private String handlingEventType;
    @Transient
    private String handlingEventVoyage;
    @Transient
    private String handlingEventLocation;
    @Transient
    public static final LastCargoHandledEvent EMPTY = new LastCargoHandledEvent();
}
