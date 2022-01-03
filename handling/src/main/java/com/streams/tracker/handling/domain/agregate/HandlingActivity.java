package com.streams.tracker.handling.domain.agregate;


import com.streams.tracker.handling.domain.valueobject.BookingId;
import com.streams.tracker.handling.domain.valueobject.Location;
import com.streams.tracker.handling.domain.valueobject.Type;
import com.streams.tracker.handling.domain.valueobject.VoyageNumber;
import com.streams.tracker.shared.event.CargoHandledEvent;
import com.streams.tracker.shared.event.data.CargoHandledEventData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@NamedQuery(name = "HandlingActivity.findByBookingId",
        query = "Select e from HandlingActivity e where e.bookingId.bookingId = :bookingId")
@Table(name = "handling_activity")
public class HandlingActivity extends AbstractAggregateRoot<HandlingActivity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private Type type;
    @Embedded
    private VoyageNumber voyageNumber;
    @Embedded
    private Location location;
    @Temporal(TemporalType.DATE)
    @Column(name = "event_completion_time")
    private Date completionTime;
    @Embedded
    private BookingId bookingId;

    public HandlingActivity(BookingId bookingId, Date completionTime,
                            Type type, Location location, VoyageNumber voyageNumber) {

        if (type.prohibitsVoyage()) {
            throw new IllegalArgumentException(
                    "VoyageNumber is not allowed with event type " + type);
        }

        this.voyageNumber = voyageNumber;
        this.completionTime = (Date) completionTime.clone();
        this.type = type;
        this.location = location;
        this.bookingId = bookingId;

        CargoHandledEvent cargoHandledEvent =
                new CargoHandledEvent(
                        new CargoHandledEventData(
                                this.bookingId.getBookingId(), this.type.toString(),
                                this.completionTime,
                                this.location.getUnLocCode(),
                                this.voyageNumber.getVoyageNumber()));
        addDomainEvent(cargoHandledEvent);
    }

    public HandlingActivity(BookingId bookingId, Date completionTime,
                            Type type, Location location) {

        if (type.requiresVoyage()) {
            throw new IllegalArgumentException(
                    "VoyageNumber is required for event type " + type);
        }

        this.completionTime = (Date) completionTime.clone();
        this.type = type;
        this.location = location;
        this.bookingId = bookingId;
        this.voyageNumber = null;

        CargoHandledEvent cargoHandledEvent =
                new CargoHandledEvent(
                        new CargoHandledEventData(
                                this.bookingId.getBookingId(),
                                this.type.toString(),
                                this.completionTime,
                                this.location.getUnLocCode(),
                                ""));
        addDomainEvent(cargoHandledEvent);
    }

    public void addDomainEvent(Object event) {
        registerEvent(event);
    }
}
