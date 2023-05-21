package com.userlogin.userapp.enums;

public enum TipoConsumo {

	PERSONA_FISICA(1, "Persona Física"), PERSONA_JURIDICA(2, "Persona Jurídica");

	private Integer cod;
	private String desc;

	TipoConsumo(Integer cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public static TipoConsumo toEnum(Integer cod) {
		if (cod == null)
			return null;
		for (TipoConsumo tipo : TipoConsumo.values()) {
			if (cod.equals(tipo.getCod()))
				return tipo;
		}
		throw new IllegalArgumentException("Id Inválido:" + cod);
	}
}
