package pe.pamperurpet.pamperurpetapp.interfaceservice;

import org.springframework.transaction.annotation.Transactional;
import pe.pamperurpet.pamperurpetapp.dtos.PaseadorDTO;
import pe.pamperurpet.pamperurpetapp.entities.Paseador;
import pe.pamperurpet.pamperurpetapp.exceptions.PaseadorNotFoundException;

import java.util.List;

public interface PaseadorService {
    @Transactional(rollbackFor = Exception.class)
    Paseador register(Paseador paseador);
    public Paseador getPaseadorById(Long id_pas);
    public List<PaseadorDTO> listPaseadores();
    public Paseador updateDataPaseador(Long id_pas, PaseadorDTO nuevosDatos) throws PaseadorNotFoundException;
}
