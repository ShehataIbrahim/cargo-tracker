package com.streams.tracker.booking.domain.event;

import com.streams.tracker.booking.domain.event.data.CargoRoutedEventData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoRoutedEvent {
    private CargoRoutedEventData cargoRoutedEventData;
}
