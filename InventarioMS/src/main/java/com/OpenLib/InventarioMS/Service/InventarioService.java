package com.OpenLib.InventarioMS.Service;

import com.OpenLib.InventarioMS.Model.InventarioModel;
import com.OpenLib.InventarioMS.Repository.InventarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InventarioService {

    private static final Logger logger = LoggerFactory.getLogger(InventarioService.class);

    @Autowired
    private InventarioRepository inventarioRepository;

    @Value("${catalogo.ms.url}")
    private String catalogoUrl;

    public List<InventarioModel> mostrarInventario() {
        logger.info("Obteniendo todo el inventario");
        return inventarioRepository.findAll();
    }

    public Optional<InventarioModel> buscarId(Long id) {
        logger.info("Buscando registro de inventario con id: {}", id);
        return inventarioRepository.findById(id);
    }

    public Optional<InventarioModel> buscarIsbn(String isbn) {
        logger.info("Buscando inventario del libro con ISBN: {}", isbn);
        return inventarioRepository.findByIsbnLibro(isbn);
    }

    // Endpoint llamado por CatalogoMS cuando agrega un libro nuevo
    public InventarioModel registrarDesideCatalogo(Map<String, Object> datosCatalogo) {
        logger.info("Recibiendo datos desde CatalogoMS para registrar stock inicial");

        String isbn = (String) datosCatalogo.get("isbn");
        String titulo = (String) datosCatalogo.get("titulo");
        Integer stock = (Integer) datosCatalogo.get("stockDisponible");

        // Si ya existe el ISBN no se duplica
        Optional<InventarioModel> existente = inventarioRepository.findByIsbnLibro(isbn);
        if (existente.isPresent()) {
            logger.warn("El libro con ISBN {} ya existe en el inventario", isbn);
            return existente.get();
        }

        InventarioModel nuevo = new InventarioModel();
        nuevo.setIsbnLibro(isbn);
        nuevo.setTituloLibro(titulo);
        nuevo.setCantidadDisponible(stock != null ? stock : 0);
        nuevo.setCantidadTotal(stock != null ? stock : 0);
        nuevo.setEstado(stock != null && stock > 0 ? "DISPONIBLE" : "AGOTADO");

        logger.info("Inventario registrado para libro: {}", titulo);
        return inventarioRepository.save(nuevo);
    }

    public InventarioModel agregarInventario(InventarioModel inventario) {
        // Consulta a CatalogoMS para verificar que el libro existe
        try {
            WebClient webClient = WebClient.create(catalogoUrl);
            webClient.get()
                .uri("/catalogo/isbn/" + inventario.getIsbnLibro())
                .retrieve()
                .bodyToMono(String.class)
                .block();
            logger.info("Libro verificado en CatalogoMS para ISBN: {}", inventario.getIsbnLibro());
        } catch (Exception e) {
            logger.error("No se pudo verificar el libro en CatalogoMS: {}", e.getMessage());
        }

        logger.info("Agregando registro de inventario para ISBN: {}", inventario.getIsbnLibro());
        return inventarioRepository.save(inventario);
    }

    public InventarioModel modificarInventario(Long id, InventarioModel inventarioModificado) {
        InventarioModel inventarioExistente = inventarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("El registro de inventario " + id + " no existe"));

        inventarioExistente.setIsbnLibro(inventarioModificado.getIsbnLibro());
        inventarioExistente.setTituloLibro(inventarioModificado.getTituloLibro());
        inventarioExistente.setCantidadDisponible(inventarioModificado.getCantidadDisponible());
        inventarioExistente.setCantidadTotal(inventarioModificado.getCantidadTotal());
        inventarioExistente.setEstado(inventarioModificado.getEstado());

        logger.info("Inventario modificado para libro: {}", inventarioExistente.getTituloLibro());
        return inventarioRepository.save(inventarioExistente);
    }

    public void eliminarInventario(Long id) {
        logger.info("Eliminando registro de inventario con id: {}", id);
        inventarioRepository.deleteById(id);
    }

}
