package com.renato.listrest.models.dto;

import java.io.Serializable;

import com.renato.listrest.models.entities.Restritiva;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RestritivaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String fullfone = "";
	private String ddi = "";
	private String ddd = "";
	private String fone = "";
	private String dh;
	private String dhup;
	
	public static RestritivaDTO transfonaEmDTO(Restritiva lstGeral) {
		return new RestritivaDTO(lstGeral.getId(), lstGeral.getFullfone(), lstGeral.getDdi(), lstGeral.getDdd(),
				lstGeral.getFone(), lstGeral.getDh(), lstGeral.getDhup());
	}
}