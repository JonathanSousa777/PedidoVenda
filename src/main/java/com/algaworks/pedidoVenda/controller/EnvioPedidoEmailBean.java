package com.algaworks.pedidoVenda.controller;

import com.algaworks.pedidoVenda.model.Pedido;
import com.algaworks.pedidoVenda.util.jsf.FacesUtil;
import com.algaworks.pedidoVenda.util.mail.Mailer;
import com.outjected.email.api.MailMessage;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {

    @Inject
    private Mailer mailer;

    @Inject
    @PedidoEdicao
    private Pedido pedido;

    public void enviarPedido() {
        MailMessage message = mailer.novaMensagem();
        message.to(pedido.getCliente().getEmail())
                .subject("Pedido " + pedido.getId())
                .bodyHtml("<strong>Valor total</strong>: " + this.pedido.getValorTotal())
                .send();

        FacesUtil.addMessage("Pedido enviado por e-mail com sucesso!");
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
