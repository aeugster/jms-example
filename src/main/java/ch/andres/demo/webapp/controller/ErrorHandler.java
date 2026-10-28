package ch.andres.demo.webapp.controller;

import ch.andres.demo.webapp.misc.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

	/**
	 * user did something wrong/wrong as expected -> info
	 */
	@ExceptionHandler(value = {ApplicationException.class})
	protected ResponseEntity<Object> handleApplication(ApplicationException ex, WebRequest request) {
		log.info(ex.getMessage(), ex);

		var headers = new HttpHeaders();
		headers.add("application-exception", "true");

		return handleExceptionInternal(ex, ex.getMessage(), headers,
				HttpStatus.BAD_REQUEST, request);
	}
}
