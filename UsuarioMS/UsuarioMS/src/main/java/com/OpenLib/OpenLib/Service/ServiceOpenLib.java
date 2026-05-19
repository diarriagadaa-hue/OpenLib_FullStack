//Diego andres Arriagada Aranguiz
package com.OpenLib.OpenLib.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.OpenLib.OpenLib.Model.UsuarioOpenLib;
import com.OpenLib.OpenLib.Repository.InterfaceRepositoryOpenLib;

@Service
public class ServiceOpenLib {

    private static final Logger log = LoggerFactory.getLogger(ServiceOpenLib.class);

    @Autowired
    private InterfaceRepositoryOpenLib usuarioOpenLibRepository;

    public List<UsuarioOpenLib> mostrarUsuarios() {
        log.info("Obteniendo todos los usuarios");
        return usuarioOpenLibRepository.findAll();
    }

    public Optional<UsuarioOpenLib> buscarId(Long id) {
        log.info("Buscando usuario con ID: {}", id);
        return usuarioOpenLibRepository.findById(id);
    }

    public Optional<UsuarioOpenLib> buscarRut(String rut) {
        log.info("Buscando usuario con RUT: {}", rut);
        return usuarioOpenLibRepository.findByRut(rut);
    }

    public UsuarioOpenLib agregarUsuario(UsuarioOpenLib usuario) {
        log.info("Agregando usuario: {}", usuario.getNombre());
        return usuarioOpenLibRepository.save(usuario);
    }

    public UsuarioOpenLib modificarUsuario(Long id, UsuarioOpenLib usuarioModificado) {
        log.info("Modificando usuario con ID: {}", id);
        UsuarioOpenLib usuarioExistente = usuarioOpenLibRepository.findById(id)
            .orElseThrow(() -> {
                log.error("Usuario {} no encontrado", id);
                return new RuntimeException("Usuario " + id + " no encontrado");
            });
        usuarioExistente.setNombre(usuarioModificado.getNombre());
        usuarioExistente.setApellido(usuarioModificado.getApellido());
        usuarioExistente.setCorreo(usuarioModificado.getCorreo());
        usuarioExistente.setRut(usuarioModificado.getRut());
        usuarioExistente.setNumeroTelefono(usuarioModificado.getNumeroTelefono());
        return usuarioOpenLibRepository.save(usuarioExistente);
    }

    public void eliminarUsuario(Long id) {
        log.info("Eliminando usuario con ID: {}", id);
        usuarioOpenLibRepository.deleteById(id);
    }
}