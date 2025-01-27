package ru.format.demo.organizationservice.gw.input.rest.handler;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("handleIllegalArgumentException():", ex);
        return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<Map<String, String>> handleHibernateException(HibernateException ex) {
        log.error("handleHibernateException()", ex);
        return ResponseEntity.internalServerError().body(Map.of("message", ex.getMessage()));
    }
}
