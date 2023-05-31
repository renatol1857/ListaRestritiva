package com.renato.listrest.models.enums;

import java.util.Optional;

public enum FeatureRestritivaEn {
	INCLUIR_FULLPHONE(1, "Incluir utilizando o telefone completo 5511....."),
	INCLUIR(2, "Incluir utilizando o DDI/DDD/telefone."),
	CONSULTAR_FULLPHONE(3, "Consultar utilizando o telefone completo 5511....."),
	CONSULTAR_FULLPHONE_INC(4, "Consultar utilizando o telefone completo 5511..... com inc no histórico."),
	LISTAR(5, "Apresentar a lista cadastrada."),
	LISTAR_FULLPHONE(6, "Apresentar os dados do telefone completo 5511..... e seus históricos "),
	APAGAR_FULLPHONE(7, "Apagar utilizando o telefone completo 5511..... e seus histórico."),
	CARGA(8, "Popular a tabela utilizando um arquivo ASCII."), EXPORTAR(9, "Exportar os dados para um arquivo ASCII.");

	private int cod;
	private String msg;

	FeatureRestritivaEn(int cod, String msg) {
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
		for (FeatureRestritivaEn x : FeatureRestritivaEn.values()) {
			if (x.getCod() == cod)
				return true;
		}
		return false;
	}

	public static Optional<FeatureRestritivaEn> toEnum(Integer cod) {
		if (cod == null)
			return null;
		for (FeatureRestritivaEn x : FeatureRestritivaEn.values()) {
			if (x.getCod() == cod)
				return Optional.of(x);
		}
		return null;
	}

	public static Optional<String> toMsg(Integer cod) {
		if (cod == null)
			return null;
		for (FeatureRestritivaEn x : FeatureRestritivaEn.values()) {
			if (x.getCod() == cod)
				return Optional.of(x.getMsg());
		}
		return null;
	}

}
