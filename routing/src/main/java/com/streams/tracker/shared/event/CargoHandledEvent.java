package com.streams.tracker.shared.event;

import com.streams.tracker.shared.event.data.CargoHandledEventData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoHandledEvent {
    private CargoHandledEventData cargoHandledEventData;
}
