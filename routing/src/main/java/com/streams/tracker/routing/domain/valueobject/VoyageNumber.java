package com.streams.tracker.routing.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class VoyageNumber {
    @Column(name = "voyage_number")
    private String voyageNumber;
}
