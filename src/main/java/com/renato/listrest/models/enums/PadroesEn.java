package com.renato.listrest.models.enums;

public enum PadroesEn {
	LENGTH_MIN(1),
	LENGTH_MAX(2),
	START_WITH(3),
	END_WITH(4),
	CONTENT(5),
	NOT_MOBILE(6),
	NOT_LANDLINE(7),
	NOT_DDI(8),
	NOT_DDD(9),
	NOT_SEQUENTIAL(10),
	NOT_ANONYMOUS(11),
	NOT_STRING(12);
	
	private int cod;
	
	PadroesEn(int cod){
		this.cod = cod;
		}
	
	public int getCod(){
		return cod;
		}
	
	public  static boolean isValid (Integer cod) {
		if (cod == null)
			return false;	
		for (PadroesEn x : PadroesEn.values()) {
			if (x.getCod() == cod)
				return true;
		}
		return false;
	}
}
