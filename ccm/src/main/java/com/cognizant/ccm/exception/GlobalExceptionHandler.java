package com.cognizant.ccm.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * Global Exception handler class to handle all the Exceptions
 * @author Sujay
 *
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Handles Gateway Settings Parameters not found Exception
	 * @param Exception Class
	 * @return
	 */
	@ExceptionHandler(GatewaySettingsParametersNotFoundException.class)
	public ResponseEntity<String> gatewaySettingsNotFoundExceptionHandler(GatewaySettingsParametersNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<String> invalidTokenExceptionHandler(InvalidTokenException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		log.info("Start : handleMethodArguementNotValid()");

		// Map that contains the error details

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		// Get all validation errors

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		// Add errors to the response map

		body.put("errors", errors);
		log.info("End");
		return new ResponseEntity<>(body, headers, status);
	}
}
