package com.streams.tracker.routing.controller;

import com.streams.tracker.routing.domain.model.TransitPath;
import com.streams.tracker.routing.internal.assembler.TransitPathAssember;
import com.streams.tracker.routing.internal.query.RoutingQueryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/optimalRoute")
public class RoutingController {


    private final RoutingQueryService routingQueryService;

    public RoutingController(RoutingQueryService routingQueryService) {
        this.routingQueryService = routingQueryService;
    }

    //TODO enhance implementation by considering deadline value
    @GetMapping
    @ResponseBody
    public TransitPath findOptimalRoute(
            @RequestParam("origin") String originUnLocode,
            @RequestParam("destination") String destinationUnLocode,
            @RequestParam("deadline") String deadline) {

        return TransitPathAssember.toTransitPath(routingQueryService.findByArrivalAndDepartureLocations(originUnLocode, destinationUnLocode));
    }
}
