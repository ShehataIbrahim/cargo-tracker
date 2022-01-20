package com.streams.tracker.routing.internal.assembler;

import com.streams.tracker.routing.domain.aggregate.Voyage;
import com.streams.tracker.routing.domain.entity.CarrierMovement;
import com.streams.tracker.routing.domain.model.TransitEdge;
import com.streams.tracker.routing.domain.model.TransitPath;

import java.util.List;
import java.util.stream.Collectors;

public class TransitPathAssember {
    public static TransitPath toTransitPath(List<Voyage> voyages) {
        TransitPath transitPath = new TransitPath();
        transitPath.setTransitEdges(voyages.stream().map(TransitPathAssember::mapVoyageToTransitEdge).collect(Collectors.toList()));
        return transitPath;
    }

    private static TransitEdge mapVoyageToTransitEdge(Voyage voyage) {
        TransitEdge transitEdge = new TransitEdge();
        transitEdge.setVoyageNumber(voyage.getVoyageNumber().getVoyageNumber());
        CarrierMovement movement =
                voyage.getSchedule().getCarrierMovements().get(0);
        transitEdge.setFromDate(movement.getArrivalDate());
        transitEdge.setToDate(movement.getDepartureDate());
        transitEdge.setFromUnLoCode(movement.getArrivalLocation().getUnLocCode());
        transitEdge.setToUnLoCode(movement.getDepartureLocation().getUnLocCode());
        return transitEdge;
    }
}
