package pe.pamperurpet.pamperurpetapp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.pamperurpet.pamperurpetapp.dtos.MascotaDTO;
import pe.pamperurpet.pamperurpetapp.entities.Mascota;
import pe.pamperurpet.pamperurpetapp.exceptions.MascotaNotFoundException;
import pe.pamperurpet.pamperurpetapp.interfaceservice.MascotaService;
import pe.pamperurpet.pamperurpetapp.repositories.MascotaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MascotaServiceImpl implements MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Mascota register(Mascota mascota){
        return mascotaRepository.save(mascota);
    }

    public List<MascotaDTO> listMascotas() {
        List<Mascota> mascotas = mascotaRepository.findAll();
        return mascotas.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Mascota updateDataMascota(Long id_mas, MascotaDTO nuevosDatos) throws MascotaNotFoundException {
        Optional<Mascota> optionalMascota = mascotaRepository.findById(id_mas);

        if (optionalMascota.isPresent()) {
            Mascota mascota = optionalMascota.get();

            // Actualiza los datos con los valores proporcionados en nuevosDatos
            mascota.setNombre_mas(nuevosDatos.getNombre_mas());
            mascota.setRaza_mas(nuevosDatos.getRaza_mas());
            mascota.setEdad_mas(nuevosDatos.getEdad_mas());

            // Guarda el postulante actualizado en la base de datos
            return mascotaRepository.save(mascota);
        } else {
            // Si no se encuentra el postulante con el ID dado, puedes manejar el error de alguna manera, por ejemplo, lanzando una excepción.
            throw new MascotaNotFoundException("No se encontró una mascota con el ID proporcionado");
        }
    }

    @Override
    public Mascota getMascotaById(Long id_mas) {
        Optional<Mascota> optionalMascota = mascotaRepository.findById(id_mas);

        if (optionalMascota.isPresent()) {
            return optionalMascota.get();
        } else {
            try {
                throw new MascotaNotFoundException("No se encontró un propietario con el ID proporcionado");
            } catch (MascotaNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public MascotaDTO deleteMascota(Long id_mas) throws Exception {
        Mascota mascota = mascotaRepository.findById(id_mas)
                .orElseThrow(() -> new Exception("No se encontró entidad"));

        mascotaRepository.delete(mascota);

        return convertToDto(mascota);
    }

    private List<MascotaDTO> convertToLisDto(List<Mascota> mascotas){
        return mascotas.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private MascotaDTO convertToDto(Mascota mascota) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mascota, MascotaDTO.class);
    }


}
