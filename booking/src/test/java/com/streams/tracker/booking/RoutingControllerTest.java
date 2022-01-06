package com.streams.tracker.booking;

import com.streams.tracker.booking.controller.BookingController;
import com.streams.tracker.booking.controller.RoutingController;
import com.streams.tracker.booking.controller.request.RouteCargoRequest;
import com.streams.tracker.booking.domain.aggregate.BookingId;
import com.streams.tracker.booking.domain.aggregate.Cargo;
import com.streams.tracker.booking.domain.model.TransitEdge;
import com.streams.tracker.booking.domain.model.TransitPath;
import com.streams.tracker.booking.domain.valueobject.RoutingStatus;
import com.streams.tracker.booking.domain.valueobject.TransportStatus;
import com.streams.tracker.shared.exception.BaseBusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {RoutingController.class})
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@ComponentScan(basePackages = {"com.streams.tracker.booking"})
@RunWith(SpringRunner.class)
class RoutingControllerTest {

    @Autowired
    RoutingController controller;
    @MockBean
    RestTemplate restTemplate;
    @MockBean
    private DiscoveryClient discoveryClient;
    @Autowired
    BookingController bookingController;

    @Mock
    ServiceInstance serviceInstanceMock;

    @BeforeEach
    void setUp() throws URISyntaxException {

        //noinspection unchecked
        when(restTemplate.getForObject(anyString(), any(), any(Map.class))).thenReturn(preparePath());
        when(discoveryClient.getInstances(anyString())).thenReturn(Collections.singletonList(serviceInstanceMock));
        when(serviceInstanceMock.getUri()).thenReturn(new URI("localhost"));

    }

    private TransitPath preparePath() {
        TransitPath path = new TransitPath();

        List<TransitEdge> edges = new ArrayList<>();
        edges.add(new TransitEdge("001v", "PLKRK", "PLWAW", new Date(), new Date()));
        path.setTransitEdges(edges);
        return path;
    }

    @Test
    void routeCargo() throws BaseBusinessException {
        RouteCargoRequest request = new RouteCargoRequest();
        request.setBookingId("6840966B");
        BookingId result = controller.routeCargo(request);
        Cargo cargo = bookingController.findByBookingId(result.getBookingId());
        assertNotNull(cargo);
        assertEquals(RoutingStatus.ROUTED, cargo.getDelivery().getRoutingStatus());
        assertEquals(TransportStatus.NOT_RECEIVED, cargo.getDelivery().getTransportStatus());
        assertEquals("001v", cargo.getCargoRoute().getLegs().get(0).getVoyage().getVoyageNumber());

    }
}