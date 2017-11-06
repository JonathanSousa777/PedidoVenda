package com.algaworks.pedidoVenda.controller;

import com.algaworks.pedidoVenda.model.EnderecoEntrega;
import com.algaworks.pedidoVenda.model.Pedido;
import com.algaworks.pedidoVenda.service.NegocioException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CadastroPedidoBean implements Serializable{

    private Pedido pedido;
    private List<Integer> itens;

    public CadastroPedidoBean() {
        pedido = new Pedido();
        pedido.setEnderecoEntrega(new EnderecoEntrega());
        itens = new ArrayList<>();
        itens.add(1);
    }
    
    public void salvar() {
        throw new NegocioException("Pedido não pode ser salvo.");
    }

    public List<Integer> getItens() {
        return itens;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
