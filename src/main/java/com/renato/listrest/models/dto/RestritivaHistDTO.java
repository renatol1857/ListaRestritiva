package com.renato.listrest.models.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.renato.listrest.models.entities.Restritiva;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestritivaHistDTO  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String fullfone = "";
	private String dh;
	private String dhup;
	private List<RestritivaHistLstDTO> restritivaHistListDTO = new ArrayList<>();

	public static RestritivaHistDTO transfonaEmDTO(Restritiva lst) {
		return new RestritivaHistDTO(lst.getId(), lst.getFullfone(), lst.getDh(), lst.getDhup());
	}

	private RestritivaHistDTO(Long id, String fullPhone, String sDh, String sDhup) {
		this.id = id;
		this.fullfone = fullPhone;
		this.dh = sDh;
		this.dhup = sDhup;
	}

}
