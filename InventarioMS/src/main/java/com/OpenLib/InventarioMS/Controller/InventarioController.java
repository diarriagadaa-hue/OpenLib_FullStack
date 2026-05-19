package com.OpenLib.InventarioMS.Controller;

import com.OpenLib.InventarioMS.Model.InventarioModel;
import com.OpenLib.InventarioMS.Service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<InventarioModel>> mostrarInventario() {
        List<InventarioModel> inventario = inventarioService.mostrarInventario();
        return ResponseEntity.ok(inventario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioModel> buscarId(@PathVariable Long id) {
        return inventarioService.buscarId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<InventarioModel> buscarIsbn(@PathVariable String isbn) {
        return inventarioService.buscarIsbn(isbn)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint que recibe datos desde CatalogoMS
    @PostMapping("/registrar")
    public ResponseEntity<InventarioModel> registrarDesideCatalogo(@RequestBody Map<String, Object> datosCatalogo) {
        InventarioModel registrado = inventarioService.registrarDesideCatalogo(datosCatalogo);
        return ResponseEntity.status(201).body(registrado);
    }

    @PostMapping
    public ResponseEntity<InventarioModel> agregarInventario(@Valid @RequestBody InventarioModel inventario) {
        InventarioModel nuevo = inventarioService.agregarInventario(inventario);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioModel> modificarInventario(@PathVariable Long id, @Valid @RequestBody InventarioModel inventarioModificado) {
        InventarioModel modificado = inventarioService.modificarInventario(id, inventarioModificado);
        return ResponseEntity.ok(modificado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable Long id) {
        inventarioService.eliminarInventario(id);
        return ResponseEntity.noContent().build();
    }

}
