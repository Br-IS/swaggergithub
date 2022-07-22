package edu.ista.m5b.service;

import edu.ista.m5b.modelo.Lista;

import java.util.List;

public interface ListaService {


    public Lista save(Lista lista);

    public Lista findById(String nombre);

    public List<Lista> findAll();

    public void deleteById(String nombre);


}

