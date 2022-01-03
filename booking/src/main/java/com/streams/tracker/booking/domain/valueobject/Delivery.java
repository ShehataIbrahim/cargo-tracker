package com.streams.tracker.booking.domain.valueobject;


import com.streams.tracker.booking.domain.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Delivery {

    public static final Date ETA_UNKNOWN = null;


    @Enumerated(EnumType.STRING)
    @Column(name = "routing_status")
    private RoutingStatus routingStatus;
    @Enumerated(EnumType.STRING)
    @Column(name = "transport_status")
    private TransportStatus transportStatus;
    @Column(name = "last_known_location_id")
    @AttributeOverride(name = "unLocCode", column = @Column(name = "last_known_location_id"))
    private Location lastKnownLocation;
    @Column(name = "current_voyage_number")
    @AttributeOverride(name = "voyageNumber", column = @Column(name = "current_voyage_number"))
    private Voyage currentVoyage;
    @Embedded
    private LastCargoHandledEvent lastEvent;

    public Delivery(LastCargoHandledEvent lastEvent, CargoRoute route,
                    RouteSpecification routeSpecification) {
        this.lastEvent = lastEvent;

        this.routingStatus = calculateRoutingStatus(route);
        this.transportStatus = calculateTransportStatus();
        this.lastKnownLocation = calculateLastKnownLocation();
        this.currentVoyage = calculateCurrentVoyage();
    }

    public Delivery updateOnRouting(RouteSpecification routeSpecification,
                                    CargoRoute route) {


        return new Delivery(this.lastEvent, route, routeSpecification);
    }

    public static Delivery derivedFrom(RouteSpecification routeSpecification,
                                       CargoRoute route, LastCargoHandledEvent lastCargoHandledEvent) {

        return new Delivery(lastCargoHandledEvent, route, routeSpecification);
    }

    private RoutingStatus calculateRoutingStatus(CargoRoute route) {
        if (route == null || route == CargoRoute.EMPTY_ROUTE) {
            return RoutingStatus.NOT_ROUTED;
        } else {
            return RoutingStatus.ROUTED;
        }
    }

    private TransportStatus calculateTransportStatus() {
        System.out.println("Transport Status for last event" + lastEvent.getHandlingEventType());
        if (lastEvent.getHandlingEventType() == null) {
            return TransportStatus.NOT_RECEIVED;
        }

        switch (lastEvent.getHandlingEventType()) {
            case "LOAD":
                return TransportStatus.ONBOARD_CARRIER;
            case "UNLOAD":
            case "RECEIVE":
            case "CUSTOMS":
                return TransportStatus.IN_PORT;
            case "CLAIM":
                return TransportStatus.CLAIMED;
            default:
                return TransportStatus.UNKNOWN;
        }
    }

    private Location calculateLastKnownLocation() {
        if (lastEvent != null) {
            return new Location(lastEvent.getHandlingEventLocation());
        } else {
            return null;
        }
    }

    private Voyage calculateCurrentVoyage() {
        if (getTransportStatus().equals(TransportStatus.ONBOARD_CARRIER) && lastEvent != null) {
            return new Voyage(lastEvent.getHandlingEventVoyage());
        } else {
            return null;
        }
    }
}
