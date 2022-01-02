package com.streams.tracker.booking.domain.valueobjects;

import com.streams.tracker.booking.domain.entities.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CargoHandlingActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "next_expected_handling_event_type")
    private String type;
    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "next_expected_location_id"))
    private Location location;
    @Column(name = "next_expected_voyage_id")
    private Voyage voyage;


}
