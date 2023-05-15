package com.renato.listrest.models.dto;

import java.io.Serializable;

import com.renato.listrest.models.entities.ListaFree;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListaFreeTDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String fullfone = "";
	private String ddi = "";
	private String ddd = "";
	private String fone = "";
	private String dh;
	private String dhup;

	public static ListaFreeTDO transfonaEmDTO(ListaFree lstGeral) {
		return new ListaFreeTDO(lstGeral.getId(), lstGeral.getFullfone(), lstGeral.getDdi(), lstGeral.getDdd(),
				lstGeral.getFone(), lstGeral.getDh(), lstGeral.getDhup());
	}

}
