package com.streams.tracker.handling;

import com.streams.tracker.handling.controller.HandlingController;
import com.streams.tracker.handling.controller.request.HandlingActivityRegistrationRequest;
import com.streams.tracker.handling.domain.agregate.HandlingActivity;
import com.streams.tracker.handling.domain.valueobject.Type;
import com.streams.tracker.handling.infrastructure.mq.EventSource;
import com.streams.tracker.handling.infrastructure.repository.HandlingActivityRepository;
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
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {HandlingController.class})
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@ComponentScan(basePackages = {"com.streams.tracker.handling"})
@RunWith(SpringRunner.class)
class HandlingControllerTest {

    @Autowired
    HandlingController controller;
    @Autowired
    EventSource eventSource;
    @Mock
    MessageChannel channel;
    @MockBean
    RestTemplate restTemplate;
    @Autowired
    HandlingActivityRepository handlingActivityRepository;
    @MockBean
    private DiscoveryClient discoveryClient;

    @Mock
    ServiceInstance serviceInstanceMock;

    @BeforeEach
    void setUp() throws URISyntaxException {
        when(eventSource.handling()).thenReturn(channel);
        when(channel.send(any())).thenReturn(true);
        //noinspection unchecked
        when(restTemplate.getForObject(anyString(), any(), any(Map.class))).thenReturn("Done");
        when(discoveryClient.getInstances(anyString())).thenReturn(Collections.singletonList(serviceInstanceMock));
        when(serviceInstanceMock.getUri()).thenReturn(new URI("localhost"));

    }

    @Test
    void registerHandlingActivityWithException() {
        HandlingActivityRegistrationRequest request = new HandlingActivityRegistrationRequest("5A75E242", "0001V", "PLKRK", "RECEIVE", new Date());
        assertThrows(IllegalArgumentException.class, () -> controller.registerHandlingActivity(request));

    }

    @Test
    void registerHandlingActivity() throws BaseBusinessException {
        HandlingActivityRegistrationRequest request = new HandlingActivityRegistrationRequest("5A75E242", "", "PLKRK", "RECEIVE", new Date());
        Boolean result = controller.registerHandlingActivity(request);
        assertTrue(result);
        verify(channel, times(1)).send(any());
        HandlingActivity savedValue = handlingActivityRepository.findByBookingId(request.getBookingId());
        assertNotNull(savedValue);
        assertEquals(Type.RECEIVE, savedValue.getType());
        assertEquals("PLKRK", savedValue.getLocation().getUnLocCode());
    }
}