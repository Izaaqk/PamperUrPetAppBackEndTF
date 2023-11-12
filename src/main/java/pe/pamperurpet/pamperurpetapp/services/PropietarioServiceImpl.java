package pe.pamperurpet.pamperurpetapp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.pamperurpet.pamperurpetapp.dtos.PropietarioDTO;
import pe.pamperurpet.pamperurpetapp.entities.Propietario;
import pe.pamperurpet.pamperurpetapp.exceptions.PropietarioNotFoundException;
import pe.pamperurpet.pamperurpetapp.interfaceservice.PropietarioService;
import pe.pamperurpet.pamperurpetapp.repositories.PropietarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropietarioServiceImpl implements PropietarioService {
    @Autowired
    private PropietarioRepository propietarioRepository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Propietario register(Propietario propietario){
        return propietarioRepository.save(propietario);
    }

    public List<PropietarioDTO> listPropietarios() {
        List<Propietario> propietarios = propietarioRepository.findAll();
        return propietarios.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @Override
    public Propietario login(String correo, Long contraseña) {
        Propietario propietario = propietarioRepository.findByCorreo_prop(correo);

        if (propietario != null) {
            if (propietario.getContraseña_prop().equals(contraseña)) {
                return propietario;
            }
        }

        return null;
    }

    @Override
    public Propietario getPropietarioById(Long propietarioid) {
        Optional<Propietario> optionalPropietario = propietarioRepository.findById(propietarioid);

        if (optionalPropietario.isPresent()) {
            return optionalPropietario.get();
        } else {
            try {
                throw new PropietarioNotFoundException("No se encontró un propietario con el ID proporcionado");
            } catch (PropietarioNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Propietario updateDataPropietario(Long propietarioid, PropietarioDTO nuevosDatos) throws PropietarioNotFoundException {
        Optional<Propietario> optionalPropietario = propietarioRepository.findById(propietarioid);

        if (optionalPropietario.isPresent()) {
            Propietario propietario = optionalPropietario.get();

            // Actualiza los datos con los valores proporcionados en nuevosDatos
            propietario.setNombreapellido_prop(nuevosDatos.getNombreapellido_prop());
            propietario.setTelefono_prop(nuevosDatos.getTelefono_prop());
            propietario.setCorreo_prop(nuevosDatos.getCorreo_prop());
            propietario.setContraseña_prop(nuevosDatos.getContraseña_prop());

            // Guarda el postulante actualizado en la base de datos
            return propietarioRepository.save(propietario);
        } else {
            // Si no se encuentra el postulante con el ID dado, puedes manejar el error de alguna manera, por ejemplo, lanzando una excepción.
            throw new PropietarioNotFoundException("No se encontró un paseador con el ID proporcionado");
        }
    }

    public PropietarioDTO deletePropietario(Long propietarioid) throws Exception {
        Propietario propietario = propietarioRepository.findById(propietarioid)
                .orElseThrow(() -> new Exception("No se encontró entidad"));

        propietarioRepository.delete(propietario);

        return convertToDto(propietario);
    }


    private List<PropietarioDTO> convertToLisDto(List<Propietario> propietarios){
        return propietarios.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private PropietarioDTO convertToDto(Propietario propietario) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(propietario, PropietarioDTO.class);
    }
}