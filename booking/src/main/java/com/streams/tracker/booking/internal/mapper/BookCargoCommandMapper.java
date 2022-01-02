package com.streams.tracker.booking.internal.mapper;

import com.streams.tracker.booking.controller.request.BookCargoRequest;
import com.streams.tracker.booking.domain.command.BookCargoCommand;
import org.springframework.beans.BeanUtils;

public class BookCargoCommandMapper {
    public static BookCargoCommand toCommand(BookCargoRequest request)
    {
        BookCargoCommand bean=new BookCargoCommand();
        BeanUtils.copyProperties(request, bean,"bookingId");
        return bean;
    }
}
