package com.streams.tracker.tracking.internal.assembler;

import com.streams.tracker.shared.event.CargoRoutedEvent;
import com.streams.tracker.tracking.domain.command.AssignTrackingNumberCommand;

public class TrackingDetailsCommandEventAssembler {
    public static AssignTrackingNumberCommand toCommandFromEvent(CargoRoutedEvent cargoRoutedEvent) {
        return new AssignTrackingNumberCommand(
                cargoRoutedEvent.getCargoRoutedEventData().getBookingId(), "");
    }
}
