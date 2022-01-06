package com.streams.tracker.handling.infrastructure.integration;

import com.streams.tracker.handling.domain.valueobject.BookingId;
import com.streams.tracker.shared.exception.BaseBusinessException;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class ExternalTrackingService {
    final DiscoveryClient discovery;
    final RestTemplate restTemplate;

    public ExternalTrackingService(DiscoveryClient discovery, RestTemplate restTemplate) {
        this.discovery = discovery;
        this.restTemplate = restTemplate;
    }

    public boolean validateBookingTracking(BookingId bookingId) throws BaseBusinessException {
        Map<String, Object> params = new HashMap<>();
        params.put("bookingId", bookingId.getBookingId());
        List<ServiceInstance> routingService = discovery.getInstances("tracking");

        ServiceInstance instance;
        switch (routingService.size()) {
            case 0:
                throw new BaseBusinessException("Tracking service is down now");
            case 1:
                instance = routingService.get(0);
                break;
            default:
                Random rand = new Random();
                instance = routingService.get(rand.nextInt(routingService.size()));
        }
        try {
            if (instance == null || instance.getUri() == null)
                throw new BaseBusinessException("Tracking service running host can't be retrieved");
            restTemplate.getForObject(
                    String.format("%s/tracking/?bookingId={bookingId}", instance.getUri().toString()),
                    Object.class, params);
            return true;
        } catch (HttpStatusCodeException e) {
            return false;
        }
    }
}
