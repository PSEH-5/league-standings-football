package com.publicissapient.service.exceptions;

import com.publicissapient.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class FootBallExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(FootBallException ex, WebRequest request) {

		ErrorMessage error = new ErrorMessage();
		error.setErrorcode(HttpStatus.NOT_FOUND.toString());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}
}
