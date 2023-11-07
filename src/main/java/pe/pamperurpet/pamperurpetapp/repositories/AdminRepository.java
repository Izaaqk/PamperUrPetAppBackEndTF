package pe.pamperurpet.pamperurpetapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.pamperurpet.pamperurpetapp.entities.Admin;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findById(Long id_admin);
}
