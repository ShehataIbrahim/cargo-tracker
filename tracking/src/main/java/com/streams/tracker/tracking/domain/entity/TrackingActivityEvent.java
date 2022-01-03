package com.streams.tracker.tracking.domain.entity;


import com.streams.tracker.tracking.domain.valueobject.TrackingEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TrackingActivityEvent {

    @Transient
    public static final TrackingActivityEvent EMPTY_ACTIVITY = new TrackingActivityEvent();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tracking_id")
    private List<TrackingEvent> trackingEvents = new ArrayList<>();

}
