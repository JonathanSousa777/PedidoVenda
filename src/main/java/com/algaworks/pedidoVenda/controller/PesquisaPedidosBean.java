package com.algaworks.pedidoVenda.controller;

import com.algaworks.pedidoVenda.model.Pedido;
import com.algaworks.pedidoVenda.model.StatusPedido;
import com.algaworks.pedidoVenda.repository.Pedidos;
import com.algaworks.pedidoVenda.repository.filter.PedidoFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import javax.inject.Named;

@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable {

    @Inject
    private Pedidos pedidos;

    private PedidoFilter filter;
    private List<Pedido> pedidosFiltrados;

    public PesquisaPedidosBean() {
        filter = new PedidoFilter();
        pedidosFiltrados = new ArrayList<>();
    }

    public void pesquisar() {
        pedidosFiltrados = pedidos.filtrados(filter);
    }

    public StatusPedido[] getStatusPedido() {
        return StatusPedido.values();
    }

    public List<Pedido> getPedidosFiltrados() {
        return pedidosFiltrados;
    }

    public void setPedidosFiltrados(List<Pedido> pedidosFiltrados) {
        this.pedidosFiltrados = pedidosFiltrados;
    }

    public PedidoFilter getFilter() {
        return filter;
    }

    public void setFilter(PedidoFilter filter) {
        this.filter = filter;
    }
}
