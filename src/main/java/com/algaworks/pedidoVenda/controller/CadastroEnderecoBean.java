package com.algaworks.pedidoVenda.controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class CadastroEnderecoBean {

    public List<Integer> listaFiltrada;

    public CadastroEnderecoBean() {
        listaFiltrada = new ArrayList<>();

        listaFiltrada.add(1);
        listaFiltrada.add(1);
    }

    public List<Integer> getListaFiltrada() {
        return listaFiltrada;
    }
}
