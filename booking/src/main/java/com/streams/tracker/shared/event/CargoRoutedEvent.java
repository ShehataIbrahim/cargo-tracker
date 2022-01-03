package com.streams.tracker.shared.event;

import com.streams.tracker.shared.event.data.CargoRoutedEventData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoRoutedEvent {
    private CargoRoutedEventData cargoRoutedEventData;
}
