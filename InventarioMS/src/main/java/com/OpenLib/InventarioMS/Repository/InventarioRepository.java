package com.OpenLib.InventarioMS.Repository;

import com.OpenLib.InventarioMS.Model.InventarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<InventarioModel, Long> {

    Optional<InventarioModel> findByIsbnLibro(String isbnLibro);

}
