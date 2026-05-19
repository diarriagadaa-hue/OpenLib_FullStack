//Diego andres Arriagada Aranguiz
package com.OpenLib.PrestamoMS.Repository;

import java.util.List;
import com.OpenLib.PrestamoMS.Model.PrestamoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<PrestamoModel, Long> {
    List<PrestamoModel> findByRutUsuario(String rutUsuario);
    List<PrestamoModel> findByEstado(String estado);
}