package com.streams.tracker.shared.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.util.SocketUtils;
import org.springframework.util.StringUtils;

@Log4j2
public class SpringBootUtil {

    public static void setRandomPort() {
        setRandomPort(1025, 65535);
    }

    public static void setRandomPort(int minPort, int maxPort) {
        try {
            String userDefinedPort = System.getProperty("server.port", System.getenv("SERVER_PORT"));
            if (!StringUtils.hasLength(userDefinedPort) || userDefinedPort.equals("0")) {
                int port = SocketUtils.findAvailableTcpPort(minPort, maxPort);
                System.setProperty("server.port", String.valueOf(port));
                int managementPort;
                do {
                    managementPort = SocketUtils.findAvailableTcpPort(minPort, maxPort);
                } while (managementPort == port);
                System.setProperty("management.server.port", String.valueOf(managementPort));
                log.info("Random Server Port is set to {} and management port is set to {}.", port, managementPort);
            }
        } catch (IllegalStateException e) {
            log.warn(String.format("No port available in range%d-%d. Default embedded server configuration will be used.", minPort, maxPort));
        }
    }
}
