package com.algaworks.pedidoVenda.controller;

import com.algaworks.pedidoVenda.model.Pedido;
import com.algaworks.pedidoVenda.service.EmissaoPedidoService;
import com.algaworks.pedidoVenda.util.jsf.FacesUtil;
import java.io.Serializable;
import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class EmissaoPedidoBean implements Serializable {

    @Inject
    private EmissaoPedidoService emissaoPedidoService;

    @Inject
    @PedidoEdicao
    private Pedido pedido;

    @Inject
    private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;

    public void emitirPedido() {
        this.pedido.removerItemVazio();
        try {
            this.pedido = this.emissaoPedidoService.emitir(this.pedido);
            this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));

            FacesUtil.addMessage("Pedido emitido com sucesso!");
        } finally {
            this.pedido.adicionarItemVazio();
        }
    }
}
