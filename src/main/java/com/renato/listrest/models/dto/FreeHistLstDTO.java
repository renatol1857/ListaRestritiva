package com.renato.listrest.models.dto;

import com.renato.listrest.models.entities.FreeHist;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FreeHistLstDTO {
	private Long id;
	private String ip;
	private String dh;
	private String obs;

	public static FreeHistLstDTO transfonaEmDTO(FreeHist histFree) {
		return new FreeHistLstDTO(histFree.getId(), histFree.getIp(), histFree.getDh(), histFree.getObs());
	}

}
