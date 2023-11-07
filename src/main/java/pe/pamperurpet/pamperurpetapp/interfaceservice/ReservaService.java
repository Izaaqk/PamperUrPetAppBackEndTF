package pe.pamperurpet.pamperurpetapp.interfaceservice;

import org.springframework.transaction.annotation.Transactional;
import pe.pamperurpet.pamperurpetapp.dtos.ReservaDTO;
import pe.pamperurpet.pamperurpetapp.entities.Reserva;
import pe.pamperurpet.pamperurpetapp.exceptions.ReservaNotFoundException;

import java.util.List;

public interface ReservaService {
    @Transactional(rollbackFor = Exception.class)
    Reserva register(Reserva reserva);
    public List<ReservaDTO> listReservas();
    public Reserva updateDataReserva(Long id_reser, ReservaDTO nuevosDatos) throws ReservaNotFoundException;
    public ReservaDTO deleteReserva(Long id_reser) throws Exception;
}
