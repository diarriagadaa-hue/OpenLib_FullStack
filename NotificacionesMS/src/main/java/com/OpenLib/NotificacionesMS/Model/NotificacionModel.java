package com.OpenLib.NotificacionesMS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Notificaciones_OpenLib")
public class NotificacionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El tipo de notificacion es obligatorio")
    @Size(max = 30, message = "El tipo no puede exceder los 30 caracteres")
    @Column(nullable = false, length = 30)
    private String tipo; // PRESTAMO, DEVOLUCION, MULTA, STOCK

    @NotBlank(message = "El destinatario es obligatorio")
    @Size(max = 60, message = "El destinatario no puede exceder los 60 caracteres")
    @Column(nullable = false, length = 60)
    private String destinatario;

    @NotBlank(message = "El mensaje es obligatorio")
    @Size(max = 300, message = "El mensaje no puede exceder los 300 caracteres")
    @Column(nullable = false, length = 300)
    private String mensaje;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 20, message = "El estado no puede exceder los 20 caracteres")
    @Column(nullable = false, length = 20)
    private String estado; // ENVIADA, PENDIENTE, FALLIDA

    // Microservicio de origen que envio la notificacion
    @NotBlank(message = "El origen es obligatorio")
    @Size(max = 30, message = "El origen no puede exceder los 30 caracteres")
    @Column(nullable = false, length = 30)
    private String origen;

}
