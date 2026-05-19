//Diego andres Arriagada Aranguiz
package com.OpenLib.MultaMS.Repository;

import com.OpenLib.MultaMS.Model.MultaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MultaRepository extends JpaRepository<MultaModel, Long> {
    List<MultaModel> findByRut(String rut);
}