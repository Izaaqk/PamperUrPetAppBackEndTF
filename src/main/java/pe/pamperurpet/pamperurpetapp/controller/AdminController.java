package pe.pamperurpet.pamperurpetapp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.pamperurpet.pamperurpetapp.dtos.AdminDTO;
import pe.pamperurpet.pamperurpetapp.dtos.MascotaDTO;
import pe.pamperurpet.pamperurpetapp.dtos.PaseadorDTO;
import pe.pamperurpet.pamperurpetapp.dtos.PropietarioDTO;
import pe.pamperurpet.pamperurpetapp.entities.Admin;
import pe.pamperurpet.pamperurpetapp.entities.Mascota;
import pe.pamperurpet.pamperurpetapp.entities.Paseador;
import pe.pamperurpet.pamperurpetapp.entities.Propietario;
import pe.pamperurpet.pamperurpetapp.exceptions.AdminNotFoundException;
import pe.pamperurpet.pamperurpetapp.exceptions.PropietarioNotFoundException;
import pe.pamperurpet.pamperurpetapp.interfaceservice.AdminService;
import pe.pamperurpet.pamperurpetapp.interfaceservice.MascotaService;
import pe.pamperurpet.pamperurpetapp.interfaceservice.PaseadorService;
import pe.pamperurpet.pamperurpetapp.interfaceservice.PropietarioService;
import pe.pamperurpet.pamperurpetapp.repositories.AdminRepository;
import pe.pamperurpet.pamperurpetapp.repositories.MascotaRepository;
import pe.pamperurpet.pamperurpetapp.repositories.PaseadorRepository;
import pe.pamperurpet.pamperurpetapp.repositories.PropietarioRepository;
import pe.pamperurpet.pamperurpetapp.services.AdminServiceImpl;
import pe.pamperurpet.pamperurpetapp.services.MascotaServiceImpl;
import pe.pamperurpet.pamperurpetapp.services.PaseadorServiceImpl;
import pe.pamperurpet.pamperurpetapp.services.PropietarioServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AdminController {
    @Autowired //inyectando
    private AdminRepository adminRepository;
    @Autowired //inyectando
    private AdminService adminService;
    @Autowired //inyectando
    private PropietarioRepository propietarioRepository;
    @Autowired //inyectando
    private PropietarioServiceImpl propietarioServiceImpl;
    @Autowired
    private PaseadorRepository paseadorRepository;

    @Autowired
    private PaseadorServiceImpl paseadorServiceImpl;
    @Autowired
    private PaseadorService paseadorService;
    @Autowired
    private PropietarioService propietarioService;
    @Autowired
    private MascotaService mascotaService;
    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private MascotaServiceImpl mascotaServiceImpl;
    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @PostMapping("/admin")
    public ResponseEntity<AdminDTO> register(@RequestBody AdminDTO adminDTO){
        Admin admin = convertToEntity(adminDTO);
        admin = adminServiceImpl.register(admin);
        adminDTO = convertToDto(admin);
        return new ResponseEntity<AdminDTO>(adminDTO, HttpStatus.OK);
    }

    @PostMapping("/propietario/{id_admin}") // Cambia la ruta a algo que tenga sentido para registrar un paseador
    public ResponseEntity<PropietarioDTO> register(@PathVariable Long id_admin, @RequestBody PropietarioDTO propietarioDTO) throws AdminNotFoundException {
        try {
            // Buscar al administrador por su ID utilizando adminServiceImpl
            Admin admin = adminServiceImpl.getAdminById(id_admin);

            // Convertir el DTO de paseador a entidad Paseador
            Propietario propietario = convertToEntity(propietarioDTO);

            // Establecer la relación con el administrador
            propietario.setAdmin(admin);

            // Registrar el paseador
            propietario = propietarioServiceImpl.register(propietario);

            // Convertir la entidad paseador a DTO
            propietarioDTO = convertToDto(propietario);

            return new ResponseEntity<PropietarioDTO>(propietarioDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id_admin}/modifypropietario/{propietarioid}") // Cambia la ruta para reflejar que estás modificando un propietario
    public PropietarioDTO modifyPropietario(@PathVariable Long id_admin,
                                            @PathVariable Long propietarioid,
                                            @RequestBody PropietarioDTO propietarioDTO) throws AdminNotFoundException {
        // Busca el administrador por ID
        Admin admin = adminService.getAdminById(id_admin);

        // Verifica si el administrador tiene permisos para modificar el propietario (puedes agregar lógica de autorización aquí)

        // Busca el propietario por ID
        Propietario propietario = propietarioService.getPropietarioById(propietarioid);

        // Actualiza los campos del propietario con los valores proporcionados en propietarioDTO
        propietario.setNombreapellido_prop(propietarioDTO.getNombreapellido_prop());
        propietario.setTelefono_prop(propietarioDTO.getTelefono_prop());
        propietario.setCorreo_prop(propietarioDTO.getCorreo_prop());
        propietario.setContraseña_prop(propietarioDTO.getContraseña_prop());

        // Guarda el propietario actualizado en el repositorio de propietarios
        propietario = propietarioService.register(propietario);

        // Puedes convertir el propietario actualizado a un DTO y devolverlo como respuesta
        return convertToDto(propietario);
    }




    @PostMapping("/paseador/{id_admin}") // Cambia la ruta a algo que tenga sentido para registrar un paseador
    public ResponseEntity<PaseadorDTO> register(@PathVariable Long id_admin, @RequestBody PaseadorDTO paseadorDTO) throws AdminNotFoundException {
        try {
            // Buscar al administrador por su ID utilizando adminServiceImpl
            Admin admin = adminServiceImpl.getAdminById(id_admin);

            // Convertir el DTO de paseador a entidad Paseador
            Paseador paseador = convertToEntity(paseadorDTO);

            // Establecer la relación con el administrador
            paseador.setAdmin(admin);

            // Registrar el paseador
            paseador = paseadorServiceImpl.register(paseador);

            // Convertir la entidad paseador a DTO
            paseadorDTO = convertToDto(paseador);

            return new ResponseEntity<PaseadorDTO>(paseadorDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id_admin}/modifypaseador/{paseadorid}") // Cambia la ruta para reflejar que estás modificando un paseador
    public PaseadorDTO modifyPaseador(@PathVariable Long id_admin,
                                      @PathVariable Long paseadorid,
                                      @RequestBody PaseadorDTO paseadorDTO) throws AdminNotFoundException {
        // Busca el administrador por ID
        Admin admin = adminService.getAdminById(id_admin);

        // Verifica si el administrador tiene permisos para modificar el paseador (puedes agregar lógica de autorización aquí)

        // Busca el paseador por ID
        Paseador paseador = paseadorService.getPaseadorById(paseadorid);

        // Actualiza los campos del paseador con los valores proporcionados en paseadorDTO
        paseador.setNombreapellido_pas(paseadorDTO.getNombreapellido_pas());
        paseador.setTelefono_pas(paseadorDTO.getTelefono_pas());
        paseador.setCorreo_pas(paseadorDTO.getCorreo_pas());
        paseador.setContraseña_pas(paseadorDTO.getContraseña_pas());

        // Guarda el paseador actualizado en el repositorio de paseadores
        paseador = paseadorService.register(paseador);

        // Puedes convertir el paseador actualizado a un DTO y devolverlo como respuesta
        return convertToDto(paseador);
    }



    @PostMapping("/admin/mascota/{id_admin}") // Cambia la ruta para reflejar que estás registrando una mascota
    public ResponseEntity<MascotaDTO> register(@PathVariable Long id_admin, @RequestBody MascotaDTO mascotaDTO) throws AdminNotFoundException {
        try {
            // Buscar al administrador por su ID utilizando adminServiceImpl
            Admin admin = adminServiceImpl.getAdminById(id_admin);

            // Convertir el DTO de mascota a entidad Mascota
            Mascota mascota = convertToEntity(mascotaDTO);

            // Establecer la relación con el administrador
            mascota.setAdmin(admin);

            // Registrar la mascota
            mascota = mascotaServiceImpl.register(mascota);

            // Convertir la entidad mascota a DTO
            mascotaDTO = convertToDto(mascota);

            return new ResponseEntity<MascotaDTO>(mascotaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/mascota/{adminid}/{propietarioid}")
    public ResponseEntity<MascotaDTO> register(
            @PathVariable Long adminid,
            @PathVariable Long propietarioid,
            @RequestBody MascotaDTO mascotaDTO) throws AdminNotFoundException, PropietarioNotFoundException {
        try {
            // Buscar al administrador y al propietario por sus IDs utilizando los servicios correspondientes
            Admin admin = adminServiceImpl.getAdminById(adminid);
            Propietario propietario = propietarioServiceImpl.getPropietarioById(propietarioid);

            // Convertir el DTO de mascota a entidad Mascota
            Mascota mascota = convertToEntity(mascotaDTO);

            // Establecer la relación con el administrador y el propietario
            mascota.setAdmin(admin);
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


    @PutMapping("/{id_admin}/modifymascota/{id_mas}") // Cambia la ruta para reflejar que estás modificando una mascota
    public MascotaDTO modifyMascota(@PathVariable Long id_admin,
                                    @PathVariable Long id_mas,
                                    @RequestBody MascotaDTO mascotaDTO) throws AdminNotFoundException {
        // Busca el administrador por ID
        Admin admin = adminService.getAdminById(id_admin);

        // Verifica si el administrador tiene permisos para modificar la mascota (puedes agregar lógica de autorización aquí)

        // Busca la mascota por ID
        Mascota mascota = mascotaService.getMascotaById(id_mas);

        // Actualiza los campos de la mascota con los valores proporcionados en mascotaDTO
        mascota.setNombre_mas(mascotaDTO.getNombre_mas());
        mascota.setRaza_mas(mascotaDTO.getRaza_mas());
        mascota.setEdad_mas(mascotaDTO.getEdad_mas());

        // Guarda la mascota actualizada en el repositorio de mascotas
        mascota = mascotaService.register(mascota);

        // Puedes convertir la mascota actualizada a un DTO y devolverla como respuesta
        return convertToDto(mascota);
    }

    private PaseadorDTO convertToDto(Paseador paseador) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(paseador, PaseadorDTO.class);
    }

    private Paseador convertToEntity(PaseadorDTO paseadorDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(paseadorDTO, Paseador.class);
    }

    private MascotaDTO convertToDto(Mascota mascota) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mascota, MascotaDTO.class);
    }

    private Mascota convertToEntity(MascotaDTO mascotaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mascotaDTO, Mascota.class);
    }

    private PropietarioDTO convertToDto(Propietario propietario) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(propietario, PropietarioDTO.class);
    }

    private Propietario convertToEntity(PropietarioDTO propietarioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(propietarioDTO, Propietario.class);
    }

    private AdminDTO convertToDto(Admin admin) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(admin, AdminDTO.class);
    }

    private Admin convertToEntity(AdminDTO adminDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(adminDTO, Admin.class);
    }

    private List<AdminDTO> convertToLisDto(List<Admin> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
