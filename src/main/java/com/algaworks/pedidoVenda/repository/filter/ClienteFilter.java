package com.algaworks.pedidoVenda.repository.filter;

import java.io.Serializable;

public class ClienteFilter implements Serializable {

    private String nome;
    private String documentoReceitaFederal;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumentoReceitaFederal() {
        return documentoReceitaFederal;
    }

    public void setDocumentoReceitaFederal(String documentoReceitaFederal) {
        this.documentoReceitaFederal = documentoReceitaFederal;
    }
}
