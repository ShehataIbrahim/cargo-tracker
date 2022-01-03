package com.streams.tracker.tracking.controller;

import com.streams.tracker.tracking.domain.aggregate.TrackingActivity;
import com.streams.tracker.tracking.domain.entity.BookingId;
import com.streams.tracker.tracking.internal.query.TrackingQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TrackingController {
    private final TrackingQueryService trackingQueryService;

    public TrackingController(TrackingQueryService trackingQueryService) {
        this.trackingQueryService = trackingQueryService;
    }

    @GetMapping
    public TrackingActivity findTrackingActivity(@RequestParam("bookingId") String bookingId) {
        return trackingQueryService.findTrackingActivities(new BookingId(bookingId));
    }
}
