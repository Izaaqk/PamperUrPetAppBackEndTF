package pe.pamperurpet.pamperurpetapp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.pamperurpet.pamperurpetapp.dtos.ReservaDTO;
import pe.pamperurpet.pamperurpetapp.entities.Reserva;
import pe.pamperurpet.pamperurpetapp.exceptions.ReservaNotFoundException;
import pe.pamperurpet.pamperurpetapp.interfaceservice.ReservaService;
import pe.pamperurpet.pamperurpetapp.repositories.ReservaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Reserva register(Reserva reserva){
        return reservaRepository.save(reserva);
    }

    public List<ReservaDTO> listReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Reserva updateDataReserva(Long id_reser, ReservaDTO nuevosDatos) throws ReservaNotFoundException {
        Optional<Reserva> optionalReserva = reservaRepository.findById(id_reser);

        if (optionalReserva.isPresent()) {
            Reserva reserva = optionalReserva.get();

            // Actualiza los datos con los valores proporcionados en nuevosDatos
            reserva.setFechainicio_reser(nuevosDatos.getFechainicio_reser());
            reserva.setFechafin_reser(nuevosDatos.getFechafin_reser());
            reserva.setNumeromascotas_mas(nuevosDatos.getNumeromascotas_mas());
            reserva.setId_pas(nuevosDatos.getId_pas());

            // Guarda el postulante actualizado en la base de datos
            return reservaRepository.save(reserva);
        } else {
            // Si no se encuentra el postulante con el ID dado, puedes manejar el error de alguna manera, por ejemplo, lanzando una excepción.
            throw new ReservaNotFoundException("No se encontró una reserva con el ID proporcionado");
        }
    }

    public ReservaDTO deleteReserva(Long id_reser) throws Exception {
        Reserva reserva = reservaRepository.findById(id_reser)
                .orElseThrow(() -> new Exception("No se encontró entidad"));

        reservaRepository.delete(reserva);

        return convertToDto(reserva);
    }

    private List<ReservaDTO> convertToLisDto(List<Reserva> reservas){
        return reservas.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private ReservaDTO convertToDto(Reserva reserva) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reserva, ReservaDTO.class);
    }
}
