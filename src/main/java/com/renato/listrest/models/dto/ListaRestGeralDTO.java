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

	public static ListaRestGeralDTO transfonaEmDTO(ListaRestGeral restGeral) {
		return new ListaRestGeralDTO(restGeral.getId(), restGeral.getFullfone(), restGeral.getDdi(), restGeral.getDdd(),
				restGeral.getFone(), restGeral.getDh());
	}
}