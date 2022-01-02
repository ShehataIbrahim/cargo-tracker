package com.streams.tracker.booking.domain.event.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoRoutedEventData {
    private String bookingId;
}
