package com.renato.listrest.models.dto;

import java.io.Serializable;

import com.renato.listrest.models.entities.ListaRestGeral;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ListaRestGeralDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String fullfone = "";
	private String ddi = "";
	private String ddd = "";
	private String fone = "";
	private String dh;
	private String dhup;
	
	public static ListaRestGeralDTO transfonaEmDTO(ListaRestGeral lstGeral) {
		return new ListaRestGeralDTO(lstGeral.getId(), lstGeral.getFullfone(), lstGeral.getDdi(), lstGeral.getDdd(),
				lstGeral.getFone(), lstGeral.getDh(), lstGeral.getDhup());
	}
}