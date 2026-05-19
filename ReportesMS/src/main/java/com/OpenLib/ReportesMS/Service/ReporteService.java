package com.OpenLib.ReportesMS.Service;

import com.OpenLib.ReportesMS.Model.ReporteModel;
import com.OpenLib.ReportesMS.Repository.ReporteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {

    private static final Logger logger = LoggerFactory.getLogger(ReporteService.class);

    @Autowired
    private ReporteRepository reporteRepository;

    @Value("${catalogo.ms.url}")
    private String catalogoUrl;

    @Value("${inventario.ms.url}")
    private String inventarioUrl;

    @Value("${notificaciones.ms.url}")
    private String notificacionesUrl;

    public List<ReporteModel> mostrarReportes() {
        logger.info("Obteniendo todos los reportes");
        return reporteRepository.findAll();
    }

    public Optional<ReporteModel> buscarId(Long id) {
        logger.info("Buscando reporte con id: {}", id);
        return reporteRepository.findById(id);
    }

    public List<ReporteModel> buscarPorTipo(String tipoReporte) {
        logger.info("Buscando reportes de tipo: {}", tipoReporte);
        return reporteRepository.findByTipoReporte(tipoReporte);
    }

    // Lee datos del CatalogoMS y genera un reporte
    public ReporteModel generarReporteCatalogo() {
        logger.info("Generando reporte desde CatalogoMS");
        String datos = "";
        try {
            WebClient webClient = WebClient.create(catalogoUrl);
            datos = webClient.get()
                .uri("/catalogo")
                .retrieve()
                .bodyToMono(String.class)
                .block();
            logger.info("Datos obtenidos de CatalogoMS correctamente");
        } catch (Exception e) {
            logger.error("Error al consultar CatalogoMS: {}", e.getMessage());
            datos = "No se pudieron obtener los datos del catalogo";
        }

        ReporteModel reporte = new ReporteModel();
        reporte.setTipoReporte("CATALOGO");
        reporte.setTitulo("Reporte de Catalogo de Libros");
        reporte.setContenido(datos);
        reporte.setOrigenMS("CatalogoMS");
        reporte.setEstado("GENERADO");

        return reporteRepository.save(reporte);
    }

    // Lee datos del InventarioMS y genera un reporte
    public ReporteModel generarReporteInventario() {
        logger.info("Generando reporte desde InventarioMS");
        String datos = "";
        try {
            WebClient webClient = WebClient.create(inventarioUrl);
            datos = webClient.get()
                .uri("/inventario")
                .retrieve()
                .bodyToMono(String.class)
                .block();
            logger.info("Datos obtenidos de InventarioMS correctamente");
        } catch (Exception e) {
            logger.error("Error al consultar InventarioMS: {}", e.getMessage());
            datos = "No se pudieron obtener los datos del inventario";
        }

        ReporteModel reporte = new ReporteModel();
        reporte.setTipoReporte("INVENTARIO");
        reporte.setTitulo("Reporte de Inventario");
        reporte.setContenido(datos);
        reporte.setOrigenMS("InventarioMS");
        reporte.setEstado("GENERADO");

        return reporteRepository.save(reporte);
    }

    public ReporteModel agregarReporte(ReporteModel reporte) {
        logger.info("Agregando reporte de tipo: {}", reporte.getTipoReporte());
        reporte.setEstado("GENERADO");
        return reporteRepository.save(reporte);
    }

    public ReporteModel modificarReporte(Long id, ReporteModel reporteModificado) {
        ReporteModel reporteExistente = reporteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("El reporte " + id + " no existe"));

        reporteExistente.setTipoReporte(reporteModificado.getTipoReporte());
        reporteExistente.setTitulo(reporteModificado.getTitulo());
        reporteExistente.setContenido(reporteModificado.getContenido());
        reporteExistente.setOrigenMS(reporteModificado.getOrigenMS());
        reporteExistente.setEstado(reporteModificado.getEstado());

        logger.info("Reporte modificado con id: {}", id);
        return reporteRepository.save(reporteExistente);
    }

    public void eliminarReporte(Long id) {
        logger.info("Eliminando reporte con id: {}", id);
        reporteRepository.deleteById(id);
    }

}
