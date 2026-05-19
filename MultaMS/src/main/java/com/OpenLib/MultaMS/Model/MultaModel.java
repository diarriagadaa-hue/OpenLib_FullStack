//Diego andres Arriagada Aranguiz
package com.OpenLib.MultaMS.Model;

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
@Table(name = "Multas_OpenLib")
public class MultaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 20)
    private String nombreUsuario;

    @NotBlank(message = "El RUT es obligatorio")
    @Column(nullable = false, length = 12)
    private String rut;

    @NotBlank(message = "Libro/Prestamo es obligatorio")
    @Column(nullable = false, length = 100)
    private String prestamo;

    @NotNull(message = "Ingrese monto es obligatorio")
    @Min(value = 1, message = "El monto debe ser mayor a 0")
    @Column(nullable = false)
    private Integer monto;

    @NotNull(message = "La fecha es obligatoria")
    @Column(nullable = false)
    private LocalDate fecha;

    @Size(max = 200, message = "El motivo no puede exceder los 200 caracteres")
    @Column(length = 200)
    private String motivo;
}