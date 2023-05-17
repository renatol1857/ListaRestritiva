package com.renato.listrest.models.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.renato.listrest.models.entities.ListaFree;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaFreeHitoricoTDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String fullfone = "";
	private String dh;
	private String dhup;
	private List<HistFreeDTO> HistFreeDTO = new ArrayList<>();

	public static ListaFreeHitoricoTDO transfonaEmDTO(ListaFree lstFree) {
		return new ListaFreeHitoricoTDO(lstFree.getId(), lstFree.getFullfone(), lstFree.getDh(), lstFree.getDhup());
	}

	private ListaFreeHitoricoTDO(Long id, String fullPhone, String sDh, String sDhup) {
		this.id = id;
		this.fullfone = fullPhone;
		this.dh = sDh;
		this.dhup = sDhup;
	}

}
