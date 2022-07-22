package edu.ista.m5b.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lista")
@Data @AllArgsConstructor
public class Lista {
    @Id
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL)
    public List<Cancion> cancion;

    public Lista() {

    }
}
