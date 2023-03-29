package com.musala.drones.advice;


import com.musala.drones.model.APIError;
import com.musala.drones.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
@RequiredArgsConstructor
@Log4j2
public class BaseControllerAdvice extends ResponseEntityExceptionHandler {

    private final MessageUtils messageUtils;

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<APIError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest servletRequest, HttpServletResponse response) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body(APIError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .statusCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(messageUtils.getMessage("type.mismatch"))
                .path(servletRequest.getRequestURI())
                .timestamp(LocalDateTime.now()).build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<APIError> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest servletRequest, HttpServletResponse response) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body(APIError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .statusCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(messageUtils.getMessage("invalid.request.format"))
                .path(servletRequest.getRequestURI())
                .timestamp(LocalDateTime.now()).build());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<APIError> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest servletRequest, HttpServletResponse response) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body(APIError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .statusCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(messageUtils.getMessage("invalid.method"))
                .path(servletRequest.getRequestURI())
                .timestamp(LocalDateTime.now()).build());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> handleAllExceptions(Exception exception, HttpServletRequest servletRequest, HttpServletResponse response) {
        exception.printStackTrace();
        return ResponseEntity.badRequest().body(APIError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(messageUtils.getMessage("internal.server.error"))
                .timestamp(LocalDateTime.now())
                .path(servletRequest.getRequestURI())
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Error in one of the fields: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(APIError.builder()
                .status(status.value())
                .statusCode(status.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .errors(ex.getBindingResult().getFieldErrors().stream()
                        .map(error -> error.getField() + " " + error.getDefaultMessage()).collect(Collectors.toList()))
                .build());
    }
}
