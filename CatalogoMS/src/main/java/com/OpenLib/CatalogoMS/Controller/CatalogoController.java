package com.OpenLib.CatalogoMS.Controller;

import com.OpenLib.CatalogoMS.Model.CatalogoModel;
import com.OpenLib.CatalogoMS.Service.CatalogoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogo")
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping
    public ResponseEntity<List<CatalogoModel>> mostrarLibros() {
        List<CatalogoModel> libros = catalogoService.mostrarLibros();
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogoModel> buscarId(@PathVariable Long id) {
        return catalogoService.buscarId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<CatalogoModel> buscarIsbn(@PathVariable String isbn) {
        return catalogoService.buscarIsbn(isbn)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CatalogoModel> agregarLibro(@Valid @RequestBody CatalogoModel libro) {
        CatalogoModel nuevo = catalogoService.agregarLibro(libro);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatalogoModel> modificarLibro(@PathVariable Long id, @Valid @RequestBody CatalogoModel libroModificado) {
        CatalogoModel modificado = catalogoService.modificarLibro(id, libroModificado);
        return ResponseEntity.ok(modificado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        catalogoService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }

}
