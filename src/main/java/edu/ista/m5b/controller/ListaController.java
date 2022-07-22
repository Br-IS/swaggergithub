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

    private Lista lista;
    private List<Lista> listaList;
    private Cancion cancion;

    @Autowired
    private ListaServiceImpl listaServiceImpl;

    @Autowired
    private CancionServiceImpl cancionService;

    //READ BY ID
    @GetMapping("/{nombre}")
    public ResponseEntity<Lista> readById(@PathVariable String nombre) {
        lista = listaServiceImpl.findById(nombre);
        if (!(lista == null)) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //CREATE
    @PostMapping("/crear")
    public ResponseEntity<Lista> create(@RequestBody Lista lista) {
        listaList = listaServiceImpl.findAll();
        lista.setNombre(String.format("Lista %d", listaList.size() + 1));
        if(lista ==  null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
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
    public ResponseEntity<Lista> update(@PathVariable String nombre, @RequestBody Lista lista) {
        lista = listaServiceImpl.findById(nombre);
        if (!(lista == null)) {
            try {
                lista.setDescripcion(lista.getDescripcion());

                return new ResponseEntity<>(listaServiceImpl.save(lista), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //DELETE
    @DeleteMapping("/{nombre}")
    public ResponseEntity<Lista> delete(@PathVariable String nombre) {
        lista = listaServiceImpl.findById(nombre);
        if (!(lista == null)) {
/*
            lista.getCancion().forEach(cancion -> {
                cancion.setLista(listaServiceImpl.findById(nombre));
                Cancion cancion1 = new Cancion(cancion.getTitulo(), cancion.getArtista(), cancion.getAlbum(), cancion.getAnio(), cancion.getLista());
                cancionService.save(cancion1);

                //cancionService.delete(cancion.getId());
            });*/



            listaServiceImpl.deleteById(nombre);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}