package edu.ista.m5b.service;

import edu.ista.m5b.modelo.Cancion;

import java.util.List;

public interface CancionService {
    Cancion save(Cancion cancion);

    Cancion findById(String titulo);

    List<Cancion> findAll();

    void deleteById(String titulo);

}