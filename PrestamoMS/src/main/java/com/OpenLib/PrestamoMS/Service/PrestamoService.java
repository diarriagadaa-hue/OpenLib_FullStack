//Diego andres Arriagada Aranguiz
package com.OpenLib.PrestamoMS.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.OpenLib.PrestamoMS.Model.PrestamoModel;
import com.OpenLib.PrestamoMS.Repository.PrestamoRepository;

@Service
public class PrestamoService {

    private static final Logger log = LoggerFactory.getLogger(PrestamoService.class);

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private WebClient webClient;

    // URL del microservicio de usuarios
    private static final String USUARIO_MS_URL = "http://localhost:8080/usuarios/rut/";

    public List<PrestamoModel> mostrarPrestamos() {
        log.info("Obteniendo todos los prestamos");
        return prestamoRepository.findAll();
    }

    public Optional<PrestamoModel> buscarPorId(Long id) {
        log.info("Buscando prestamo con ID: {}", id);
        return prestamoRepository.findById(id);
    }

    public List<PrestamoModel> buscarPorRut(String rut) {
        log.info("Buscando prestamos del RUT: {}", rut);
        return prestamoRepository.findByRutUsuario(rut);
    }

    public List<PrestamoModel> buscarPorEstado(String estado) {
        log.info("Buscando prestamos con estado: {}", estado);
        return prestamoRepository.findByEstado(estado);
    }

    public PrestamoModel agregarPrestamo(PrestamoModel prestamo) {
        log.info("Verificando usuario con RUT: {}", prestamo.getRutUsuario());

        // Verificar que el usuario existe en UsuarioMS
        try {
            webClient.get()
                .uri(USUARIO_MS_URL + prestamo.getRutUsuario())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
            log.info("Usuario verificado correctamente");
        } catch (Exception e) {
            log.error("Usuario con RUT {} no encontrado en UsuarioMS", prestamo.getRutUsuario());
            throw new RuntimeException("El usuario con RUT " + prestamo.getRutUsuario() + " no existe");
        }

        log.info("Agregando prestamo para usuario: {}", prestamo.getNombreUsuario());
        return prestamoRepository.save(prestamo);
    }

    public PrestamoModel modificarPrestamo(Long id, PrestamoModel prestamoModificado) {
        log.info("Modificando prestamo con ID: {}", id);
        PrestamoModel prestamoExistente = prestamoRepository.findById(id)
            .orElseThrow(() -> {
                log.error("Prestamo {} no encontrado", id);
                return new RuntimeException("Préstamo " + id + " no encontrado");
            });
        prestamoExistente.setNombreUsuario(prestamoModificado.getNombreUsuario());
        prestamoExistente.setRutUsuario(prestamoModificado.getRutUsuario());
        prestamoExistente.setLibro(prestamoModificado.getLibro());
        prestamoExistente.setFechaPrestamo(prestamoModificado.getFechaPrestamo());
        prestamoExistente.setFechaDevolucion(prestamoModificado.getFechaDevolucion());
        prestamoExistente.setEstado(prestamoModificado.getEstado());
        return prestamoRepository.save(prestamoExistente);
    }

    public void eliminarPrestamo(Long id) {
        log.info("Eliminando prestamo con ID: {}", id);
        prestamoRepository.deleteById(id);
    }
}