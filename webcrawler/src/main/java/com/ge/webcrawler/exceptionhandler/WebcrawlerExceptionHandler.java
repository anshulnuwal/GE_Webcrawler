package com.ge.webcrawler.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WebcrawlerExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseEntity<String> missingInputHandler(final MethodArgumentNotValidException e) {
	    return new ResponseEntity<>("Invalid Input Request -> Page List empty\nError Message : " + e.getMessage(), 
	    		HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<String> exceptionHandler(final Exception e) {
	    return new ResponseEntity<>("Exception Occured, Please Try Again.\nError Message : " + e.getMessage(), 
	    		HttpStatus.OK);
	}
}
