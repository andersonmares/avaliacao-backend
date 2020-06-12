package br.com.avaliacao.model;

import lombok.Getter;

import java.io.Serializable;

public enum TipoTelefone implements Serializable {
    RES ("Residencial"),
    COM ("Comercial"),
    CEL ("Celular");

    @Getter
    private String descricao;

    TipoTelefone(String descricao) {
        this.descricao = descricao;
    }

}
