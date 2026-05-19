//Diego andres Arriagada Aranguiz
package com.OpenLib.MultaMS.Controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.OpenLib.MultaMS.Model.MultaModel;
import com.OpenLib.MultaMS.Service.MultaService;

@RestController
@RequestMapping("/multas")
public class MultaController {

    @Autowired
    private MultaService multaService;

    @GetMapping
    public ResponseEntity<List<MultaModel>> mostrarTodasMultas() {
        return ResponseEntity.ok(multaService.mostrarMultas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MultaModel> buscarPorId(@PathVariable Long id) {
        return multaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<List<MultaModel>> buscarPorRut(@PathVariable String rut) {
        return ResponseEntity.ok(multaService.buscarPorRut(rut));
    }

    @PostMapping
    public ResponseEntity<MultaModel> agregarMulta(@Valid @RequestBody MultaModel multa) {
        return ResponseEntity.status(201).body(multaService.agregarMulta(multa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MultaModel> modificarMulta(@PathVariable Long id,
                                                      @Valid @RequestBody MultaModel multa) {
        return ResponseEntity.ok(multaService.modificarMulta(id, multa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMulta(@PathVariable Long id) {
        multaService.eliminarMulta(id);
        return ResponseEntity.noContent().build();
    }
}