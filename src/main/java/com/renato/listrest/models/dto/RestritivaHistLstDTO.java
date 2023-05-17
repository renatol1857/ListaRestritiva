package com.renato.listrest.models.dto;

import com.renato.listrest.models.entities.RestritivaHist;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestritivaHistLstDTO {
	private Long id;
	private String ip;
	private String dh;
	private String obs;

	public static RestritivaHistLstDTO transfonaEmDTO(RestritivaHist hist) {
		return new RestritivaHistLstDTO(hist.getId(), hist.getIp(), hist.getDh(), hist.getObs());
	}
}
