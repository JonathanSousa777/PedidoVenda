package com.algaworks.pedidoVenda.repository;

import com.algaworks.pedidoVenda.model.Cliente;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

public class Clientes implements Serializable {

    @Inject
    private EntityManager manager;

    public Cliente guardar(Cliente cliente) {
        return manager.merge(cliente);
    }

    public Cliente porId(Long id) {
        return manager.find(Cliente.class, id);
    }

}
