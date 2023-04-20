package com.renato.listrest.errors;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        return new ResponseEntity<> (new ErrorResponse(status, 1, e.getMessage(), stackTrace  ), status );
    }

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<ErrorResponse> handleCustomErrorExceptions( Exception e ) {
        // casting the generic Exception e to CustomErrorException
        CustomErrorException customErrorException = (CustomErrorException) e;

        HttpStatus status = customErrorException.getStatus();

        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        customErrorException.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();
        stackTrace = stackTrace.substring(0, 200);

        return new ResponseEntity<>( new ErrorResponse( status, 2,customErrorException.getMessage(), stackTrace), status );
    }
}
