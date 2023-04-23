package com.renato.listrest.models.dto;

import java.util.Date;

import com.renato.listrest.models.entities.Phone;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class PhoneSalvarRespostaDTO {
	private Long id;
    private String dnis;
    private String fullfone;
    private String ddi;
    private String ddd;
    private String fone;
    private Date dh;

    public static PhoneSalvarRespostaDTO transfonaEmDTO(Phone phone) {
    	return new PhoneSalvarRespostaDTO(
    			phone.getId(),
    			phone.getDnis().getDnis(), 
    			phone.getFullfone(), 
    			phone.getDdi(), 
    			phone.getDdd(), 
    			phone.getFone(), 
    			phone.getDh());
	}
}
