//Diego andres Arriagada Aranguiz
package com.OpenLib.OpenLib.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.OpenLib.OpenLib.Model.UsuarioOpenLib;

@Repository
public interface InterfaceRepositoryOpenLib extends JpaRepository<UsuarioOpenLib, Long> {
    
    Optional<UsuarioOpenLib> findByRut(String rut);
}
