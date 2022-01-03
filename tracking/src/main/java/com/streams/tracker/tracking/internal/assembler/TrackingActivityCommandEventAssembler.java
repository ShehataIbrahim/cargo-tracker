package com.streams.tracker.tracking.internal.assembler;

import com.streams.tracker.shared.event.CargoHandledEvent;
import com.streams.tracker.shared.event.data.CargoHandledEventData;
import com.streams.tracker.tracking.domain.command.AddTrackingEventCommand;

public class TrackingActivityCommandEventAssembler {
    public static AddTrackingEventCommand toCommandFromEvent(CargoHandledEvent cargoHandledEvent) {
        CargoHandledEventData eventData = cargoHandledEvent.getCargoHandledEventData();
        return new AddTrackingEventCommand(
                eventData.getBookingId(),
                eventData.getHandlingCompletionTime(),
                eventData.getHandlingType(),
                eventData.getHandlingLocation(),
                eventData.getVoyageNumber());
    }
}
