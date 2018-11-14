package com.algaworks.pedidoVenda.controller;

import com.algaworks.pedidoVenda.model.Pedido;

public class PedidoAlteradoEvent {

    private Pedido pedido;

    public PedidoAlteradoEvent(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

}
