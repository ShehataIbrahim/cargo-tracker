package com.streams.tracker.tracking.internal.integration;

import com.streams.tracker.shared.event.CargoHandledEvent;
import com.streams.tracker.tracking.infrastructure.mq.HandlingBinding;
import com.streams.tracker.tracking.internal.assembler.TrackingActivityCommandEventAssembler;
import com.streams.tracker.tracking.internal.command.AssignTrackingIdCommandService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(HandlingBinding.class)
public class CargoHandledEventHandler {

    private AssignTrackingIdCommandService assignTrackingIdCommandService;

    public CargoHandledEventHandler(AssignTrackingIdCommandService assignTrackingIdCommandService) {
        this.assignTrackingIdCommandService = assignTrackingIdCommandService;
    }

    @StreamListener(target = HandlingBinding.HANDLING)
    public void receiveEvent(CargoHandledEvent cargoHandledEvent) {
        assignTrackingIdCommandService.addTrackingEvent(
                TrackingActivityCommandEventAssembler
                        .toCommandFromEvent(cargoHandledEvent));

    }
}
