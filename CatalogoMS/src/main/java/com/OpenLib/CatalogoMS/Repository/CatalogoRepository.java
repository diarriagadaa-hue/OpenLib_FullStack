package com.OpenLib.CatalogoMS.Repository;

import com.OpenLib.CatalogoMS.Model.CatalogoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogoRepository extends JpaRepository<CatalogoModel, Long> {

    Optional<CatalogoModel> findByIsbn(String isbn);

}
