package pe.pamperurpet.pamperurpetapp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pe.pamperurpet.pamperurpetapp.dtos.MembresiaDTO;
import pe.pamperurpet.pamperurpetapp.dtos.PropietarioDTO;
import pe.pamperurpet.pamperurpetapp.entities.Admin;
import pe.pamperurpet.pamperurpetapp.entities.Membresia;
import pe.pamperurpet.pamperurpetapp.entities.Propietario;
import pe.pamperurpet.pamperurpetapp.exceptions.PropietarioNotFoundException;
import pe.pamperurpet.pamperurpetapp.interfaceservice.PropietarioService;
import pe.pamperurpet.pamperurpetapp.repositories.PagoRepository;
import pe.pamperurpet.pamperurpetapp.repositories.PropietarioRepository;
import pe.pamperurpet.pamperurpetapp.services.AdminServiceImpl;
import pe.pamperurpet.pamperurpetapp.services.MembresiaServiceImpl;
import pe.pamperurpet.pamperurpetapp.services.PropietarioServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "http://13.59.192.42"})
public class PropietarioController {
    @Autowired //inyectando
    private PropietarioService propietarioService;
    @Autowired //inyectando
    private PropietarioRepository propietarioRepository;

    @Autowired //inyectando
    private PagoRepository pagoRepository;
    @Autowired //inyectando
    private PropietarioServiceImpl propietarioServiceImpl;
    @Autowired //inyectando
    private AdminServiceImpl adminServiceImpl;
    @Autowired //inyectando
    private MembresiaServiceImpl membresiaServiceImpl;


