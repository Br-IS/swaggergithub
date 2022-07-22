package edu.ista.m5b.controller;


import edu.ista.m5b.modelo.Cancion;
import edu.ista.m5b.service.impl.CancionServiceImpl;
import edu.ista.m5b.service.impl.ListaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/canciones")
public class CancionController {

    private Cancion cancion;

    private List<Cancion> cancionList;

    @Autowired
    private CancionServiceImpl cancionServiceImpl;
    @Autowired
    private ListaServiceImpl listaServiceImpl;

    //READ BY ID
    @GetMapping("/{titulo}")
    public ResponseEntity<Cancion> readById(@PathVariable String titulo) {
        cancion = cancionServiceImpl.findById(titulo);
        if (!(cancion == null)) {
            return new ResponseEntity<>(cancion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //CREATE
    @PostMapping("/crear")
    public ResponseEntity<Cancion> create(@RequestBody Cancion cancion) {
        return new ResponseEntity<>(cancionServiceImpl.save(cancion), HttpStatus.OK);
    }

    //UPDATE
    @PutMapping("/{titulo}/{nombre}")
    public ResponseEntity<Cancion> update(@PathVariable String titulo,@PathVariable String nombre, @RequestBody Cancion cancione) {
        cancion = cancionServiceImpl.findById(titulo);
        if (!(cancion == null)) {
            try {
                cancion.setAlbum(cancione.getAlbum());
                cancion.setArtista(cancione.getArtista());
                cancion.setAnio(cancione.getAnio());
                cancion.setLista(listaServiceImpl.findById(nombre));
                return new ResponseEntity<>(cancionServiceImpl.save(cancion), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //READ
    @GetMapping("/list")
    public ResponseEntity<List<Cancion>> read() {
        return new ResponseEntity<>(cancionServiceImpl.findAll(), HttpStatus.OK);
    }



    //DELETE
    @DeleteMapping("/{titulo}")
    public ResponseEntity<Cancion> delete(@PathVariable String titulo) {
        cancion = cancionServiceImpl.findById(titulo);
        if (!(cancion == null)) {
            cancionServiceImpl.deleteById(titulo);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
