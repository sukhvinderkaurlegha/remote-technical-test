package com.task.remotetechnicaltest.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {IOException.class, URISyntaxException.class})
	public ResponseEntity<String> handleIOException(Exception ex) {
		String message = ex.getMessage();
		log.warn("Sending [{}] because [{}]", HttpStatus.INTERNAL_SERVER_ERROR, message);
		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
