package com.renato.listrest.errors;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private int httpCode;
    private int codMsg;
    private String status;
    private String message;
    private int marcador=0;
    private int rc=0;
    private String stackTrace;

    public ErrorResponse() {
        timestamp = new Date();
    }

    public ErrorResponse(HttpStatus httpStatus, int codMsg, String message ) {
        this();

        this.httpCode = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
        this.codMsg = codMsg;
    }

    public ErrorResponse( HttpStatus httpStatus, int codMsg, String message, String stackTrace ) {
        this( httpStatus, codMsg, message );
        this.stackTrace = stackTrace;
    }

}
