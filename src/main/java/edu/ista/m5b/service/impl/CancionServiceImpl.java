package edu.ista.m5b.service.impl;

import edu.ista.m5b.modelo.Cancion;
import edu.ista.m5b.repository.CancionRepository;
import edu.ista.m5b.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancionServiceImpl implements CancionService {
    @Autowired
    private CancionRepository cancionRepository;

    @Override
    public Cancion save(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    @Override
    public Cancion findById(String titulo) {
        return cancionRepository.findById(titulo).orElse(new Cancion());
    }

    @Override
    public List<Cancion> findAll() {
        return cancionRepository.findAll();
    }

    @Override
    public void deleteById(String titulo) {
        cancionRepository.deleteById(titulo);
    }

}