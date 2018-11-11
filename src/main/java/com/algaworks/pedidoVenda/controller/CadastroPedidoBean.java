package com.algaworks.pedidoVenda.controller;

import com.algaworks.pedidoVenda.model.Cliente;
import com.algaworks.pedidoVenda.model.EnderecoEntrega;
import com.algaworks.pedidoVenda.model.FormaPagamento;
import com.algaworks.pedidoVenda.model.ItemPedido;
import com.algaworks.pedidoVenda.model.Pedido;
import com.algaworks.pedidoVenda.model.Produto;
import com.algaworks.pedidoVenda.model.Usuario;
import com.algaworks.pedidoVenda.repository.Clientes;
import com.algaworks.pedidoVenda.repository.Produtos;
import com.algaworks.pedidoVenda.repository.Usuarios;
import com.algaworks.pedidoVenda.service.CadastroPedidoService;
import com.algaworks.pedidoVenda.util.jsf.FacesUtil;
import com.algaworks.pedidoVenda.validation.SKU;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

    @Inject
    private Usuarios usuarios;

    @Inject
    private Clientes clientes;

    @Inject
    private Produtos produtos;

    @Inject
    private CadastroPedidoService cadastroPedidoService;

    private String sku;
    private Pedido pedido;
    private Produto produtoLinhaEditavel;
    private List<Usuario> vendedores;
    private List<ItemPedido> listItens;

    public CadastroPedidoBean() {
        limpar();
    }

    public void inicializar() {
        this.pedido = new Pedido();
        this.pedido.setEnderecoEntrega(new EnderecoEntrega());
        this.pedido.adicionarItemVazio();
        if (FacesUtil.isNotPostback()) {
            this.vendedores = new ArrayList<>();
            this.vendedores = usuarios.vendedores();
        }
    }

    public List<Cliente> completarCliente(String nome) {
        return this.clientes.porNome(nome);
    }

    public List<Produto> completarProduto(String nome) {
        return this.produtos.porNome(nome);
    }

    public void carregarProdutoLinhaEditavel() {
        if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
            FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
        } else {
            ItemPedido item = this.pedido.getItens().get(0);
            if (produtoLinhaEditavel != null) {
                item.setProduto(produtoLinhaEditavel);
                item.setValorUnitario(produtoLinhaEditavel.getValorUnitario());
                item.setQuantidade(1);
                this.pedido.adicionarItemVazio();
                this.produtoLinhaEditavel = null;
                this.pedido.recalcularValorTotal();
                this.sku = null;
            }
        }
    }

    public void carregarProdutoPorSku() {
        if (StringUtils.isNotEmpty(this.sku)) {
            this.produtoLinhaEditavel = this.produtos.porSku(sku);
            this.carregarProdutoLinhaEditavel();
        }
    }

    private boolean existeItemComProduto(Produto produtoLinhaEditavel) {
        boolean existeItem = false;
        for (ItemPedido item : this.getPedido().getItens()) {
            if (item.getProduto().equals(produtoLinhaEditavel)) {
                existeItem = true;
                break;
            }
        }
        return existeItem;
    }

    public void atualizarQuantidade(ItemPedido item, int indexLinha) {
        if (item.getQuantidade() < 1) {
            if (indexLinha == 0) {
                item.setQuantidade(1);
            } else {
                this.getPedido().getItens().remove(indexLinha);
            }
        }
        this.pedido.recalcularValorTotal();
    }

    public void limpar() {
        pedido = new Pedido();
        pedido.setEnderecoEntrega(new EnderecoEntrega());
    }

    public FormaPagamento[] getFormasPagamento() {
        return FormaPagamento.values();
    }

    public void salvar() {
        this.pedido.removerItemVazio();
        try {
            this.pedido = cadastroPedidoService.salvar(pedido);
            FacesUtil.addMessage("Pedido salvo com sucesso.");
        } finally {
            this.pedido.adicionarItemVazio();
        }
    }

    public boolean isEditando() {
        return this.pedido.getId() != null;
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

    public List<ItemPedido> getListItens() {
        return listItens;
    }

    public void setListItens(List<ItemPedido> listItens) {
        this.listItens = listItens;
    }

    public Produto getProdutoLinhaEditavel() {
        return produtoLinhaEditavel;
    }

    public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
        this.produtoLinhaEditavel = produtoLinhaEditavel;
    }

    @SKU
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
