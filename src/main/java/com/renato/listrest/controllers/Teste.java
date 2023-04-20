package com.renato.listrest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.listrest.exceptions.CustomErrorException;

import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
public class Teste {
	
	@GetMapping("/dniservice")
	public String dniService() {
		return "dniService";
	}
	
	@GetMapping("test-generic-exception")
	public ResponseEntity<Void> test4() {
	    throw new RuntimeException("Generic Exception");
	}
	
    @GetMapping("test-custom-error-exception")
    public ResponseEntity<Void> test3() {
        //throw new CustomErrorException( 2,HttpStatus.BAD_REQUEST, "Parameters not passed" );
    	throw new CustomErrorException(HttpStatus.BAD_REQUEST, "Parameters not passed" );
    }	


}
