package edu.ista.m5b.repository;

import edu.ista.m5b.modelo.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionRepository extends JpaRepository<Cancion, String>{

   // Cancion findByTitulo(String titulo);



}