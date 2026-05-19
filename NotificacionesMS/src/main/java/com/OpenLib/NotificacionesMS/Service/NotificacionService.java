package com.OpenLib.NotificacionesMS.Service;

import com.OpenLib.NotificacionesMS.Model.NotificacionModel;
import com.OpenLib.NotificacionesMS.Repository.NotificacionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    private static final Logger logger = LoggerFactory.getLogger(NotificacionService.class);

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<NotificacionModel> mostrarNotificaciones() {
        logger.info("Obteniendo todas las notificaciones");
        return notificacionRepository.findAll();
    }

    public Optional<NotificacionModel> buscarId(Long id) {
        logger.info("Buscando notificacion con id: {}", id);
        return notificacionRepository.findById(id);
    }

    public List<NotificacionModel> buscarPorDestinatario(String destinatario) {
        logger.info("Buscando notificaciones del destinatario: {}", destinatario);
        return notificacionRepository.findByDestinatario(destinatario);
    }

    public List<NotificacionModel> buscarPorOrigen(String origen) {
        logger.info("Buscando notificaciones del microservicio: {}", origen);
        return notificacionRepository.findByOrigen(origen);
    }

    // Cualquier microservicio puede llamar a este metodo para enviar notificacion
    public NotificacionModel recibirNotificacion(NotificacionModel notificacion) {
        notificacion.setEstado("ENVIADA");
        logger.info("Notificacion recibida desde {} para {}: {}", notificacion.getOrigen(), notificacion.getDestinatario(), notificacion.getMensaje());
        return notificacionRepository.save(notificacion);
    }

    public NotificacionModel modificarNotificacion(Long id, NotificacionModel notificacionModificada) {
        NotificacionModel notificacionExistente = notificacionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("La notificacion " + id + " no existe"));

        notificacionExistente.setTipo(notificacionModificada.getTipo());
        notificacionExistente.setDestinatario(notificacionModificada.getDestinatario());
        notificacionExistente.setMensaje(notificacionModificada.getMensaje());
        notificacionExistente.setEstado(notificacionModificada.getEstado());
        notificacionExistente.setOrigen(notificacionModificada.getOrigen());

        logger.info("Notificacion modificada con id: {}", id);
        return notificacionRepository.save(notificacionExistente);
    }

    public void eliminarNotificacion(Long id) {
        logger.info("Eliminando notificacion con id: {}", id);
        notificacionRepository.deleteById(id);
    }

}
