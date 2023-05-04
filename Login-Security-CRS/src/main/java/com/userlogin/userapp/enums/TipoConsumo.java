package com.userlogin.userapp.enums;

public enum TipoConsumo {

    PESSOA_FISICA(1,"Pessoa Física"),
    PESSOA_JURIDICA(2,"Pessoa Jurídica");

    private Integer cod;
    private  String desc;

    TipoConsumo(Integer cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }

    public static TipoConsumo toEnum(Integer cod){
        if(cod == null) return null;
        for(TipoConsumo tipo : TipoConsumo.values()){
            if(cod.equals(tipo.getCod())) return tipo;
        }
        throw new IllegalArgumentException("Id Inválido:"+cod);
    }
}


