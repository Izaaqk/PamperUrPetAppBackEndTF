package pe.pamperurpet.pamperurpetapp.interfaceservice;

import pe.pamperurpet.pamperurpetapp.dtos.PropietarioDTO;
import pe.pamperurpet.pamperurpetapp.entities.Propietario;
import pe.pamperurpet.pamperurpetapp.exceptions.PropietarioNotFoundException;

import java.util.List;

public interface PropietarioService {
    public Propietario register(Propietario propietario);
    public List<PropietarioDTO> listPropietarios();

    Propietario getPropietarioById(Long propietarioId);

    public Propietario updateDataPropietario(Long propietarioid, PropietarioDTO nuevosDatos) throws PropietarioNotFoundException;

    public PropietarioDTO deletePropietario(Long propietarioid) throws Exception;

}
