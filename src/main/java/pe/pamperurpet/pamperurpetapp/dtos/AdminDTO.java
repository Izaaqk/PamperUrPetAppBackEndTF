package pe.pamperurpet.pamperurpetapp.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Data
public class AdminDTO {
    private Long id_admin;
    private PropietarioDTO propietarioDTO;
    private PaseadorDTO paseadorDTO;
    private MascotaDTO mascotaDTO;

    public AdminDTO(Long id_admin) {
        this.id_admin = id_admin;
    }

    public Long getId_admin() {
        return id_admin;
    }

    public void setId_admin(Long id_admin) {
        this.id_admin = id_admin;
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
                "id_admin=" + id_admin +
                '}';
    }
}
