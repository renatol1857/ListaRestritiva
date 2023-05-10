package com.renato.listrest.models.dto;

import com.renato.listrest.models.entities.Phone;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class PhoneRespostaDTO {
	private Long id;
    private String dnis;
    private String fullfone;
    private String ddi;
    private String ddd;
    private String fone;
    private String dh;
    private String dhup;

    public static PhoneRespostaDTO transfonaEmDTO(Phone phone) {
    	return new PhoneRespostaDTO(
    			phone.getId(),
    			phone.getDnis().getDnis(), 
    			phone.getFullfone(), 
    			phone.getDdi(), 
    			phone.getDdd(), 
    			phone.getFone(), 
    			phone.getDh(),
    			phone.getDhup());
    	
	}
}
