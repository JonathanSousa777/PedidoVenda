package com.algaworks.pedidoVenda.service;

import com.algaworks.pedidoVenda.model.Pedido;
import com.algaworks.pedidoVenda.model.StatusPedido;
import com.algaworks.pedidoVenda.repository.Pedidos;
import com.algaworks.pedidoVenda.util.jpa.Transactional;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Inject;

public class CadastroPedidoService implements Serializable {

    @Inject
    private Pedidos pedidos;

    @Transactional
    public Pedido salvar(Pedido pedido) {
        if(pedido.isNovo()) {
            pedido.setDataCriacao(new Date());
            pedido.setStatus(StatusPedido.ORCAMENTO);
        }

        pedido = this.pedidos.guardar(pedido);
        return pedido;
    }
}
