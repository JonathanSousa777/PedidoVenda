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
        Cliente clienteExistente = clientes.porDocumentoReceitaFederal(cliente.getDocumentoReceitaFederal());

        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            throw new NegocioException("Já existe um cliente com o Documento informado.");
        }

        return clientes.guardar(cliente);
    }
}
