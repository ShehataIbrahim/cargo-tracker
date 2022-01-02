package com.streams.tracker.booking.internal.mapper;

import com.streams.tracker.booking.controller.request.RouteCargoRequest;
import com.streams.tracker.booking.domain.command.RouteCargoCommand;
import org.springframework.beans.BeanUtils;

public class RouteCargoCommandMapper {
    public static RouteCargoCommand toCommand(RouteCargoRequest request)
    {
        RouteCargoCommand bean=new RouteCargoCommand();
        BeanUtils.copyProperties(request, bean);
        return bean;
    }
}
