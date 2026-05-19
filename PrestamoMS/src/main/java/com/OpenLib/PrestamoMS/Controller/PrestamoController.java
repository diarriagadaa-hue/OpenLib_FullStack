//Diego andres Arriagada Aranguiz
package com.OpenLib.PrestamoMS.Controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.OpenLib.PrestamoMS.Model.PrestamoModel;
import com.OpenLib.PrestamoMS.Service.PrestamoService;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    public ResponseEntity<List<PrestamoModel>> mostrarPrestamos() {
        return ResponseEntity.ok(prestamoService.mostrarPrestamos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoModel> buscarPorId(@PathVariable Long id) {
        return prestamoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<List<PrestamoModel>> buscarPorRut(@PathVariable String rut) {
        return ResponseEntity.ok(prestamoService.buscarPorRut(rut));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PrestamoModel>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(prestamoService.buscarPorEstado(estado));
    }

    @PostMapping
    public ResponseEntity<PrestamoModel> agregarPrestamo(@Valid @RequestBody PrestamoModel prestamo) {
        return ResponseEntity.status(201).body(prestamoService.agregarPrestamo(prestamo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestamoModel> modificarPrestamo(@PathVariable Long id,
                                                            @Valid @RequestBody PrestamoModel prestamo) {
        return ResponseEntity.ok(prestamoService.modificarPrestamo(id, prestamo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Long id) {
        prestamoService.eliminarPrestamo(id);
        return ResponseEntity.noContent().build();
    }
}