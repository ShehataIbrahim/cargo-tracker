package com.streams.tracker.booking.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "leg")
public class Leg implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "load_time")
    private LocalDateTime loadTime;

    @Column(name = "unload_time")
    private LocalDateTime unloadTime;

    @Column(name = "load_location_id")
    private String loadLocationId;

    @Column(name = "unload_location_id")
    private String unloadLocationId;

    @Column(name = "voyage_number")
    private String voyageNumber;

    @Column(name = "cargo_id")
    private Integer cargoId;

}
