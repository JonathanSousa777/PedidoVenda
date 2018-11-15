package com.algaworks.pedidoVenda.controller;

import com.algaworks.pedidoVenda.model.Pedido;
import com.algaworks.pedidoVenda.service.CancelamentoPedidoService;
import com.algaworks.pedidoVenda.util.jsf.FacesUtil;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CancelamentoPedidoBean implements Serializable {

    @Inject
    private CancelamentoPedidoService cancelamentoPedidoService;

    @Inject
    private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;

    @Inject
    @PedidoEdicao
    private Pedido pedido;

    public void cancelarPedido() {
        this.pedido = this.cancelamentoPedidoService.cancelar(pedido);
        this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(pedido));
        FacesUtil.addMessage("Pedido cancelado com sucesso!");
    }
}
