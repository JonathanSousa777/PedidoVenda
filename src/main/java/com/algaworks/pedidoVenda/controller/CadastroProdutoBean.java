package com.algaworks.pedidoVenda.controller;

import com.algaworks.pedidoVenda.model.Categoria;
import com.algaworks.pedidoVenda.model.Produto;
import com.algaworks.pedidoVenda.repository.Categorias;
import com.algaworks.pedidoVenda.service.CadastroProdutoService;
import com.algaworks.pedidoVenda.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

    @Inject
    private Categorias categorias;

    @Inject
    private CadastroProdutoService cadastroProdutoService;

    private Categoria categoriaPai;
    private Produto produto;

    private List<Categoria> categoriasRaizes;
    private List<Categoria> subcategorias;

    public CadastroProdutoBean() {
        limpar();
    }

    public void inicializar() {
        categoriasRaizes = categorias.raizes();

        if (categoriaPai != null) {
            carregarSubcategorias();
        }
    }

    public void carregarSubcategorias() {
        subcategorias = categorias.subcategoriasDe(categoriaPai);
    }

    public void salvar() {
        this.produto = cadastroProdutoService.salvar(this.produto);
        limpar();

        FacesUtil.addMessage("Produto salvo com sucesso!");
    }

    public boolean isEditando() {
        return produto.getId() != null;
    }

    public void limpar() {
        produto = new Produto();
        categoriaPai = null;
        subcategorias = new ArrayList<>();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;

        if (this.produto != null) {
            this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
        }
    }

    public List<Categoria> getCategoriasRaizes() {
        return categoriasRaizes;
    }

    @NotNull
    public Categoria getCategoriaPai() {
        return categoriaPai;
    }

    public void setCategoriaPai(Categoria categoriaPai) {
        this.categoriaPai = categoriaPai;
    }

    public List<Categoria> getSubcategorias() {
        return subcategorias;
    }
}
