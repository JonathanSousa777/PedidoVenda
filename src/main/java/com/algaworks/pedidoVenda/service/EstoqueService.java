package com.algaworks.pedidoVenda.service;

import com.algaworks.pedidoVenda.model.ItemPedido;
import com.algaworks.pedidoVenda.model.Pedido;
import com.algaworks.pedidoVenda.repository.Pedidos;
import com.algaworks.pedidoVenda.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

public class EstoqueService implements Serializable {

    @Inject
    private Pedidos pedidos;

    @Transactional
    public void baixarItensEstoque(Pedido pedido) {
        pedido = this.pedidos.porId(pedido.getId());
        for (ItemPedido item : pedido.getItens()) {
            item.getProduto().baixarEstoque(item.getQuantidade());
        }
    }
}
