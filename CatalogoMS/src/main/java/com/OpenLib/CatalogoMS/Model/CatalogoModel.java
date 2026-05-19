package com.OpenLib.CatalogoMS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Catalogo_OpenLib")
public class CatalogoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El ISBN es obligatorio")
    @Size(max = 20, message = "El ISBN no puede exceder los 20 caracteres")
    @Column(unique = true, nullable = false, length = 20)
    private String isbn;

    @NotBlank(message = "El titulo es obligatorio")
    @Size(max = 100, message = "El titulo no puede exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    @Size(max = 60, message = "El autor no puede exceder los 60 caracteres")
    @Column(nullable = false, length = 60)
    private String autor;

    @NotBlank(message = "El genero es obligatorio")
    @Size(max = 40, message = "El genero no puede exceder los 40 caracteres")
    @Column(nullable = false, length = 40)
    private String genero;

    @NotNull(message = "El año de publicacion es obligatorio")
    @Min(value = 1000, message = "El año no es valido")
    @Max(value = 2100, message = "El año no es valido")
    @Column(nullable = false)
    private Integer anioPublicacion;

    @NotNull(message = "El stock inicial es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false)
    private Integer stockDisponible;

}
