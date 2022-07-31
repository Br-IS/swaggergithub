package edu.ista.m5b.controller;

import edu.ista.m5b.modelo.Cancion;
import edu.ista.m5b.modelo.Lista;
import edu.ista.m5b.service.impl.CancionServiceImpl;
import edu.ista.m5b.service.impl.ListaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listas")
public class ListaController {

    private Lista listaAcual;
    private List<Lista> listaList;
    private Cancion cancion;

    @Autowired
    private ListaServiceImpl listaServiceImpl;

    @Autowired
    private CancionServiceImpl cancionService;

    //READ BY ID
    @GetMapping("/{nombre}")
    public ResponseEntity<Lista> readById(@PathVariable String nombre) {
        listaAcual = listaServiceImpl.findById(nombre);
        if (!(listaAcual == null)) {
            return new ResponseEntity<>(listaAcual, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //CREATE
    @PostMapping("/crear")
    public ResponseEntity<Lista> create(@RequestBody Lista lista) {
        listaList = listaServiceImpl.findAll();
        lista.setNombre(String.format("Lista_%d", listaList.size() + 1));
        if (lista == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(listaServiceImpl.save(lista), HttpStatus.CREATED);
        }

    }

    //READ
    @GetMapping("/list")
    public ResponseEntity<List<Lista>> read() {
        return new ResponseEntity<>(listaServiceImpl.findAll(), HttpStatus.OK);
    }

    //UPDATE
    @PutMapping("/{nombre}")
    public ResponseEntity<Lista> update(@PathVariable String nombre, @RequestBody Lista lista1) {
        return getListaResponseEntity(nombre, lista1);
    }

    @PostMapping("/{nombre}")
    public ResponseEntity<Lista> updatePost(@PathVariable String nombre, @RequestBody Lista lista1) {
        return getListaResponseEntity(nombre, lista1);
    }

    private ResponseEntity<Lista> getListaResponseEntity(@PathVariable String nombre, @RequestBody Lista lista1) {
        listaAcual = listaServiceImpl.findById(nombre);
        if (listaAcual == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                listaAcual.setDescripcion(lista1.getDescripcion());
                return new ResponseEntity<>(listaServiceImpl.save(listaAcual), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


    //DELETE
    @DeleteMapping("/{nombre}")
    public ResponseEntity<Lista> delete(@PathVariable String nombre) {
        listaAcual = listaServiceImpl.findById(nombre);
        if (!(listaAcual == null)) {

            listaServiceImpl.deleteById(nombre);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}