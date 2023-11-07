package pe.pamperurpet.pamperurpetapp.interfaceservice;

import pe.pamperurpet.pamperurpetapp.dtos.MascotaDTO;
import pe.pamperurpet.pamperurpetapp.entities.Mascota;
import pe.pamperurpet.pamperurpetapp.exceptions.MascotaNotFoundException;

import java.util.List;

public interface MascotaService {
    public Mascota register(Mascota mascota);
    public List<MascotaDTO> listMascotas();
    public Mascota updateDataMascota(Long id_mas, MascotaDTO nuevosDatos) throws MascotaNotFoundException;

    Mascota getMascotaById(Long id_mas);

    public MascotaDTO deleteMascota(Long id_mas) throws Exception;
}
