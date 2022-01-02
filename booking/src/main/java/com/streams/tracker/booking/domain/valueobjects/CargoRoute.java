package com.streams.tracker.booking.domain.valueobjects;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CargoRoute {

    @Transient
    public static final CargoRoute EMPTY_ROUTE = new CargoRoute();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cargo_id")
    private List<Leg> legs = Collections.emptyList();

    public List<Leg> getLegs() {
        return Collections.unmodifiableList(legs);
    }
}
