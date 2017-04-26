package app.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import app.domain.exception.ElementNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ElementNotFoundException.class })
	ResponseEntity<String> handleNotFounds(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ IllegalArgumentException.class })
	ResponseEntity<String> handleBadRequests(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// @ExceptionHandler(IllegalTransitionException.class)
	// ResponseEntity<String> handleConflicts(Exception e) {
	// return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	// }

}
