package com.algaworks.pedidoVenda.controller;

import com.algaworks.pedidoVenda.model.Pedido;
import com.algaworks.pedidoVenda.util.jsf.FacesUtil;
import com.algaworks.pedidoVenda.util.mail.Mailer;
import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.velocity.tools.generic.NumberTool;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {

    @Inject
    private Mailer mailer;

    @Inject
    @PedidoEdicao
    private Pedido pedido;

    public void enviarPedido() {
        String pathTemplateEmail = "E:\\SI\\Netbeans\\GitPedidoVendas\\PedidoVenda\\src\\main\\resources\\emails\\pedido.template";
        try {
            MailMessage message = mailer.novaMensagem();
            message.to(pedido.getCliente().getEmail())
                    .subject("pedido " + pedido.getId())
                    .put("pedido", this.pedido)
                    .put("numberTool", new NumberTool())
                    .put("locale", new Locale("pt", "BR"))
                    .bodyHtml(new VelocityTemplate(new File(pathTemplateEmail)))
                    .send();
        } catch (IOException ex) {
            FacesUtil.addErrorMessage("Houve um no envio de e-mail!");
            Logger.getLogger(EnvioPedidoEmailBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        FacesUtil.addMessage("Pedido enviado por e-mail com sucesso!");
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
