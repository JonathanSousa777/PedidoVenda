package com.algaworks.pedidoVenda.repository.filter;

import com.algaworks.pedidoVenda.validation.SKU;
import java.io.Serializable;

public class ProdutoFilter implements Serializable {

    private String sku;
    private String nome;

    @SKU
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.toUpperCase();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
