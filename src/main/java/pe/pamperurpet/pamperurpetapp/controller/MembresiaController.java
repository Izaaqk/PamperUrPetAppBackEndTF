package pe.pamperurpet.pamperurpetapp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pe.pamperurpet.pamperurpetapp.dtos.MembresiaDTO;
import pe.pamperurpet.pamperurpetapp.entities.Membresia;
import pe.pamperurpet.pamperurpetapp.interfaceservice.MembresiaService;
import pe.pamperurpet.pamperurpetapp.services.MembresiaServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class MembresiaController {
    @Autowired //inyectando
    private MembresiaService membresiaService;
    @Autowired //inyectando
    private MembresiaServiceImpl membresiaServiceImpl;

    @PostMapping("/membresia")
    public ResponseEntity<MembresiaDTO> register(@RequestBody MembresiaDTO membresiaDTO){
        Membresia membresia = convertToEntity(membresiaDTO);
        membresia = membresiaServiceImpl.register(membresia);
        membresiaDTO = convertToDto(membresia);
        return new ResponseEntity<MembresiaDTO>(membresiaDTO, HttpStatus.OK);
    }

    @GetMapping("/membresias")
    public ResponseEntity<List<MembresiaDTO>> listMembresias() {
        try {
            List<MembresiaDTO> membresias = membresiaServiceImpl.listMembresias();
            return new ResponseEntity<>(membresias, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/membresiadelete/{id_memb}")
    MembresiaDTO delete(@PathVariable(value = "id_memb") Long id_memb){
        MembresiaDTO membresia;
        try{
            membresia = membresiaService.deleteMembresia(id_memb);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se puede eliminar");
        }
        return membresia;
    }

    private MembresiaDTO convertToDto(Membresia membresia) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(membresia, MembresiaDTO.class);
    }

    private Membresia convertToEntity(MembresiaDTO membresiaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(membresiaDTO, Membresia.class);
    }

    private List<MembresiaDTO> convertToLisDto(List<Membresia> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
