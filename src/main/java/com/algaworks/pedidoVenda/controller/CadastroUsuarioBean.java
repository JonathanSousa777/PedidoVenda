package com.algaworks.pedidoVenda.controller;

import com.algaworks.pedidoVenda.model.Grupo;
import com.algaworks.pedidoVenda.model.Usuario;
import com.algaworks.pedidoVenda.repository.Grupos;
import com.algaworks.pedidoVenda.repository.Usuarios;
import com.algaworks.pedidoVenda.service.CadastroUsuarioService;
import com.algaworks.pedidoVenda.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

    @Inject
    private Usuarios usuarios;

    @Inject
    private Grupos grupos;

    @Inject
    private CadastroUsuarioService cadastroUsuarioService;

    private Usuario usuario;
    private Grupo grupoSelecionado;

    private List<Grupo> gruposUsuario;
    private List<Grupo> listaGrupos;

    public CadastroUsuarioBean() {
        limpar();
    }

    public void inicializar() {
        listaGrupos = grupos.todos();
    }

    public void limpar() {
        usuario = new Usuario();
        gruposUsuario = new ArrayList<>();
        grupoSelecionado = new Grupo();
    }

    public void salvar() {
        this.usuario.setGrupos(gruposUsuario);
        cadastroUsuarioService.salvar(usuario);
        limpar();

        FacesUtil.addMessage("Usuário salvo com sucesso!");
    }

    public void adicionarGrupo() {
        this.gruposUsuario.add(grupoSelecionado);
        this.grupoSelecionado = new Grupo();
    }

    public void removerGrupo() {
        this.gruposUsuario.remove(grupoSelecionado);
        grupoSelecionado = new Grupo();
    }

    public boolean isEditando() {
        return this.usuario.getId() != null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

        if (this.usuario != null) {
            this.gruposUsuario = this.usuario.getGrupos();
        }
    }

    public List<Grupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<Grupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public List<Grupo> getGruposUsuario() {
        return gruposUsuario;
    }

    public void setGruposUsuario(List<Grupo> gruposUsuario) {
        this.gruposUsuario = gruposUsuario;
    }

    public Grupo getGrupoSelecionado() {
        return grupoSelecionado;
    }

    public void setGrupoSelecionado(Grupo grupoSelecionado) {
        this.grupoSelecionado = grupoSelecionado;
    }
}
