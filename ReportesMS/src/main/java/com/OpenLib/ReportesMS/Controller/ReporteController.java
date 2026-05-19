package com.OpenLib.ReportesMS.Controller;

import com.OpenLib.ReportesMS.Model.ReporteModel;
import com.OpenLib.ReportesMS.Service.ReporteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<ReporteModel>> mostrarReportes() {
        List<ReporteModel> reportes = reporteService.mostrarReportes();
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteModel> buscarId(@PathVariable Long id) {
        return reporteService.buscarId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipoReporte}")
    public ResponseEntity<List<ReporteModel>> buscarPorTipo(@PathVariable String tipoReporte) {
        List<ReporteModel> reportes = reporteService.buscarPorTipo(tipoReporte);
        return ResponseEntity.ok(reportes);
    }

    // Genera reporte leyendo datos de CatalogoMS
    @PostMapping("/generar/catalogo")
    public ResponseEntity<ReporteModel> generarReporteCatalogo() {
        ReporteModel reporte = reporteService.generarReporteCatalogo();
        return ResponseEntity.status(201).body(reporte);
    }

    // Genera reporte leyendo datos de InventarioMS
    @PostMapping("/generar/inventario")
    public ResponseEntity<ReporteModel> generarReporteInventario() {
        ReporteModel reporte = reporteService.generarReporteInventario();
        return ResponseEntity.status(201).body(reporte);
    }

    @PostMapping
    public ResponseEntity<ReporteModel> agregarReporte(@Valid @RequestBody ReporteModel reporte) {
        ReporteModel nuevo = reporteService.agregarReporte(reporte);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteModel> modificarReporte(@PathVariable Long id, @Valid @RequestBody ReporteModel reporteModificado) {
        ReporteModel modificado = reporteService.modificarReporte(id, reporteModificado);
        return ResponseEntity.ok(modificado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id) {
        reporteService.eliminarReporte(id);
        return ResponseEntity.noContent().build();
    }

}
