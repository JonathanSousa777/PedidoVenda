package com.algaworks.pedidoVenda.model;

public enum StatusPedido {

    ORCAMENTO("Orçamento"),
    EMITIDO("Emitido"),
    CANCELADO("Cancelado");

    private String descicao;

    private StatusPedido(String descicao) {
        this.descicao = descicao;
    }

    public String getDescicao() {
        return descicao;
    }

    public void setDescicao(String descicao) {
        this.descicao = descicao;
    }

}
