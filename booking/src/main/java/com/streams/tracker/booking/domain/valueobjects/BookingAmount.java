package com.streams.tracker.booking.domain.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingAmount {

    @Column(name = "booking_amount", unique = true, updatable= false)
    private Integer bookingAmount;
}
