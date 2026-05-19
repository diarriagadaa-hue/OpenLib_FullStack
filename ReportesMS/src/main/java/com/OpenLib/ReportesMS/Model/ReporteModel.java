package com.OpenLib.ReportesMS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Reportes_OpenLib")
public class ReporteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El tipo de reporte es obligatorio")
    @Size(max = 40, message = "El tipo no puede exceder los 40 caracteres")
    @Column(nullable = false, length = 40)
    private String tipoReporte; // INVENTARIO, PRESTAMOS, MULTAS, NOTIFICACIONES

    @NotBlank(message = "El titulo del reporte es obligatorio")
    @Size(max = 100, message = "El titulo no puede exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String titulo;

    @NotBlank(message = "El contenido es obligatorio")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

    @NotBlank(message = "El microservicio de origen es obligatorio")
    @Size(max = 30, message = "El origen no puede exceder los 30 caracteres")
    @Column(nullable = false, length = 30)
    private String origenMS; // CatalogoMS, InventarioMS, etc.

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 20, message = "El estado no puede exceder los 20 caracteres")
    @Column(nullable = false, length = 20)
    private String estado; // GENERADO, PENDIENTE

}
