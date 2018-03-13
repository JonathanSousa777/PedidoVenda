package com.algaworks.pedidoVenda.repository;

import com.algaworks.pedidoVenda.model.Usuario;
import com.algaworks.pedidoVenda.service.NegocioException;
import com.algaworks.pedidoVenda.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class Usuarios implements Serializable {

    @Inject
    private EntityManager manager;

    public Usuario guardar(Usuario usuario) {
        return manager.merge(usuario);
    }

    @Transactional
    public void remover(Usuario usuario) {
        try {
            usuario = porId(usuario.getId());
            manager.remove(usuario);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Usuário não pode ser excluído!");
        }
    }

    public List<Usuario> vendedores() {
        //TODO filtrar apenas vendedores
        return manager.createQuery("from Usuario", Usuario.class)
                .getResultList();
    }

    public Usuario porId(Long id) {
        return manager.find(Usuario.class, id);
    }

    public List<Usuario> pesquisar(String nome) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Usuario.class);

        criteria.add(Restrictions.ilike("nome", nome, MatchMode.START));

        return criteria.addOrder(Order.asc("nome")).list();
    }

    public Usuario porCpf(String cpf) {
        try {
            return manager.createQuery("from Usuario where cpf = :cpf", Usuario.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
