package com.renato.listrest.models.dto;

import com.renato.listrest.models.entities.DNIS;
import com.renato.listrest.models.enums.StatusEn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class DNISPutDTO{
	private Long id;
	private String dnis;
	private String alias; 
	private String descricao;
	private StatusEn status;
	
	public DNIS transformaToObj() {
		return new DNIS(id, dnis, alias, descricao, status);
	}



}
