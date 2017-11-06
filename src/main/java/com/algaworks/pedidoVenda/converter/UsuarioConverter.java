package com.algaworks.pedidoVenda.converter;

import com.algaworks.pedidoVenda.model.Usuario;
import com.algaworks.pedidoVenda.repository.Usuarios;
import com.algaworks.pedidoVenda.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

    private Usuarios usuarios;

    public UsuarioConverter() {
        usuarios = CDIServiceLocator.getBean(Usuarios.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            return usuarios.porId(new Long(value));
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Usuario usuario = (Usuario) value;
            return usuario.getId() == null ? null : usuario.getId().toString();
        }

        return "";
    }
}
