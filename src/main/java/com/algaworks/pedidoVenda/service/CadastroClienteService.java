package com.algaworks.pedidoVenda.service;

import com.algaworks.pedidoVenda.model.Cliente;
import com.algaworks.pedidoVenda.repository.Clientes;
import com.algaworks.pedidoVenda.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

public class CadastroClienteService implements Serializable {

    @Inject
    private Clientes clientes;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        return clientes.guardar(cliente);
    }
}
