package com.streams.tracker.tracking.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TrackingEventType {

    @Column(name = "event_type")
    private String eventType;
    @Column(name = "event_time")
    @Temporal(TemporalType.DATE)
    private Date eventTime;
}
