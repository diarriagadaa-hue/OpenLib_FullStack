//Diego andres Arriagada Aranguiz
package com.OpenLib.MultaMS.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.OpenLib.MultaMS.Model.MultaModel;
import com.OpenLib.MultaMS.Repository.MultaRepository;

@Service
public class MultaService {

    private static final Logger log = LoggerFactory.getLogger(MultaService.class);

    @Autowired
    private MultaRepository multaRepository;

    public List<MultaModel> mostrarMultas() {
        log.info("Obteniendo todas las multas");
        return multaRepository.findAll();
    }

    public Optional<MultaModel> buscarPorId(Long id) {
        log.info("Buscando multa con ID: {}", id);
        return multaRepository.findById(id);
    }

    public List<MultaModel> buscarPorRut(String rut) {
        log.info("Buscando multas del RUT: {}", rut);
        return multaRepository.findByRut(rut);
    }

    public MultaModel agregarMulta(MultaModel multa) {
        log.info("Agregando multa para usuario: {}", multa.getNombreUsuario());
        return multaRepository.save(multa);
    }

    public MultaModel modificarMulta(Long id, MultaModel multaModificada) {
        log.info("Modificando multa con ID: {}", id);
        MultaModel multaExistente = multaRepository.findById(id)
            .orElseThrow(() -> {
                log.error("Multa {} no encontrada", id);
                return new RuntimeException("Multa " + id + " no encontrada");
            });
        multaExistente.setNombreUsuario(multaModificada.getNombreUsuario());
        multaExistente.setRut(multaModificada.getRut());
        multaExistente.setPrestamo(multaModificada.getPrestamo());
        multaExistente.setMonto(multaModificada.getMonto());
        multaExistente.setFecha(multaModificada.getFecha());
        multaExistente.setMotivo(multaModificada.getMotivo());
        return multaRepository.save(multaExistente);
    }

    public void eliminarMulta(Long id) {
        log.info("Eliminando multa con ID: {}", id);
        multaRepository.deleteById(id);
    }
}