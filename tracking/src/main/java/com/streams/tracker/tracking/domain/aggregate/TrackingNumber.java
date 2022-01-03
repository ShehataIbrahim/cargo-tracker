package com.streams.tracker.tracking.domain.aggregate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TrackingNumber {

    @Column(name = "tracking_number")
    private String trackingNumber;
}
