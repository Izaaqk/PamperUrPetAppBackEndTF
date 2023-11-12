package pe.pamperurpet.pamperurpetapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.pamperurpet.pamperurpetapp.entities.Propietario;

import java.util.Optional;

@Repository
public interface PropietarioRepository extends JpaRepository<Propietario,Long> {
    Optional<Propietario> findById(Long propietarioid);
    @Query("SELECT p FROM Propietario p WHERE p.correo_prop = :correo")
    Propietario findByCorreo_prop(@Param("correo") String correo);
}
