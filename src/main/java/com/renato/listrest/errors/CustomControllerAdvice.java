package com.renato.listrest.errors;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.renato.listrest.exceptions.CustomErrorException;

import lombok.NoArgsConstructor;

@ControllerAdvice
@NoArgsConstructor
public class CustomControllerAdvice {
    @ExceptionHandler(Exception.class) // exception handled
    public ResponseEntity<Object> handleExceptions(  Exception e ) {
        // ... potential custom logic

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();
        stackTrace = stackTrace.substring(0, 500);

        return new ResponseEntity<> (new ResponseGeralRL(status, 1, e.getMessage(), stackTrace  ), status );
    }

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<ResponseGeralRL> handleCustomErrorExceptions( Exception e ) {
        CustomErrorException customErrorException = (CustomErrorException) e;

        HttpStatus status = customErrorException.getStatus();
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        customErrorException.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();
        stackTrace = stackTrace.substring(0, 200);
        return new ResponseEntity<>( new ResponseGeralRL( status, 2,customErrorException.getMessage(), stackTrace), status );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseGeralRL> handleCustomValidationException( Exception e ) {
    	MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	
    	final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
 		StringBuilder errors = new StringBuilder();

 		for (FieldError fieldError : fieldErrors) {
 			errors
 				.append(fieldError.getField())
 				.append(": ")
 				.append(fieldError.getDefaultMessage());
 				//.append("\n");
    	}
 		return new ResponseEntity<>( new ResponseGeralRL( status, 2, errors.toString(), "Validation"), status );
    }

}
