package com.algaworks.pedidoVenda.controller;

import com.algaworks.pedidoVenda.model.Produto;
import com.algaworks.pedidoVenda.repository.Produtos;
import com.algaworks.pedidoVenda.repository.filter.ProdutoFilter;
import com.algaworks.pedidoVenda.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

    @Inject
    private Produtos produtos;

    private ProdutoFilter filter;
    private Produto produtoSelecionado;
    private List<Produto> produtosFiltrados;

    public PesquisaProdutosBean() {
        filter = new ProdutoFilter();
    }

    public void excluir() {
        produtos.remover(produtoSelecionado);
        produtosFiltrados.remove(produtoSelecionado);

        FacesUtil.addMessage("Produto " + produtoSelecionado.getSku() + " excluído com sucesso.");
    }

    public void pesquisar() {
        produtosFiltrados = produtos.filtrados(filter);
    }

    public List<Produto> getProdutosFiltrados() {
        return produtosFiltrados;
    }

    public ProdutoFilter getFilter() {
        return filter;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }
}
