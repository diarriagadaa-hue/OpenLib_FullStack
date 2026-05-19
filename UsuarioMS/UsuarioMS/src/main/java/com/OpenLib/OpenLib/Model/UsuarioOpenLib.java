//Diego andres Arriagada Aranguiz
package com.OpenLib.OpenLib.Model;


import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Usuarios_OpenLib")
public class UsuarioOpenLib {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El RUT es obligatorio")
    @Size(max = 12, message = "El RUT no puede exceder los 12 caracteres")
    @Column(unique = true, nullable = false,length = 12)
    private String rut;

    @NotBlank(message = "El Nombre no puede estar vacío")
    @Size(max = 20, message = "El Nombre no puede exceder los 20 caracteres")
    @Column(nullable = false,length = 20)
    private String nombre;

    @NotBlank(message = "El Apellido no puede estar vacío")
    @Size(max = 50, message = "El Apellido no puede exceder los 50 caracteres")
    @Column(nullable = false,length = 50)
    private String apellido;

    @NotBlank(message = "El Correo no puede estar vacío")
    @Email(message = "El Correo debe tener un formato válido")
    @Column(unique = true, nullable = false)
    private String correo;

    @NotBlank(message = "El Número de Teléfono no puede estar vacío")
    @Size(max = 9, message = "El Número de Teléfono no puede exceder los 9 caracteres")
    @Column(nullable = false,length = 9)
    private String numeroTelefono;

}
