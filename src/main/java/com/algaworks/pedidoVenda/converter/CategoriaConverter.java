package com.algaworks.pedidoVenda.converter;

import com.algaworks.pedidoVenda.model.Categoria;
import com.algaworks.pedidoVenda.repository.Categorias;
import com.algaworks.pedidoVenda.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    private Categorias categorias;

    public CategoriaConverter() {
        categorias = CDIServiceLocator.getBean(Categorias.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Categoria retorno = null;

        if (value != null) {
            retorno = categorias.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Categoria) value).getId().toString();
        }
        return "";
    }
}
