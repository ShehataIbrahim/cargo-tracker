package com.streams.tracker.booking.internal.assembler;

import com.streams.tracker.booking.controller.request.BookCargoRequest;
import com.streams.tracker.booking.domain.command.BookCargoCommand;
import org.springframework.beans.BeanUtils;

public class BookCargoCommandAssembler {
    public static BookCargoCommand toCommand(BookCargoRequest request) {
        BookCargoCommand bean = new BookCargoCommand();
        BeanUtils.copyProperties(request, bean, "bookingId");
        return bean;
    }
}
