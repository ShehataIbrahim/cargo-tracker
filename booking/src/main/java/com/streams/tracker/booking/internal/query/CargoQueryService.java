package com.streams.tracker.booking.internal.query;


import com.streams.tracker.booking.domain.aggregates.BookingId;
import com.streams.tracker.booking.domain.aggregates.Cargo;
import com.streams.tracker.booking.infrastructure.repositories.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CargoQueryService {

    private final CargoRepository cargoRepository;

    public CargoQueryService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public Cargo getById(Integer id) {
        return requireOne(id);
    }

    public Cargo find(String bookingId) {
        return cargoRepository.findByBookingId(new BookingId(bookingId));
    }

    private Cargo requireOne(Integer id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
