package pe.pamperurpet.pamperurpetapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.pamperurpet.pamperurpetapp.entities.Mascota;

import java.util.Optional;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota,Long> {
    Optional<Mascota> findById(Long id_mas);
}
