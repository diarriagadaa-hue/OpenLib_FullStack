package com.OpenLib.NotificacionesMS.Repository;

import com.OpenLib.NotificacionesMS.Model.NotificacionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<NotificacionModel, Long> {

    List<NotificacionModel> findByDestinatario(String destinatario);

    List<NotificacionModel> findByOrigen(String origen);

}
