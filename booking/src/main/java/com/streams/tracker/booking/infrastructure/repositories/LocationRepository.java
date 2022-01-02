package com.streams.tracker.booking.infrastructure.repositories;

import com.streams.tracker.booking.domain.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LocationRepository extends JpaRepository<Location, Void>, JpaSpecificationExecutor<Location> {

}