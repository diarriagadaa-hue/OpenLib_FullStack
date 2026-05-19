package com.OpenLib.ReportesMS.Repository;

import com.OpenLib.ReportesMS.Model.ReporteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<ReporteModel, Long> {

    List<ReporteModel> findByTipoReporte(String tipoReporte);

    List<ReporteModel> findByOrigenMS(String origenMS);

}
