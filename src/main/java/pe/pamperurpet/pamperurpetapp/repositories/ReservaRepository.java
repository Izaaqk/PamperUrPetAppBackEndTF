package pe.pamperurpet.pamperurpetapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.pamperurpet.pamperurpetapp.entities.Reserva;

import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long> {

    Optional<Reserva> findById(Long id_reser);
}
