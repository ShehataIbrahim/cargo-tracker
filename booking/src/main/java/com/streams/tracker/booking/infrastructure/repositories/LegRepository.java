package com.streams.tracker.booking.infrastructure.repositories;

import com.streams.tracker.booking.domain.model.Leg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LegRepository extends JpaRepository<Leg, Integer>, JpaSpecificationExecutor<Leg> {

}