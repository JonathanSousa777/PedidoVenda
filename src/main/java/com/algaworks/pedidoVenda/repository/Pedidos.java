package com.algaworks.pedidoVenda.repository;

import com.algaworks.pedidoVenda.model.Pedido;
import com.algaworks.pedidoVenda.repository.filter.PedidoFilter;
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

public class Pedidos implements Serializable {

    @Inject
    private EntityManager manager;
    
    public Pedido guardar(Pedido pedido) {
        return manager.merge(pedido);
    }
    
    public Pedido porId(Long id) {
        return manager.find(Pedido.class, id);
    }

    public List<Pedido> filtrados(PedidoFilter filter) {
        Session session = manager.unwrap(Session.class);

        Criteria criteria = session.createCriteria(Pedido.class)
                .createAlias("cliente", "c")
                .createAlias("vendedor", "v");

        if (filter.getNumeroDe() != null) {
            //ge = greater or equals
            criteria.add(Restrictions.ge("id", filter.getNumeroDe()));
        }

        if (filter.getNumeroAte() != null) {
            //le = lower or equals
            criteria.add(Restrictions.le("id", filter.getNumeroAte()));
        }

        if (filter.getDataCriacaoDe() != null) {
            criteria.add(Restrictions.ge("dataCriacao", filter.getDataCriacaoDe()));
        }

        if (filter.getDataCriacaoAte() != null) {
            criteria.add(Restrictions.le("dataCriacao", filter.getDataCriacaoAte()));
        }

        if (StringUtils.isNoneBlank(filter.getNomeCliente())) {
            criteria.add(Restrictions.ilike("c.nome", filter.getNomeCliente(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotBlank(filter.getNomeVendedor())) {
            criteria.add(Restrictions.ilike("v.nome", filter.getNomeVendedor(), MatchMode.ANYWHERE));
        }

        if (filter.getStatus() != null && filter.getStatus().length > 0) {
            //In = tem que ser um dos status selecionados
            criteria.add(Restrictions.in("status", filter.getStatus()));
        }

        return criteria.addOrder(Order.asc("id")).list();
    }
}
