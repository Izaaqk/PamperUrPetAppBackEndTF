package pe.pamperurpet.pamperurpetapp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.pamperurpet.pamperurpetapp.dtos.AdminDTO;
import pe.pamperurpet.pamperurpetapp.entities.Admin;
import pe.pamperurpet.pamperurpetapp.exceptions.AdminNotFoundException;
import pe.pamperurpet.pamperurpetapp.interfaceservice.AdminService;
import pe.pamperurpet.pamperurpetapp.repositories.AdminRepository;
import pe.pamperurpet.pamperurpetapp.repositories.MascotaRepository;
import pe.pamperurpet.pamperurpetapp.repositories.PaseadorRepository;
import pe.pamperurpet.pamperurpetapp.repositories.PropietarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PropietarioRepository propietarioRepository;
    @Autowired
    private PaseadorRepository paseadorRepository;
    @Autowired
    private MascotaRepository mascotaRepository;

    public Admin register(Admin admin){
        return adminRepository.save(admin);
    }

    public List<AdminDTO> listAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletePaseadorFromAdmin(Long id_admin) throws AdminNotFoundException {
        // Busca el Admin por ID
        Optional<Admin> adminOptional = adminRepository.findById(id_admin);

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();

            // Establece el paseador en null
            admin.setPaseadores(null);

            // Guarda el Admin actualizado en la base de datos
            adminRepository.save(admin);
        } else {
            throw new AdminNotFoundException("No se encontró el Admin con ID " + id_admin);
        }
    }

    @Override
    public Admin getAdminById(Long id_admin) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id_admin);

        if (optionalAdmin.isPresent()) {
            return optionalAdmin.get();
        } else {
            try {
                throw new AdminNotFoundException("No se encontró un propietario con el ID proporcionado");
            } catch (AdminNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }




    private List<AdminDTO> convertToLisDto(List<Admin> admins){
        return admins.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private AdminDTO convertToDto(Admin admin) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(admin, AdminDTO.class);
    }
}
