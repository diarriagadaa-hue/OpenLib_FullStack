//Diego andres Arriagada Aranguiz
package com.OpenLib.Resena.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Resenas_OpenLib")
public class ReseñaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre de usuario es obligatorio")
    @Size(max = 20, message = "El nombre de usuario no puede exceder los 20 caracteres")
    @Column(nullable = false, length = 20)
    private String nombreUsuario;

    @NotBlank(message = "El Titulo es obligatorio")
    @Size(max = 100, message = "El Titulo no puede exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String libro;

    @NotNull(message = "La Calificacion obligatoria")
    @Min(value = 1, message = "Calificacion minima es 1")
    @Max(value = 7, message = "Calificacion maxima es 7")
    @Column(nullable = false)
    private Integer calificacion;

    @NotBlank(message = "El Comentario es obligatorio")
    @Size(max = 500, message = "El Comentario no puede exceder los 500 caracteres")
    @Column(length = 500)
    private String comentario;

}
