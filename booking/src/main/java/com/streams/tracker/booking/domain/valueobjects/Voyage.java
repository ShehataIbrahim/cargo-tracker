package com.streams.tracker.booking.domain.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Voyage {

    @Column(name = "voyage_number")
    private String voyageNumber;

}
