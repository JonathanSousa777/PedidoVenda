package com.algaworks.pedidoVenda.converter;

import com.algaworks.pedidoVenda.model.Cliente;
import com.algaworks.pedidoVenda.repository.Clientes;
import com.algaworks.pedidoVenda.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

    private Clientes clientes;

    public ClienteConverter() {
        clientes = CDIServiceLocator.getBean(Clientes.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            return clientes.porId(new Long(value));
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Cliente cliente = (Cliente) value;
            return cliente.getId() == null ? null : cliente.getId().toString();
        }

        return "";
    }

}