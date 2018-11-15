package com.algaworks.pedidoVenda.service;

import com.algaworks.pedidoVenda.model.Pedido;
import com.algaworks.pedidoVenda.model.StatusPedido;
import com.algaworks.pedidoVenda.repository.Pedidos;
import com.algaworks.pedidoVenda.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

public class CancelamentoPedidoService implements Serializable {

    @Inject
    private Pedidos pedidos;

    @Inject
    private EstoqueService estoqueService;

    @Transactional
    public Pedido cancelar(Pedido pedido) {
        pedido = this.pedidos.porId(pedido.getId());
        if (pedido.isNaoCancelavel()) {
            throw new NegocioException("Pedido não pode ser cancelado no status" + pedido.getStatus().getDescicao());
        }

        if (pedido.isEmitido()) {
            this.estoqueService.retornarItensEstoque(pedido);
        }
        pedido.setStatus(StatusPedido.CANCELADO);
        pedido = this.pedidos.guardar(pedido);
        return pedido;
    }
}
