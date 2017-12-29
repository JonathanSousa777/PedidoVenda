package com.algaworks.pedidoVenda.controller;

import com.algaworks.pedidoVenda.model.Cliente;
import com.algaworks.pedidoVenda.model.TipoPessoa;
import com.algaworks.pedidoVenda.repository.Clientes;
import com.algaworks.pedidoVenda.repository.filter.ClienteFilter;
import com.algaworks.pedidoVenda.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Jonathan Sousa
 */
@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable {

    @Inject
    private Clientes clientes;

    private Cliente clienteSelecionado;
    private ClienteFilter clienteFilter;
    private List<Cliente> clientesFiltrados;

    public PesquisaClienteBean() {
        clienteFilter = new ClienteFilter();
    }

    public void pesquisar() {
        clientesFiltrados = clientes.flitrados(clienteFilter);
    }

    public void excluir() {
        clientes.remover(clienteSelecionado);
        clientesFiltrados.remove(clienteSelecionado);

        FacesUtil.addMessage("Cliente " + clienteSelecionado.getNome() + " excluído com sucesso!");
    }

    public ClienteFilter getClienteFilter() {
        return clienteFilter;
    }

    public TipoPessoa[] getTiposPessoas() {
        return TipoPessoa.values();
    }

    public void setClienteFilter(ClienteFilter clienteFilter) {
        this.clienteFilter = clienteFilter;
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }

    public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
        this.clientesFiltrados = clientesFiltrados;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }
}
