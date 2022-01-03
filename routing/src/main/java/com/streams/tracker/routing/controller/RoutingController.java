package com.streams.tracker.routing.controller;

import com.streams.tracker.routing.domain.aggregate.Voyage;
import com.streams.tracker.routing.domain.entity.CarrierMovement;
import com.streams.tracker.routing.domain.model.TransitEdge;
import com.streams.tracker.routing.domain.model.TransitPath;
import com.streams.tracker.routing.internal.query.RoutingQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class RoutingController {


    private final RoutingQueryService routingQueryService;

    public RoutingController(RoutingQueryService routingQueryService) {
        this.routingQueryService = routingQueryService;
    }

    //TODO enhance implementation by considering deadline value
    @GetMapping(path = "/optimalRoute")
    @ResponseBody
    public TransitPath findOptimalRoute(
            @RequestParam("origin") String originUnLocode,
            @RequestParam("destination") String destinationUnLocode,
            @RequestParam("deadline") String deadline) {

        List<Voyage> voyages = routingQueryService.findByArrivalAndDepartureLocations(originUnLocode, destinationUnLocode);
        TransitPath transitPath = new TransitPath();
        List<TransitEdge> transitEdges = new ArrayList<>();
        for (Voyage voyage : voyages) {

            TransitEdge transitEdge = new TransitEdge();
            transitEdge.setVoyageNumber(voyage.getVoyageNumber().getVoyageNumber());
            CarrierMovement movement =
                    voyage.getSchedule().getCarrierMovements().get(0);
            transitEdge.setFromDate(movement.getArrivalDate());
            transitEdge.setToDate(movement.getDepartureDate());
            transitEdge.setFromUnLoCode(movement.getArrivalLocation().getUnLocCode());
            transitEdge.setToUnLoCode(movement.getDepartureLocation().getUnLocCode());
            transitEdges.add(transitEdge);

        }

        transitPath.setTransitEdges(transitEdges);
        return transitPath;

    }
}
