package com.renato.listrest.models.dto;

import java.io.Serializable;

import com.renato.listrest.models.entities.ListaRest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ListaRestDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String fullfone = "";
	private String ddi = "";
	private String ddd = "";
	private String fone = "";
	private String dh;
	private String dhup;
	
	public static ListaRestDTO transfonaEmDTO(ListaRest lstGeral) {
		return new ListaRestDTO(lstGeral.getId(), lstGeral.getFullfone(), lstGeral.getDdi(), lstGeral.getDdd(),
				lstGeral.getFone(), lstGeral.getDh(), lstGeral.getDhup());
	}
}