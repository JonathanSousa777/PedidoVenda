package com.algaworks.pedidoVenda.util.mail;

import com.outjected.email.api.MailMessage;
import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.MailMessageImpl;
import java.io.Serializable;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class Mailer implements Serializable {

    @Inject
    private SessionConfig config;

    public MailMessage novaMensagem() {
        return new MailMessageImpl(this.config);
    }
}
