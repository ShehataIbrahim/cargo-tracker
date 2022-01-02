package com.streams.tracker.booking.config;


import com.streams.tracker.booking.controller.response.BaseResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings("rawtypes")
@Log4j2
@RestControllerAdvice(basePackages = {"com.streams.tracker.booking"})
@RequestMapping(produces = "application/json")
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return body;
    }

    @RequestMapping(produces = "application/json")
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> assertionException(Throwable e) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(false);
        response.setErrorMessage(e.getMessage());
        if (e instanceof HttpClientErrorException) {
            return ResponseEntity.status(((HttpClientErrorException) e).getStatusCode()).body(response);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
