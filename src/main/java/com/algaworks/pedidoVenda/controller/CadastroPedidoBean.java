package com.algaworks.pedidoVenda.controller;

import com.algaworks.pedidoVenda.model.Cliente;
import com.algaworks.pedidoVenda.model.EnderecoEntrega;
import com.algaworks.pedidoVenda.model.FormaPagamento;
import com.algaworks.pedidoVenda.model.Pedido;
import com.algaworks.pedidoVenda.model.Usuario;
import com.algaworks.pedidoVenda.repository.Clientes;
import com.algaworks.pedidoVenda.repository.Usuarios;
import com.algaworks.pedidoVenda.service.CadastroPedidoService;
import com.algaworks.pedidoVenda.service.NegocioException;
import com.algaworks.pedidoVenda.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

    @Inject
    private Usuarios usuarios;

    @Inject
    private Clientes clientes;

    @Inject
    private CadastroPedidoService cadastroPedidoService;

    private Pedido pedido;
    private List<Usuario> vendedores;

    public CadastroPedidoBean() {
        limpar();
    }

    public void inicializar() {
        if (FacesUtil.isNotPostback()) {
            this.vendedores = new ArrayList<>();
            this.vendedores = usuarios.vendedores();
        }
    }

    public List<Cliente> completarCliente(String nome) {
        return this.clientes.porNome(nome);
    }

    public void limpar() {
        pedido = new Pedido();
        pedido.setEnderecoEntrega(new EnderecoEntrega());
    }

    public FormaPagamento[] getFormasPagamento() {
        return FormaPagamento.values();
    }

    public void salvar() {
        this.pedido = cadastroPedidoService.salvar(pedido);
        FacesUtil.addMessage("Pedido salvo com sucesso.");
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Usuario> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Usuario> vendedores) {
        this.vendedores = vendedores;
    }

}