    @PostMapping("/propietario")
    public ResponseEntity<PropietarioDTO> register(@RequestBody PropietarioDTO propietarioDTO){
        Propietario propietario = convertToEntity(propietarioDTO);
        propietario = propietarioServiceImpl.register(propietario);
        propietarioDTO = convertToDto(propietario);
        return new ResponseEntity<PropietarioDTO>(propietarioDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<PropietarioDTO> login(@RequestBody PropietarioDTO propietarioDTO) {
        try {
            // Realiza la lógica de autenticación aquí
            Propietario propietario = propietarioServiceImpl.login(propietarioDTO.getCorreo_prop(), propietarioDTO.getContraseña_prop());

            if (propietario != null) {
                // Si las credenciales son válidas, devuelve el Propietario
                PropietarioDTO responseDTO = convertToDto(propietario);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                // Si las credenciales son inválidas, devuelve un ResponseEntity con HttpStatus.UNAUTHORIZED
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/propietario/membresia")
    public ResponseEntity<PropietarioDTO> registerWithMembresia(@RequestBody PropietarioDTO propietarioDTO) {
        try {
            // Convertir PropietarioDTO a entidad Propietario
            Propietario propietario = new Propietario();
            propietario.setPropietarioid(propietarioDTO.getPropietarioid());
            propietario.setNombreapellido_prop(propietarioDTO.getNombreapellido_prop());
            propietario.setTelefono_prop(propietarioDTO.getTelefono_prop());
            propietario.setCorreo_prop(propietarioDTO.getCorreo_prop());
            propietario.setContraseña_prop(propietarioDTO.getContraseña_prop());

            // Crear la entidad Membresia a partir de MembresiaDTO
            Membresia membresia = new Membresia();
            membresia.setId_memb(propietarioDTO.getMembresiaDTO().getId_memb());
            membresia.setNumerotarjeta_memb(propietarioDTO.getMembresiaDTO().getNumerotarjeta_memb());
            membresia.setFechavencimiento_memb(propietarioDTO.getMembresiaDTO().getFechavencimiento_memb());
            membresia.setCvv_memb(propietarioDTO.getMembresiaDTO().getCvv_memb());
            membresia.setNombrecompleto_memb(propietarioDTO.getMembresiaDTO().getNombrecompleto_memb());
            membresia.setMembresiaelegida_memb(propietarioDTO.getMembresiaDTO().getMembresiaelegida_memb());
            membresia.setMontopagar_memb(propietarioDTO.getMembresiaDTO().getMontopagar_memb());

            // Establecer la relación entre Propietario y Membresia
            propietario.setMembresia(membresia);

            // Guardar Propietario en la base de datos
            propietario = propietarioRepository.save(propietario);

            // Convertir el resultado a PropietarioDTO
            PropietarioDTO resultDTO = new PropietarioDTO();
            resultDTO.setPropietarioid(propietario.getPropietarioid());
            resultDTO.setNombreapellido_prop(propietario.getNombreapellido_prop());
            resultDTO.setTelefono_prop(propietario.getTelefono_prop());
            resultDTO.setCorreo_prop(propietario.getCorreo_prop());
            resultDTO.setContraseña_prop(propietario.getContraseña_prop());

            // Crear y configurar el MembresiaDTO en el resultado
            MembresiaDTO membresiaDTO = new MembresiaDTO();
            membresiaDTO.setId_memb(membresia.getId_memb());
            membresiaDTO.setNumerotarjeta_memb(membresia.getNumerotarjeta_memb());
            membresiaDTO.setFechavencimiento_memb(membresia.getFechavencimiento_memb());
            membresiaDTO.setCvv_memb(membresia.getCvv_memb());
            membresiaDTO.setNombrecompleto_memb(membresia.getNombrecompleto_memb());
            membresiaDTO.setMembresiaelegida_memb(membresia.getMembresiaelegida_memb());
            membresiaDTO.setMontopagar_memb(membresia.getMontopagar_memb());

            resultDTO.setMembresiaDTO(membresiaDTO);

            return new ResponseEntity<>(resultDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/propietario/{adminId}/{membresiaId}")
    public ResponseEntity<PropietarioDTO> registerWithAdminAndMembresia(
            @PathVariable Long adminId,
            @PathVariable Long membresiaId,
            @RequestBody PropietarioDTO propietarioDTO) {
        try {
            // Buscar al administrador por su ID
            Admin admin = adminServiceImpl.getAdminById(adminId);

            // Buscar la membresía por su ID (asumiendo que tienes un servicio correspondiente para esto)
            Membresia membresia = membresiaServiceImpl.getMembresiaById(membresiaId);

            // Convertir PropietarioDTO a entidad Propietario
            Propietario propietario = new Propietario();
            propietario.setNombreapellido_prop(propietarioDTO.getNombreapellido_prop());
            propietario.setTelefono_prop(propietarioDTO.getTelefono_prop());
            propietario.setCorreo_prop(propietarioDTO.getCorreo_prop());
            propietario.setContraseña_prop(propietarioDTO.getContraseña_prop());

            // Establecer la relación entre Propietario y Membresía
            propietario.setMembresia(membresia);

            // Establecer la relación entre Propietario y Administrador
            propietario.setAdmin(admin);

            // Guardar Propietario en la base de datos
            propietario = propietarioRepository.save(propietario);

            // Convertir el resultado a PropietarioDTO
            PropietarioDTO resultDTO = new PropietarioDTO();
            resultDTO.setPropietarioid(propietario.getPropietarioid());
            resultDTO.setNombreapellido_prop(propietario.getNombreapellido_prop());
            resultDTO.setTelefono_prop(propietario.getTelefono_prop());
            resultDTO.setCorreo_prop(propietario.getCorreo_prop());
            resultDTO.setContraseña_prop(propietario.getContraseña_prop());

            // ... (configura otros campos si es necesario)

            return new ResponseEntity<>(resultDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/propietarios")
    public ResponseEntity<List<PropietarioDTO>> listPropietario() {
        try {
            List<PropietarioDTO> propietarios = propietarioServiceImpl.listPropietarios();
            return new ResponseEntity<>(propietarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/propietarioupdate/{propietarioid}")
    public ResponseEntity<PropietarioDTO> updateDataPropietario(@PathVariable Long propietarioid, @RequestBody PropietarioDTO nuevosDatos) {
        try {
            Propietario propietarioActualizado = propietarioServiceImpl.updateDataPropietario(propietarioid, nuevosDatos);
            PropietarioDTO responseDTO = convertToDto(propietarioActualizado);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (PropietarioNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/propietariodelete/{propietarioid}")
    PropietarioDTO delete(@PathVariable(value = "propietarioid") Long propietarioid){
        PropietarioDTO propietario;
        try{
            propietario = propietarioService.deletePropietario(propietarioid);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se puede eliminar");
        }
        return propietario;
    }


    private PropietarioDTO convertToDto(Propietario propietario) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(propietario, PropietarioDTO.class);
    }

    private Propietario convertToEntity(PropietarioDTO propietarioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(propietarioDTO, Propietario.class);
    }

    private List<PropietarioDTO> convertToLisDto(List<Propietario> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
