package com.algaworks.pedidoVenda.model;

import java.io.Serializable;
import com.algaworks.pedidoVenda.model.validation.FisicaGroup;
import com.algaworks.pedidoVenda.model.validation.JuridicaGroup;

public enum TipoPessoa implements Serializable {
    FISICA("Física", "CPF", "999.999.999-99", FisicaGroup.class),
    JURIDICA("Jurídica", "CNPJ", "99.999.999/9999-99", JuridicaGroup.class);

    private String descricao;
    private String rotulo;
    private String mascara;
    private Class<?> grupoValidacao;

    private TipoPessoa(String descricao, String rotulo, String mascara, Class<?> grupoValidacao) {
        this.descricao = descricao;
        this.rotulo = rotulo;
        this.mascara = mascara;
        this.grupoValidacao = grupoValidacao;
    }

    public String getMascara() {
        return mascara;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getRotulo() {
        return rotulo;
    }

    public String getGrupoValidacao() {
        return grupoValidacao.getCanonicalName();
    }
}
