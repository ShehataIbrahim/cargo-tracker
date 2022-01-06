package com.streams.tracker.booking.domain.aggregate;

import com.streams.tracker.booking.domain.command.BookCargoCommand;
import com.streams.tracker.booking.domain.entity.Location;
import com.streams.tracker.booking.domain.valueobject.*;
import com.streams.tracker.shared.event.CargoBookedEvent;
import com.streams.tracker.shared.event.CargoRoutedEvent;
import com.streams.tracker.shared.event.data.CargoBookedEventData;
import com.streams.tracker.shared.event.data.CargoRoutedEventData;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Cargo extends AbstractAggregateRoot<Cargo> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private BookingId bookingId;
    @Embedded
    private BookingAmount bookingAmount;
    @Embedded
    private Location origin;
    @Embedded
    private RouteSpecification routeSpecification;
    @Embedded
    private CargoRoute cargoRoute;
    @Embedded
    private Delivery delivery;

    public Cargo(BookCargoCommand bookCargoCommand) {
        this.bookingId = new BookingId(bookCargoCommand.getBookingId());
        this.routeSpecification = new RouteSpecification(
                new Location(bookCargoCommand.getOriginLocation()),
                new Location(bookCargoCommand.getDestLocation()),
                bookCargoCommand.getDestArrivalDeadline()
        );
        this.origin = routeSpecification.getOrigin();
        this.cargoRoute = CargoRoute.EMPTY_ROUTE;
        this.bookingAmount = new BookingAmount(bookCargoCommand.getBookingAmount());
        this.delivery = Delivery.derivedFrom(this.routeSpecification,
                this.cargoRoute, LastCargoHandledEvent.EMPTY);

        addDomainEvent(new CargoBookedEvent(new CargoBookedEventData(this.bookingId.getBookingId())));
    }

    public void assignToRoute(CargoRoute cargoRoute) {
        this.cargoRoute = cargoRoute;
        this.delivery.setRoutingStatus(RoutingStatus.ROUTED);
        addDomainEvent(new CargoRoutedEvent(new CargoRoutedEventData(this.bookingId.getBookingId())));
    }

    public void addDomainEvent(Object event) {
        registerEvent(event);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cargo cargo = (Cargo) o;
        return id != null && Objects.equals(id, cargo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
