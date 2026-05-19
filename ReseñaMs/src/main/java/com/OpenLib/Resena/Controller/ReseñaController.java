//Diego andres Arriagada Aranguiz
package com.OpenLib.Resena.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;
import com.OpenLib.Resena.Model.ReseñaModel;
import com.OpenLib.Resena.Service.ReseñaService;

@RestController
@RequestMapping("/reseñas")
public class ReseñaController {

    @Autowired
    private ReseñaService reseñaService;

    @GetMapping
    public ResponseEntity<List<ReseñaModel>> mostrarReseñas() {

        return ResponseEntity.ok(reseñaService.mostrarReseñas());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ReseñaModel> buscarId(@PathVariable Long id) {

        return reseñaService.buscarId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<ReseñaModel> agregarReseña(@Valid @RequestBody ReseñaModel reseña) {

        return ResponseEntity.status(201).body(reseñaService.agregarReseña(reseña));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ReseñaModel> modificarReseña(@PathVariable Long id, @Valid @RequestBody ReseñaModel reseñaModificada) {

        return ResponseEntity.ok(reseñaService.modificarReseña(id, reseñaModificada));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReseña(@PathVariable Long id) {

        reseñaService.eliminarReseña(id);
        return ResponseEntity.noContent().build();

    }

}
