package com.streams.tracker.tracking.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tracking_handling_events")
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private TrackingVoyageNumber trackingVoyageNumber;
    @Embedded
    private TrackingLocation trackingLocation;
    @Embedded
    private TrackingEventType trackingEventType;

    public TrackingEvent(
            TrackingVoyageNumber trackingVoyageNumber,
            TrackingLocation trackingLocation,
            TrackingEventType trackingEventType) {
        this.trackingEventType = trackingEventType;
        this.trackingVoyageNumber = trackingVoyageNumber;
        this.trackingLocation = trackingLocation;
    }

}
