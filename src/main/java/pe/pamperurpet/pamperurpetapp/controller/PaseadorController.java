package pe.pamperurpet.pamperurpetapp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.pamperurpet.pamperurpetapp.dtos.PaseadorDTO;
import pe.pamperurpet.pamperurpetapp.entities.Paseador;
import pe.pamperurpet.pamperurpetapp.exceptions.PaseadorNotFoundException;
import pe.pamperurpet.pamperurpetapp.interfaceservice.PaseadorService;
import pe.pamperurpet.pamperurpetapp.services.PaseadorServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "http://18.216.74.240"})
public class PaseadorController {
    @Autowired //inyectando
    private PaseadorService paseadorService;
    @Autowired //inyectando
    private PaseadorServiceImpl paseadorServiceImpl;

    @PostMapping("/paseador")
    public ResponseEntity<PaseadorDTO> register(@RequestBody PaseadorDTO paseadorDTO){
        Paseador paseador = convertToEntity(paseadorDTO);
        paseador = paseadorServiceImpl.register(paseador);
        paseadorDTO = convertToDto(paseador);
        return new ResponseEntity<PaseadorDTO>(paseadorDTO, HttpStatus.OK);
    }

    @GetMapping("/paseadores")
    public ResponseEntity<List<PaseadorDTO>> listPaseadores() {
        try {
            List<PaseadorDTO> paseadores = paseadorServiceImpl.listPaseadores();
            return new ResponseEntity<>(paseadores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/paseadorupdate/{id_pas}")
    public ResponseEntity<PaseadorDTO> updateDataPaseador(@PathVariable Long id_pas, @RequestBody PaseadorDTO nuevosDatos) {
        try {
            Paseador paseadorActualizado = paseadorServiceImpl.updateDataPaseador(id_pas, nuevosDatos);
            PaseadorDTO responseDTO = convertToDto(paseadorActualizado);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (PaseadorNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private PaseadorDTO convertToDto(Paseador paseador) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(paseador, PaseadorDTO.class);
    }

    private Paseador convertToEntity(PaseadorDTO paseadorDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(paseadorDTO, Paseador.class);
    }

    private List<PaseadorDTO> convertToLisDto(List<Paseador> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
