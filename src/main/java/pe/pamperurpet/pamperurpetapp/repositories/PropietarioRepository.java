package pe.pamperurpet.pamperurpetapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.pamperurpet.pamperurpetapp.entities.Propietario;

import java.util.Optional;

@Repository
public interface PropietarioRepository extends JpaRepository<Propietario,Long> {
    Optional<Propietario> findById(Long propietarioid);
}
