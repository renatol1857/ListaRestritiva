package com.renato.listrest.models.enums;

import java.util.Optional;

public enum FeaturePadraoEn {
	INCLUIR(1, "Incluir dados."), LISTAR_PAG(2, "Listar todos os padrões com paginação"),
	LISTAR_HISTORICO_PAG(3, "Listar um padrões com o seus históricos."), UPDATE_STATUS(4, "Alterar o Status do padrão"),
	UPDATE_OBS(5, "Alterar a descrição do padrão"), DELETE_BY_ID(6, "Apagar o padrão pelo ID e seus históricos");

	private int cod;
	private String msg;

	FeaturePadraoEn(int cod, String msg) {
		this.cod = cod;
		this.msg = msg;
	}

	public int getCod() {
		return cod;
	}

	public String getMsg() {
		return msg;
	}

	public static boolean isValid(Integer cod) {
		if (cod == null)
			return false;
		for (FeaturePadraoEn x : FeaturePadraoEn.values()) {
			if (x.getCod() == cod)
				return true;
		}
		return false;
	}

	public static Optional<FeaturePadraoEn> toEnum(Integer cod) {
		if (cod == null)
			return null;
		for (FeaturePadraoEn x : FeaturePadraoEn.values()) {
			if (x.getCod() == cod)
				return Optional.of(x);
		}
		return null;
	}

	public static Optional<String> toMsg(Integer cod) {
		if (cod == null)
			return null;
		for (FeaturePadraoEn x : FeaturePadraoEn.values()) {
			if (x.getCod() == cod)
				return Optional.of(x.getMsg());
		}
		return null;
	}
}
