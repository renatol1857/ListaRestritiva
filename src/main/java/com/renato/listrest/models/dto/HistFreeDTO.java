package com.renato.listrest.models.dto;

import com.renato.listrest.models.entities.HistFree;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HistFreeDTO {
	private Long id;
	private String ip;
	private String dh;
	private String obs;

	public static HistFreeDTO transfonaEmDTO(HistFree histFree) {
		return new HistFreeDTO(histFree.getId(), histFree.getIp(), histFree.getDh(), histFree.getObs());
	}

}
