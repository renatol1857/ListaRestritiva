package com.renato.listrest.models.dto;

import com.renato.listrest.models.entities.Padrao;
import com.renato.listrest.models.enums.PadroesEn;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PadraoDTO {

	private String alias = "";

	private PadroesEn tipoPadrao;

	private String extra = "";

	private String descricao = "";

	public static Padrao transfonaEmObj(PadraoDTO obj) {
		return new Padrao(obj.alias, obj.getTipoPadrao(), obj.getDescricao(), obj.getExtra());
	}

}
