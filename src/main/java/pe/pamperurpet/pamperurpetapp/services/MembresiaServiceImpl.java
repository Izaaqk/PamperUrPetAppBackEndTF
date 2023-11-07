package pe.pamperurpet.pamperurpetapp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.pamperurpet.pamperurpetapp.dtos.MembresiaDTO;
import pe.pamperurpet.pamperurpetapp.entities.Membresia;
import pe.pamperurpet.pamperurpetapp.interfaceservice.MembresiaService;
import pe.pamperurpet.pamperurpetapp.repositories.MembresiaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MembresiaServiceImpl implements MembresiaService {
    @Autowired
    private MembresiaRepository membresiaRepository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Membresia register(Membresia membresia){
        return membresiaRepository.save(membresia);
    }

    public List<MembresiaDTO> listMembresias() {
        List<Membresia> membresias = membresiaRepository.findAll();
        return membresias.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MembresiaDTO deleteMembresia(Long id_memb) throws Exception {
        Membresia membresia = membresiaRepository.findById(id_memb)
                .orElseThrow(() -> new Exception("No se encontró entidad"));

        membresiaRepository.delete(membresia);

        return convertToDto(membresia);
    }

    private List<MembresiaDTO> convertToLisDto(List<Membresia> membresias){
        return membresias.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private MembresiaDTO convertToDto(Membresia membresia) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(membresia, MembresiaDTO.class);
    }

}
