package com.algaworks.pedidoVenda.converter;

import com.algaworks.pedidoVenda.model.Produto;
import com.algaworks.pedidoVenda.repository.Produtos;
import com.algaworks.pedidoVenda.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

    Produtos produtos = CDIServiceLocator.getBean(Produtos.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            return produtos.porId(new Long(value));
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Produto produto = (Produto) value;
            return produto.getId() == null ? null : produto.getId().toString();
        }

        return null;
    }

}
