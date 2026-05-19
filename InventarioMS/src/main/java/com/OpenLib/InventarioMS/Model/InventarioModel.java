package com.OpenLib.InventarioMS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Inventario_OpenLib")
public class InventarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El ISBN del libro es obligatorio")
    @Size(max = 20, message = "El ISBN no puede exceder los 20 caracteres")
    @Column(unique = true, nullable = false, length = 20)
    private String isbnLibro;

    @NotBlank(message = "El titulo del libro es obligatorio")
    @Size(max = 100, message = "El titulo no puede exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String tituloLibro;

    @NotNull(message = "La cantidad disponible es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    @Column(nullable = false)
    private Integer cantidadDisponible;

    @NotNull(message = "La cantidad total es obligatoria")
    @Min(value = 0, message = "La cantidad total no puede ser negativa")
    @Column(nullable = false)
    private Integer cantidadTotal;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 20, message = "El estado no puede exceder los 20 caracteres")
    @Column(nullable = false, length = 20)
    private String estado; // DISPONIBLE, AGOTADO, RESERVADO

}
