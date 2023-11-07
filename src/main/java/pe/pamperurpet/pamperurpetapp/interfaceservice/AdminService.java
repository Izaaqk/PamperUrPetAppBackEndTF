package pe.pamperurpet.pamperurpetapp.interfaceservice;

import pe.pamperurpet.pamperurpetapp.entities.Admin;
import pe.pamperurpet.pamperurpetapp.exceptions.AdminNotFoundException;

public interface AdminService {
    public void deletePaseadorFromAdmin(Long id_admin) throws AdminNotFoundException;

    Admin getAdminById(Long id_admin);
}
