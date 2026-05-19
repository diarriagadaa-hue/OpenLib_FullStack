//Diego andres Arriagada Aranguiz
package com.OpenLib.Resena.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.OpenLib.Resena.Model.ReseñaModel;
import com.OpenLib.Resena.Repository.ReseñaRepository;

@Service
public class ReseñaService {

    private static final Logger log = LoggerFactory.getLogger(ReseñaService.class);

    @Autowired
    private ReseñaRepository reseñaRepository;

    public List<ReseñaModel> mostrarReseñas() {
        log.info("Obteniendo todas las reseñas");
        return reseñaRepository.findAll();
    }

    public Optional<ReseñaModel> buscarId(Long id) {
        log.info("Buscando reseña con ID: {}", id);
        return reseñaRepository.findById(id);
    }

    public ReseñaModel agregarReseña(ReseñaModel reseña) {
        log.info("Agregando reseña de usuario: {}", reseña.getNombreUsuario());
        return reseñaRepository.save(reseña);
    }

    public ReseñaModel modificarReseña(Long id, ReseñaModel reseñaModificada) {
        log.info("Modificando reseña con ID: {}", id);
        ReseñaModel reseñaExistente = reseñaRepository.findById(id)
            .orElseThrow(() -> {
                log.error("Reseña {} no encontrada", id);
                return new RuntimeException("La reseña " + id + " no existe");
            });
        reseñaExistente.setNombreUsuario(reseñaModificada.getNombreUsuario());
        reseñaExistente.setLibro(reseñaModificada.getLibro());
        reseñaExistente.setCalificacion(reseñaModificada.getCalificacion());
        reseñaExistente.setComentario(reseñaModificada.getComentario());
        return reseñaRepository.save(reseñaExistente);
    }

    public void eliminarReseña(Long id) {
        log.info("Eliminando reseña con ID: {}", id);
        reseñaRepository.deleteById(id);
    }
}