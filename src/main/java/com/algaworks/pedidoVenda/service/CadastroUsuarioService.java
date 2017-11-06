package com.algaworks.pedidoVenda.service;

import com.algaworks.pedidoVenda.model.Usuario;
import com.algaworks.pedidoVenda.repository.Usuarios;
import com.algaworks.pedidoVenda.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

public class CadastroUsuarioService implements Serializable {

    @Inject
    private Usuarios usuarios;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        Usuario usuarioExistente = usuarios.porCpf(usuario.getCpf());

        if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
            throw new NegocioException("Esse usuário já existe");
        }

        return usuarios.guardar(usuario);
    }
}
