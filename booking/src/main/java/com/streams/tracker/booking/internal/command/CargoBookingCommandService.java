package com.streams.tracker.booking.internal.command;

import com.streams.tracker.booking.infrastructure.repositories.CargoRepository;
import org.springframework.stereotype.Service;

@Service
public class CargoBookingCommandService {
    private final CargoRepository cargoRepository;

    public CargoBookingCommandService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

}
