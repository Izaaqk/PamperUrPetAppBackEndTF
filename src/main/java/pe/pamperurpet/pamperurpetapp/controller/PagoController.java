package pe.pamperurpet.pamperurpetapp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.pamperurpet.pamperurpetapp.dtos.MembresiaDTO;
import pe.pamperurpet.pamperurpetapp.dtos.PagoDTO;
import pe.pamperurpet.pamperurpetapp.entities.Membresia;
import pe.pamperurpet.pamperurpetapp.entities.Pago;
import pe.pamperurpet.pamperurpetapp.interfaceservice.PagoService;
import pe.pamperurpet.pamperurpetapp.repositories.PagoRepository;
import pe.pamperurpet.pamperurpetapp.services.MembresiaServiceImpl;
import pe.pamperurpet.pamperurpetapp.services.PagoServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "http://18.216.74.240"})
public class PagoController {
    @Autowired //inyectando
    private PagoService pagoService;
    @Autowired //inyectando
    private PagoServiceImpl pagoServiceImpl;

    @Autowired //inyectando
    private PagoRepository pagoRepository;

    @Autowired //inyectando
    private MembresiaServiceImpl membresiaServiceImpl;

    @PostMapping("/pago")
    public ResponseEntity<PagoDTO> register(@RequestBody PagoDTO pagoDTO){
        Pago pago = convertToEntity(pagoDTO);
        pago = pagoServiceImpl.register(pago);
        pagoDTO = convertToDto(pago);
        return new ResponseEntity<PagoDTO>(pagoDTO, HttpStatus.OK);
    }

    @PostMapping("/pago/membresia")
    public ResponseEntity<PagoDTO> registerWithMembresia(@RequestBody PagoDTO pagoDTO) {
        try {
            // Convertir PagoDTO a entidad Pago
            Pago pago = new Pago();
            pago.setId_pago(pagoDTO.getId_pago());
            pago.setNombreapellido_pago(pagoDTO.getNombreapellido_pago());
            pago.setCorreo_pago(pagoDTO.getCorreo_pago());
            pago.setDireccion_pago(pagoDTO.getDireccion_pago());
            pago.setDistrito_pago(pagoDTO.getDistrito_pago());
            pago.setTelefono_pago(pagoDTO.getTelefono_pago());
            pago.setRaza_pago(pagoDTO.getRaza_pago());
            pago.setTarjeta_pago(pagoDTO.getTarjeta_pago());
            pago.setPropietario_pago(pagoDTO.getPropietario_pago());
            pago.setNumTarjeta_pago(pagoDTO.getNumTarjeta_pago());
            pago.setMesexpiracion_pago(pagoDTO.getMesexpiracion_pago());
            pago.setAñoexpiracion_pago(pagoDTO.getAñoexpiracion_pago());
            pago.setCvv_pago(pagoDTO.getCvv_pago());

            // Crear la entidad Membresia a partir de MembresiaDTO
            Membresia membresia = new Membresia();
            membresia.setId_memb(pagoDTO.getMembresiaDTO().getId_memb());
            membresia.setNumerotarjeta_memb(pagoDTO.getMembresiaDTO().getNumerotarjeta_memb());
            membresia.setFechavencimiento_memb(pagoDTO.getMembresiaDTO().getFechavencimiento_memb());
            membresia.setCvv_memb(pagoDTO.getMembresiaDTO().getCvv_memb());
            membresia.setNombrecompleto_memb(pagoDTO.getMembresiaDTO().getNombrecompleto_memb());
            membresia.setMembresiaelegida_memb(pagoDTO.getMembresiaDTO().getMembresiaelegida_memb());
            membresia.setMontopagar_memb(pagoDTO.getMembresiaDTO().getMontopagar_memb());

            // Establecer la relación entre Pago y Membresia
            pago.setMembresia(membresia);

            // Guardar Pago en la base de datos
            pago = pagoRepository.save(pago);

            // Convertir el resultado a PagoDTO
            PagoDTO resultDTO = new PagoDTO();
            resultDTO.setId_pago(pago.getId_pago());
            resultDTO.setNombreapellido_pago(pago.getNombreapellido_pago());
            resultDTO.setCorreo_pago(pago.getCorreo_pago());
            resultDTO.setDireccion_pago(pago.getDireccion_pago());
            resultDTO.setDistrito_pago(pago.getDistrito_pago());
            resultDTO.setTelefono_pago(pago.getTelefono_pago());
            resultDTO.setRaza_pago(pago.getRaza_pago());
            resultDTO.setTarjeta_pago(pago.getTarjeta_pago());
            resultDTO.setPropietario_pago(pago.getPropietario_pago());
            resultDTO.setNumTarjeta_pago(pago.getNumTarjeta_pago());
            resultDTO.setMesexpiracion_pago(pago.getMesexpiracion_pago());
            resultDTO.setAñoexpiracion_pago(pago.getAñoexpiracion_pago());
            resultDTO.setCvv_pago(pago.getCvv_pago());

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

    @PostMapping("/pago/{id_memb}")
    public ResponseEntity<PagoDTO> registerWithMembresia(@PathVariable Long id_memb, @RequestBody PagoDTO pagoDTO) {
        try {
            // Buscar la membresía por su ID utilizando el servicio de membresía (ajusta el nombre del servicio según corresponda)
            Membresia membresia = membresiaServiceImpl.getMembresiaById(id_memb);

            // Convertir el DTO de pago a entidad Pago
            Pago pago = convertToEntity(pagoDTO);

            // Establecer la relación con la membresía
            pago.setMembresia(membresia);

            // Registrar el pago
            pago = pagoService.register(pago);

            // Convertir la entidad pago a DTO
            pagoDTO = convertToDto(pago);

            return new ResponseEntity<PagoDTO>(pagoDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    private PagoDTO convertToDto(Pago pago) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pago, PagoDTO.class);
    }

    private Pago convertToEntity(PagoDTO pagoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pagoDTO, Pago.class);
    }

    private List<PagoDTO> convertToLisDto(List<Pago> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
