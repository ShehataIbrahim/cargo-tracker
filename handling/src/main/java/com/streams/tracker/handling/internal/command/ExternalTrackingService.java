package com.streams.tracker.handling.internal.command;

import com.streams.tracker.handling.domain.valueobject.BookingId;
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

    public boolean validateBookingTracking(BookingId bookingId) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookingId", bookingId.getBookingId());
        List<ServiceInstance> routingService = discovery.getInstances("tracking");

        ServiceInstance instance;
        switch (routingService.size()) {
            case 0:
                throw new RuntimeException("Tracking service is down now");
            case 1:
                instance = routingService.get(0);
                break;
            default:
                Random rand = new Random();
                instance = routingService.get(rand.nextInt(routingService.size()));
        }
        try {
            restTemplate.getForObject(
                    String.format("http://%s:%d/tracking/?bookingId={bookingId}", instance.getHost(), instance.getPort()),
                    Object.class, params);
            return true;
        } catch (HttpStatusCodeException e) {
            return false;
        }
    }
}
