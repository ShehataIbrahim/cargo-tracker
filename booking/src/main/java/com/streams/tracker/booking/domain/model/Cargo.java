package com.streams.tracker.booking.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cargo")
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "booking_id", nullable = false)
    private String bookingId;

    @Column(name = "transport_status", nullable = false)
    private String transportStatus;

    @Column(name = "routing_status", nullable = false)
    private String routingStatus;

    @Column(name = "spec_origin_id")
    private String specOriginId;

    @Column(name = "spec_destination_id")
    private String specDestinationId;

    @Column(name = "spec_arrival_deadline")
    private LocalDate specArrivalDeadline;

    @Column(name = "origin_id")
    private String originId;

    @Column(name = "booking_amount", nullable = false)
    private Integer bookingAmount;

    @Column(name = "handling_event_id")
    private Integer handlingEventId;

    @Column(name = "next_expected_location_id")
    private String nextExpectedLocationId;

    @Column(name = "next_expected_handling_event_type")
    private String nextExpectedHandlingEventType;

    @Column(name = "next_expected_voyage_id")
    private String nextExpectedVoyageId;

    @Column(name = "last_known_location_id")
    private String lastKnownLocationId;

    @Column(name = "current_voyage_number")
    private String currentVoyageNumber;

    @Column(name = "last_handling_event_id")
    private Integer lastHandlingEventId;

    @Column(name = "last_handling_event_type")
    private String lastHandlingEventType;

    @Column(name = "last_handling_event_location")
    private String lastHandlingEventLocation;

    @Column(name = "last_handling_event_voyage")
    private String lastHandlingEventVoyage;

}
