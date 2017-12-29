package com.algaworks.pedidoVenda.controller;

import com.algaworks.pedidoVenda.model.Cliente;
import com.algaworks.pedidoVenda.model.Endereco;
import com.algaworks.pedidoVenda.model.TipoPessoa;
import com.algaworks.pedidoVenda.repository.Clientes;
import com.algaworks.pedidoVenda.service.CadastroClienteService;
import com.algaworks.pedidoVenda.util.jsf.FacesUtil;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

    @Inject
    private Cliente cliente;
    @Inject
    private Clientes clientes;

    private Endereco endereco;
    private boolean editandoEndereco;

    @Inject
    private CadastroClienteService cadastroClienteService;

    public CadastroClienteBean() {
        limpar();
    }

    public void limpar() {
        if (cliente == null) {
            cliente = new Cliente();
            this.cliente.setTipo(TipoPessoa.FISICA);
        }
    }

    public TipoPessoa[] getTiposPessoas() {
        return TipoPessoa.values();
    }

    public void salvar() {
        this.cadastroClienteService.salvar(cliente);
        limpar();

        FacesUtil.addMessage("Cliente salvo com sucesso!");
    }

    public void editarEndereco(Endereco endereco) {
        this.endereco = endereco;
        this.editandoEndereco = true;
    }

    public void excluirEndereco(Endereco endereco) {
        this.cliente.getEnderecos().remove(endereco);
    }

    public void novoEndereco() {
        this.endereco = new Endereco();
        this.endereco.setCliente(this.cliente);
        this.editandoEndereco = false;
    }

    public void confirmarEndereco() {
        if (!this.cliente.getEnderecos().contains(this.endereco)) {
            this.cliente.getEnderecos().add(this.endereco);
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public boolean isEditando() {
        return cliente != null && cliente.getId() == null;
    }

    public boolean isEditantoEndereco() {
        return endereco != null && this.editandoEndereco;
    }
}
