package edu.ista.m5b.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cancion")
@Data @AllArgsConstructor
public class Cancion {
    @Id
    @Column(name = "titulo", nullable = false)
    private String titulo;
    private String artista;
    private String album;
    private String anio;

    @JsonIgnore
    @ManyToOne
    private Lista lista;

    public Cancion() {

    }
}
