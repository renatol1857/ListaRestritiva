package com.renato.listrest.models.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.renato.listrest.models.entities.Free;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreeHistDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String fullfone = "";
	private String dh;
	private String dhup;
	private List<FreeHistLstDTO> freeHistLstDTO = new ArrayList<>();

	public static FreeHistDTO transfonaEmDTO(Free lst) {
		return new FreeHistDTO(lst.getId(), lst.getFullfone(), lst.getDh(), lst.getDhup());
	}

	private FreeHistDTO(Long id, String fullPhone, String sDh, String sDhup) {
		this.id = id;
		this.fullfone = fullPhone;
		this.dh = sDh;
		this.dhup = sDhup;
	}

}
