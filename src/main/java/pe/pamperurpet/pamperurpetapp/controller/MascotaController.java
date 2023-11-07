package pe.pamperurpet.pamperurpetapp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pe.pamperurpet.pamperurpetapp.dtos.MascotaDTO;
import pe.pamperurpet.pamperurpetapp.entities.Mascota;
import pe.pamperurpet.pamperurpetapp.entities.Propietario;
import pe.pamperurpet.pamperurpetapp.exceptions.MascotaNotFoundException;
import pe.pamperurpet.pamperurpetapp.exceptions.PropietarioNotFoundException;
import pe.pamperurpet.pamperurpetapp.interfaceservice.MascotaService;
import pe.pamperurpet.pamperurpetapp.interfaceservice.PropietarioService;
import pe.pamperurpet.pamperurpetapp.repositories.PropietarioRepository;
import pe.pamperurpet.pamperurpetapp.services.MascotaServiceImpl;
import pe.pamperurpet.pamperurpetapp.services.PropietarioServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class MascotaController {
    @Autowired //inyectando
    private MascotaService mascotaService;
    @Autowired //inyectando
    private MascotaServiceImpl mascotaServiceImpl;
    @Autowired //inyectando
    private PropietarioService propietarioService;
    @Autowired //inyectando
    private PropietarioRepository propietarioRepository;
    @Autowired //inyectando
    private PropietarioServiceImpl propietarioServiceImpl;

    @PostMapping("/mascota")
    public ResponseEntity<MascotaDTO> register(@RequestBody MascotaDTO mascotaDTO){
        Mascota mascota = convertToEntity(mascotaDTO);
        mascota = mascotaServiceImpl.register(mascota);
        mascotaDTO = convertToDto(mascota);
        return new ResponseEntity<MascotaDTO>(mascotaDTO, HttpStatus.OK);
    }

    @PostMapping("/mascota/{propietarioid}")
    public ResponseEntity<MascotaDTO> register(@PathVariable Long propietarioid, @RequestBody MascotaDTO mascotaDTO) throws PropietarioNotFoundException {
        try {
            // Buscar al propietario por su ID utilizando propietarioServiceImpl
            Propietario propietario = propietarioServiceImpl.getPropietarioById(propietarioid);

            // Convertir el DTO de mascota a entidad Mascota
            Mascota mascota = convertToEntity(mascotaDTO);

            // Establecer la relación con el propietario
            mascota.setPropietario(propietario);

            // Registrar la mascota
            mascota = mascotaServiceImpl.register(mascota);

            // Convertir la entidad mascota a DTO
            mascotaDTO = convertToDto(mascota);

            return new ResponseEntity<MascotaDTO>(mascotaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/mascotas")
    public ResponseEntity<List<MascotaDTO>> listMascotas() {
        try {
            List<MascotaDTO> mascotas = mascotaServiceImpl.listMascotas();
            return new ResponseEntity<>(mascotas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/mascotaupdate/{id_mas}")
    public ResponseEntity<MascotaDTO> updateDataMascota(@PathVariable Long id_mas, @RequestBody MascotaDTO nuevosDatos) {
        try {
            Mascota mascotaActualizado = mascotaServiceImpl.updateDataMascota(id_mas, nuevosDatos);
            MascotaDTO responseDTO = convertToDto(mascotaActualizado);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (MascotaNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/mascotadelete/{id_mas}")
    MascotaDTO delete(@PathVariable(value = "id_mas") Long id_mas){
        MascotaDTO mascota;
        try{
            mascota = mascotaService.deleteMascota(id_mas);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se puede eliminar");
        }
        return mascota;
    }

    private MascotaDTO convertToDto(Mascota mascota) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mascota, MascotaDTO.class);
    }

    private Mascota convertToEntity(MascotaDTO mascotaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mascotaDTO, Mascota.class);
    }

    private List<MascotaDTO> convertToLisDto(List<Mascota> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
