package com.algaworks.pedidoVenda.service;

import com.algaworks.pedidoVenda.model.Produto;
import com.algaworks.pedidoVenda.repository.Produtos;
import com.algaworks.pedidoVenda.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

public class CadastroProdutoService implements Serializable {

    @Inject
    private Produtos produtos;

    @Transactional
    public Produto salvar(Produto produto) {
        Produto produtoExistente = produtos.porSku(produto.getSku());

        if (produtoExistente != null && !produtoExistente.equals(produto)) {
            throw new NegocioException("Já existe um produto com SKU informado.");
        }

        return produtos.guardar(produto);
    }
}