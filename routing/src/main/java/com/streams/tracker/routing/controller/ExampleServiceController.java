package com.streams.tracker.routing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RestController
@RequestMapping(value = "/example")
public class ExampleServiceController {
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    public ExampleServiceController(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    @GetMapping()
    public String welcome() {
        StringBuilder outputBuilder=new StringBuilder();
        for (RequestMappingInfo out:
        requestMappingHandlerMapping.getHandlerMethods().keySet()) {
            outputBuilder.append(out.getDirectPaths()+"\n");

        }
        return outputBuilder.toString();
    }
    @GetMapping("/get")
    public String getData() {
        return super.toString();
    }
}
