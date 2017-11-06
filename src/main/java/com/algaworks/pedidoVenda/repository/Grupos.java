package com.algaworks.pedidoVenda.repository;

import com.algaworks.pedidoVenda.model.Grupo;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

public class Grupos implements Serializable {

    @Inject
    private EntityManager manager;

    public Grupo porId(Long id) {
        return manager.find(Grupo.class, id);
    }

    public List<Grupo> todos() {
        return manager.createQuery("from Grupo").getResultList();
    }

}
