//Diego andres Arriagada Aranguiz
package com.OpenLib.PrestamoMS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Prestamos_OpenLib")
public class PrestamoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del usuario es obligatorio")
    @Column(nullable = false, length = 50)
    private String nombreUsuario;

    @NotBlank(message = "El RUT es obligatorio")
    @Column(nullable = false, length = 12)
    private String rutUsuario;

    @NotBlank(message = "El libro es obligatorio")
    @Column(nullable = false, length = 100)
    private String libro;

    @NotNull(message = "La fecha de préstamo es obligatoria")
    @Column(nullable = false)
    private LocalDate fechaPrestamo;

    @NotNull(message = "La fecha de devolución es obligatoria")
    @Column(nullable = false)
    private LocalDate fechaDevolucion;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false, length = 20)
    private String estado; // ACTIVO, DEVUELTO, ATRASADO
}