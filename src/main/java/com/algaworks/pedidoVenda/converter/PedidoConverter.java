package com.algaworks.pedidoVenda.converter;

import com.algaworks.pedidoVenda.model.Pedido;
import com.algaworks.pedidoVenda.repository.Pedidos;
import com.algaworks.pedidoVenda.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {

    Pedidos pedidos = CDIServiceLocator.getBean(Pedidos.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            return pedidos.porId(new Long(value));
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Pedido pedido = (Pedido) value;
            return pedido.getId() == null ? null : pedido.getId().toString();
        }

        return null;
    }

}
