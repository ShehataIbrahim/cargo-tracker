package com.streams.tracker.routing.infrastructure.repository;


import com.streams.tracker.routing.domain.aggregate.Voyage;
import com.streams.tracker.routing.domain.valueobject.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoyageRepository extends JpaRepository<Voyage, Long> {
    List<Voyage> findBySchedule_CarrierMovements_ArrivalLocationAndSchedule_CarrierMovements_DepartureLocation(Location arrivalLocation, Location departureLocation);


}
