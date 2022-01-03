package com.streams.tracker.shared.event;

import com.streams.tracker.shared.event.data.CargoBookedEventData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoBookedEvent {
    private CargoBookedEventData cargoBookedEventData;
}
