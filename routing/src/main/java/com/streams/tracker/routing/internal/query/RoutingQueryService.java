package com.streams.tracker.routing.internal.query;


import com.streams.tracker.routing.domain.aggregate.Voyage;
import com.streams.tracker.routing.domain.valueobject.Location;
import com.streams.tracker.routing.infrastructure.repository.VoyageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoutingQueryService {

    private final VoyageRepository voyageRepository;

    public RoutingQueryService(VoyageRepository voyageRepository) {
        this.voyageRepository = voyageRepository;
    }

    @Transactional
    public List<Voyage> findAll() {
        return voyageRepository.findAll();
    }

    @Transactional
    public List<Voyage> findByArrivalAndDepartureLocations(String arrival, String departure) {

        return voyageRepository.findBySchedule_CarrierMovements_ArrivalLocationAndSchedule_CarrierMovements_DepartureLocation(new Location(arrival), new Location(departure));
    }

}
