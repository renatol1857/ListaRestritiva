package com.renato.listrest.models.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.renato.listrest.models.entities.Free;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreeHistoricoTDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String fullfone = "";
	private String dh;
	private String dhup;
	private List<HistFreeDTO> HistFreeDTO = new ArrayList<>();

	public static FreeHistoricoTDO transfonaEmDTO(Free lstFree) {
		return new FreeHistoricoTDO(lstFree.getId(), lstFree.getFullfone(), lstFree.getDh(), lstFree.getDhup());
	}

	private FreeHistoricoTDO(Long id, String fullPhone, String sDh, String sDhup) {
		this.id = id;
		this.fullfone = fullPhone;
		this.dh = sDh;
		this.dhup = sDhup;
	}

}
