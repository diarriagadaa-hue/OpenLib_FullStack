package com.OpenLib.NotificacionesMS.Controller;

import com.OpenLib.NotificacionesMS.Model.NotificacionModel;
import com.OpenLib.NotificacionesMS.Service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public ResponseEntity<List<NotificacionModel>> mostrarNotificaciones() {
        List<NotificacionModel> notificaciones = notificacionService.mostrarNotificaciones();
        return ResponseEntity.ok(notificaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacionModel> buscarId(@PathVariable Long id) {
        return notificacionService.buscarId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/destinatario/{destinatario}")
    public ResponseEntity<List<NotificacionModel>> buscarPorDestinatario(@PathVariable String destinatario) {
        List<NotificacionModel> notificaciones = notificacionService.buscarPorDestinatario(destinatario);
        return ResponseEntity.ok(notificaciones);
    }

    @GetMapping("/origen/{origen}")
    public ResponseEntity<List<NotificacionModel>> buscarPorOrigen(@PathVariable String origen) {
        List<NotificacionModel> notificaciones = notificacionService.buscarPorOrigen(origen);
        return ResponseEntity.ok(notificaciones);
    }

    // Cualquier microservicio puede POST aqui para enviar notificacion
    @PostMapping
    public ResponseEntity<NotificacionModel> recibirNotificacion(@Valid @RequestBody NotificacionModel notificacion) {
        NotificacionModel nueva = notificacionService.recibirNotificacion(notificacion);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacionModel> modificarNotificacion(@PathVariable Long id, @Valid @RequestBody NotificacionModel notificacionModificada) {
        NotificacionModel modificada = notificacionService.modificarNotificacion(id, notificacionModificada);
        return ResponseEntity.ok(modificada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable Long id) {
        notificacionService.eliminarNotificacion(id);
        return ResponseEntity.noContent().build();
    }

}
