package pe.pamperurpet.pamperurpetapp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.pamperurpet.pamperurpetapp.dtos.PaseadorDTO;
import pe.pamperurpet.pamperurpetapp.entities.Paseador;
import pe.pamperurpet.pamperurpetapp.exceptions.PaseadorNotFoundException;
import pe.pamperurpet.pamperurpetapp.interfaceservice.PaseadorService;
import pe.pamperurpet.pamperurpetapp.repositories.PaseadorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaseadorServiceImpl implements PaseadorService {
    @Autowired
    private PaseadorRepository paseadorRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Paseador register(Paseador paseador){
        return paseadorRepository.save(paseador);
    }

    public List<PaseadorDTO> listPaseadores() {
        List<Paseador> paseadores = paseadorRepository.findAll();
        return paseadores.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public Paseador getPaseadorById(Long id_pas) {
        Optional<Paseador> optionalPaseador = paseadorRepository.findById(id_pas);

        if (optionalPaseador.isPresent()) {
            return optionalPaseador.get();
        } else {
            try {
                throw new PaseadorNotFoundException("No se encontró un paseador con el ID proporcionado");
            } catch (PaseadorNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public Paseador updateDataPaseador(Long id_pas, PaseadorDTO nuevosDatos) throws PaseadorNotFoundException {
        Optional<Paseador> optionalPaseador = paseadorRepository.findById(id_pas);

        if (optionalPaseador.isPresent()) {
            Paseador paseador = optionalPaseador.get();

            // Actualiza los datos con los valores proporcionados en nuevosDatos
            paseador.setNombreapellido_pas(nuevosDatos.getNombreapellido_pas());
            paseador.setTelefono_pas(nuevosDatos.getTelefono_pas());
            paseador.setCorreo_pas(nuevosDatos.getCorreo_pas());
            paseador.setContraseña_pas(nuevosDatos.getContraseña_pas());

            // Guarda el postulante actualizado en la base de datos
            return paseadorRepository.save(paseador);
        } else {
            // Si no se encuentra el postulante con el ID dado, puedes manejar el error de alguna manera, por ejemplo, lanzando una excepción.
            throw new PaseadorNotFoundException("No se encontró un paseador con el ID proporcionado");
        }
    }

    private List<PaseadorDTO> convertToLisDto(List<Paseador> paseadores){
        return paseadores.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private PaseadorDTO convertToDto(Paseador paseador) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(paseador, PaseadorDTO.class);
    }
}
