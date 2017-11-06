package com.algaworks.pedidoVenda.controller;

import com.algaworks.pedidoVenda.model.Usuario;
import com.algaworks.pedidoVenda.repository.Usuarios;
import com.algaworks.pedidoVenda.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

    @Inject
    private Usuarios usuarios;

    private Usuario usuario;
    private String termoPesquisa;
    private List<Usuario> usuariosFiltrados;

    public List<Usuario> pesquisar() {
        usuariosFiltrados = usuarios.pesquisar(termoPesquisa);

        return usuariosFiltrados;
    }

    public void excluir() {
        this.usuarios.remover(this.usuario);
        usuariosFiltrados.remove(this.usuario);

        FacesUtil.addMessage("Usuário " + this.usuario.getNome() + " excluído com sucesso!");
    }

    public List<Usuario> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    public void setUsuariosFiltrados(List<Usuario> usuarioFiltrados) {
        this.usuariosFiltrados = usuarioFiltrados;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
