//Diego andres Arriagada Aranguiz
package com.OpenLib.Resena.Repository;

import com.OpenLib.Resena.Model.ReseñaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReseñaRepository extends JpaRepository<ReseñaModel, Long> {


}
