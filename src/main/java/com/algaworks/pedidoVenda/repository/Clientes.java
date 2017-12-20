package com.algaworks.pedidoVenda.repository;

import com.algaworks.pedidoVenda.model.Cliente;
import com.algaworks.pedidoVenda.repository.filter.ClienteFilter;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class Clientes implements Serializable {

    @Inject
    private EntityManager manager;

    public Cliente guardar(Cliente cliente) {
        return manager.merge(cliente);
    }

    public Cliente porId(Long id) {
        return manager.find(Cliente.class, id);
    }

    public List<Cliente> flitrados(ClienteFilter clienteFilter) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Cliente.class);

        if (StringUtils.isNotBlank(clienteFilter.getNome())) {
            criteria.add(Restrictions.ilike("nome", clienteFilter.getNome(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotBlank(clienteFilter.getDocumentoReceitaFederal())) {
            criteria.add(Restrictions.ilike("documentoReceitaFederal", clienteFilter.getDocumentoReceitaFederal(), MatchMode.ANYWHERE));
        }

        return criteria.addOrder(Order.asc("nome")).list();
    }

}
