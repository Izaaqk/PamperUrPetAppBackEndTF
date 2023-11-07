package pe.pamperurpet.pamperurpetapp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.pamperurpet.pamperurpetapp.dtos.PagoDTO;
import pe.pamperurpet.pamperurpetapp.entities.Pago;
import pe.pamperurpet.pamperurpetapp.exceptions.PagoNotFoundException;
import pe.pamperurpet.pamperurpetapp.interfaceservice.PagoService;
import pe.pamperurpet.pamperurpetapp.repositories.PagoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagoServiceImpl implements PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    @Transactional(rollbackFor = Exception.class)
    public Pago register(Pago pago){
        return pagoRepository.save(pago);
    }

    private List<PagoDTO> convertToLisDto(List<Pago> pagos){
        return pagos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Pago getPagoById(Long id_pago) {
        Optional<Pago> optionalPago = pagoRepository.findById(id_pago);

        if (optionalPago.isPresent()) {
            return optionalPago.get();
        } else {
            try {
                throw new PagoNotFoundException("No se encontró un pago con el ID proporcionado");
            } catch (PagoNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private PagoDTO convertToDto(Pago pago) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pago, PagoDTO.class);
    }
}
