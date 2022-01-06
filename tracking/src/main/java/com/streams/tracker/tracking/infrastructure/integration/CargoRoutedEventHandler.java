package com.streams.tracker.tracking.infrastructure.integration;

import com.streams.tracker.shared.event.CargoRoutedEvent;
import com.streams.tracker.tracking.infrastructure.mq.RoutingBinding;
import com.streams.tracker.tracking.internal.assembler.TrackingDetailsCommandEventAssembler;
import com.streams.tracker.tracking.internal.command.AssignTrackingIdCommandService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(RoutingBinding.class)
public class CargoRoutedEventHandler {

    private final AssignTrackingIdCommandService assignTrackingIdCommandService;

    public CargoRoutedEventHandler(AssignTrackingIdCommandService assignTrackingIdCommandService) {
        this.assignTrackingIdCommandService = assignTrackingIdCommandService;
    }

    @StreamListener(target = RoutingBinding.ROUTING)
    public void receiveEvent(CargoRoutedEvent cargoRoutedEvent) {
        assignTrackingIdCommandService.assignTrackingNumberToCargo(
                TrackingDetailsCommandEventAssembler.toCommandFromEvent(cargoRoutedEvent));

    }
}
