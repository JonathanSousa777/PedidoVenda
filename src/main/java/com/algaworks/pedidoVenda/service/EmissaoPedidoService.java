package com.algaworks.pedidoVenda.service;

import com.algaworks.pedidoVenda.model.Pedido;
import com.algaworks.pedidoVenda.model.StatusPedido;
import com.algaworks.pedidoVenda.repository.Pedidos;
import com.algaworks.pedidoVenda.util.jsf.FacesUtil;
import java.io.Serializable;
import javax.inject.Inject;

public class EmissaoPedidoService implements Serializable {

    @Inject
    private Pedidos pedidos;

    @Inject
    private CadastroPedidoService cadastroPedidoService;

    @Inject
    private EstoqueService estoqueService;

    public Pedido emitir(Pedido pedido) {
        pedido = cadastroPedidoService.salvar(pedido);

        if (pedido.isNaoEmissivel()) {
            FacesUtil.addMessage("Pedido não pode ser emitido com status " + pedido.getStatus().getDescicao() + ".");
            throw new NegocioException("Pedido não pode ser emitido com status " + pedido.getStatus().getDescicao() + ".");
        }

        this.estoqueService.baixarItensEstoque(pedido);
        pedido.setStatus(StatusPedido.EMITIDO);
        pedido = this.pedidos.guardar(pedido);
        return pedido;
    }
}
