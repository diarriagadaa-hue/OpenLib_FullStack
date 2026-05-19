package com.OpenLib.CatalogoMS.Service;

import com.OpenLib.CatalogoMS.Model.CatalogoModel;
import com.OpenLib.CatalogoMS.Repository.CatalogoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogoService {

    private static final Logger logger = LoggerFactory.getLogger(CatalogoService.class);

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Value("${inventario.ms.url}")
    private String inventarioUrl;

    public List<CatalogoModel> mostrarLibros() {
        logger.info("Obteniendo todos los libros del catalogo");
        return catalogoRepository.findAll();
    }

    public Optional<CatalogoModel> buscarId(Long id) {
        logger.info("Buscando libro con id: {}", id);
        return catalogoRepository.findById(id);
    }

    public Optional<CatalogoModel> buscarIsbn(String isbn) {
        logger.info("Buscando libro con ISBN: {}", isbn);
        return catalogoRepository.findByIsbn(isbn);
    }

    public CatalogoModel agregarLibro(CatalogoModel libro) {
        logger.info("Agregando nuevo libro al catalogo: {}", libro.getTitulo());
        CatalogoModel guardado = catalogoRepository.save(libro);

        // Emitir al microservicio de Inventario para registrar el stock inicial
        try {
            WebClient webClient = WebClient.create(inventarioUrl);
            webClient.post()
                .uri("/inventario/registrar")
                .bodyValue(guardado)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(
                    resp -> logger.info("Inventario notificado para libro: {}", guardado.getTitulo()),
                    err -> logger.error("Error al notificar a Inventario: {}", err.getMessage())
                );
        } catch (Exception e) {
            logger.error("No se pudo conectar con InventarioMS: {}", e.getMessage());
        }

        return guardado;
    }

    public CatalogoModel modificarLibro(Long id, CatalogoModel libroModificado) {
        CatalogoModel libroExistente = catalogoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("El libro " + id + " no existe en el catalogo"));

        libroExistente.setIsbn(libroModificado.getIsbn());
        libroExistente.setTitulo(libroModificado.getTitulo());
        libroExistente.setAutor(libroModificado.getAutor());
        libroExistente.setGenero(libroModificado.getGenero());
        libroExistente.setAnioPublicacion(libroModificado.getAnioPublicacion());
        libroExistente.setStockDisponible(libroModificado.getStockDisponible());

        logger.info("Libro modificado: {}", libroExistente.getTitulo());
        return catalogoRepository.save(libroExistente);
    }

    public void eliminarLibro(Long id) {
        logger.info("Eliminando libro con id: {}", id);
        catalogoRepository.deleteById(id);
    }

}
