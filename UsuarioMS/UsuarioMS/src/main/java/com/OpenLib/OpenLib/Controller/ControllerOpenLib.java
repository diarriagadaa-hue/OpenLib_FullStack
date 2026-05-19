//Diego andres Arriagada Aranguiz
package com.OpenLib.OpenLib.Controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.OpenLib.OpenLib.Model.UsuarioOpenLib;
import com.OpenLib.OpenLib.Service.ServiceOpenLib;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class ControllerOpenLib {

    @Autowired
    private ServiceOpenLib serviceOpenLib;

    @GetMapping
    public ResponseEntity<List<UsuarioOpenLib>> mostrarUsuarios() {
        List<UsuarioOpenLib> usuarios = serviceOpenLib.mostrarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioOpenLib> buscarId(@PathVariable Long id) {
        return serviceOpenLib.buscarId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/rut/{rut}")
    public ResponseEntity<UsuarioOpenLib> buscarRut(@PathVariable String rut) {
        return serviceOpenLib.buscarRut(rut)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
   public ResponseEntity<UsuarioOpenLib> agregarUsuario(@Valid @RequestBody UsuarioOpenLib usuario) {
        UsuarioOpenLib nuevo = serviceOpenLib.agregarUsuario(usuario);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioOpenLib> modificarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioOpenLib usuarioModificado) {
    UsuarioOpenLib modificado = serviceOpenLib.modificarUsuario(id, usuarioModificado);
    return ResponseEntity.ok(modificado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
    serviceOpenLib.eliminarUsuario(id);
    return ResponseEntity.noContent().build();
    }



}
