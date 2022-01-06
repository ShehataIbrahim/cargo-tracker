package com.streams.tracker.booking.infrastructure.integration;

import com.streams.tracker.booking.domain.entity.Location;
import com.streams.tracker.booking.domain.model.TransitEdge;
import com.streams.tracker.booking.domain.model.TransitPath;
import com.streams.tracker.booking.domain.valueobject.CargoRoute;
import com.streams.tracker.booking.domain.valueobject.Leg;
import com.streams.tracker.booking.domain.valueobject.RouteSpecification;
import com.streams.tracker.booking.domain.valueobject.Voyage;
import com.streams.tracker.shared.exception.BaseBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ExternalCargoRoutingService {
    final private DiscoveryClient discovery;

    @Autowired
    RestTemplate restTemplate;

    public ExternalCargoRoutingService(DiscoveryClient discovery) {
        this.discovery = discovery;
    }

    public CargoRoute fetchRouteForSpecification(RouteSpecification routeSpecification) throws BaseBusinessException {
        Map<String, Object> params = new HashMap<>();
        params.put("origin", routeSpecification.getOrigin().getUnLocCode());
        params.put("destination", routeSpecification.getDestination().getUnLocCode());
        params.put("deadline", routeSpecification.getArrivalDeadline());
        List<ServiceInstance> routingService = discovery.getInstances("routing");

        ServiceInstance instance;
        switch (routingService.size()) {
            case 0:
                throw new BaseBusinessException("Routing service is down now");
            case 1:
                instance = routingService.get(0);
                break;
            default:
                Random rand = new Random();
                instance = routingService.get(rand.nextInt(routingService.size()));
        }
        TransitPath transitPath = restTemplate.getForObject(
                String.format("%s/routing/optimalRoute?origin={origin}&destination={destination}&deadline={deadline}", instance.getUri().toString()),
                TransitPath.class, params);

        if (transitPath != null && transitPath.getTransitEdges() != null) {
            List<Leg> legs = new ArrayList<>(transitPath.getTransitEdges().size());
            for (TransitEdge edge : transitPath.getTransitEdges()) {
                legs.add(toLeg(edge));
            }

            return new CargoRoute(legs);
        } else
            return new CargoRoute();
    }

    private Leg toLeg(TransitEdge edge) {
        return new Leg(
                new Voyage(edge.getVoyageNumber()),
                new Location(edge.getFromUnLoCode()),
                new Location(edge.getToUnLoCode()),
                edge.getFromDate(),
                edge.getToDate());
    }
}
