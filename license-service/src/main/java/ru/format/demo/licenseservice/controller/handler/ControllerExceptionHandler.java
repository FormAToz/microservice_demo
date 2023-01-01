package ru.format.demo.licenseservice.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.format.demo.licenseservice.controller.dto.ErrorResponse;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("handleIllegalArgumentException():", ex);
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<ErrorResponse> handleHibernateException(HibernateException ex) {
        log.error("handleHibernateException()", ex);
        return ResponseEntity.internalServerError().body(new ErrorResponse(ex.getMessage()));
    }
}
