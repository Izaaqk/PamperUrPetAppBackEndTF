package pe.pamperurpet.pamperurpetapp.interfaceservice;

import org.springframework.transaction.annotation.Transactional;
import pe.pamperurpet.pamperurpetapp.dtos.MembresiaDTO;
import pe.pamperurpet.pamperurpetapp.entities.Membresia;

import java.util.List;

public interface MembresiaService {
    @Transactional(rollbackFor = Exception.class)
    Membresia register(Membresia membresia);
    public List<MembresiaDTO> listMembresias();
    public MembresiaDTO deleteMembresia(Long id_memb) throws Exception;

    Membresia getMembresiaById(Long id_memb);
}
