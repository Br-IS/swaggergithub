package edu.ista.m5b.service.impl;

import edu.ista.m5b.modelo.Lista;
import edu.ista.m5b.repository.ListaRepository;
import edu.ista.m5b.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaServiceImpl implements ListaService {


    @Autowired
private     ListaRepository listaRepository;

    @Override
    public Lista save(Lista lista) {
        return listaRepository.save(lista);
    }

    @Override
    public Lista findById(String nombre) {
        return listaRepository.findById(nombre).orElse(new Lista());
    }

    @Override
    public List<Lista> findAll() {
        return listaRepository.findAll();
    }

    @Override
    public void deleteById(String nombre) {
        listaRepository.deleteById(nombre);
    }




}