package com.streams.tracker.tracking.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TrackingVoyageNumber {
    @Column(name = "voyage_number")
    private String voyageNumber;
}
