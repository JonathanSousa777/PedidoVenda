package com.algaworks.pedidoVenda.converter;

import com.algaworks.pedidoVenda.model.Grupo;
import com.algaworks.pedidoVenda.repository.Grupos;
import com.algaworks.pedidoVenda.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Grupo.class)
public class GrupoConverter implements Converter {

    private Grupos grupos;

    public GrupoConverter() {
        grupos = CDIServiceLocator.getBean(Grupos.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            return grupos.porId(new Long(value));
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Grupo grupo = (Grupo) value;
            return grupo.getId() == null ? null : grupo.getId().toString();
        }

        return "";
    }

}
