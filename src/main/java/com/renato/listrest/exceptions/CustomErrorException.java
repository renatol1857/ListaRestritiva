package com.renato.listrest.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomErrorException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status = null;

    public CustomErrorException() {
        super();
    }

    public CustomErrorException( String message ) {
        super(message);
    }

    public CustomErrorException( HttpStatus status, String message) {
        this(message);
        this.status = status;
    }

}
