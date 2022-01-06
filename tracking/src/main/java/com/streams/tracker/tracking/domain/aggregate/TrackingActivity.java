package com.streams.tracker.tracking.domain.aggregate;

import com.streams.tracker.tracking.domain.command.AddTrackingEventCommand;
import com.streams.tracker.tracking.domain.command.AssignTrackingNumberCommand;
import com.streams.tracker.tracking.domain.entity.BookingId;
import com.streams.tracker.tracking.domain.entity.TrackingActivityEvent;
import com.streams.tracker.tracking.domain.valueobject.TrackingEvent;
import com.streams.tracker.tracking.domain.valueobject.TrackingEventType;
import com.streams.tracker.tracking.domain.valueobject.TrackingLocation;
import com.streams.tracker.tracking.domain.valueobject.TrackingVoyageNumber;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "tracking_activity")
public class TrackingActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private TrackingNumber trackingNumber;
    @Embedded
    private BookingId bookingId;
    @Embedded
    private TrackingActivityEvent trackingActivityEvent;

    public TrackingActivity(AssignTrackingNumberCommand assignTrackingNumberCommand) {
        this.trackingNumber = new TrackingNumber(assignTrackingNumberCommand.getTrackingNumber());
        this.bookingId = new BookingId((assignTrackingNumberCommand.getBookingId()));
        this.trackingActivityEvent = TrackingActivityEvent.EMPTY_ACTIVITY;
    }

    public void addTrackingEvent(AddTrackingEventCommand addTrackingEventCommand) {
        TrackingEvent trackingEvent = new TrackingEvent(
                new TrackingVoyageNumber(addTrackingEventCommand.getVoyageNumber()),
                new TrackingLocation(addTrackingEventCommand.getLocation()),
                new TrackingEventType(addTrackingEventCommand.getEventType(), addTrackingEventCommand.getEventTime()));
        this.trackingActivityEvent.getTrackingEvents().add(trackingEvent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TrackingActivity that = (TrackingActivity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
