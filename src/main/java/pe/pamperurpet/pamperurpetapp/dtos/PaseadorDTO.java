package pe.pamperurpet.pamperurpetapp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PaseadorDTO {
    private Long id_pas;
    private String nombreapellido_pas;
    private String telefono_pas;
    private String correo_pas;
    private Long contraseña_pas;

    public PaseadorDTO(Long id_pas, String nombreapellido_pas, String telefono_pas, String correo_pas, Long contraseña_pas) {
        this.id_pas = id_pas;
        this.nombreapellido_pas = nombreapellido_pas;
        this.telefono_pas = telefono_pas;
        this.correo_pas = correo_pas;
        this.contraseña_pas = contraseña_pas;
    }

    public Long getId_pas() {
        return id_pas;
    }

    public void setId_pas(Long id_pas) {
        this.id_pas = id_pas;
    }

    public String getNombreapellido_pas() {
        return nombreapellido_pas;
    }

    public void setNombreapellido_pas(String nombreapellido_pas) {
        this.nombreapellido_pas = nombreapellido_pas;
    }

    public String getTelefono_pas() {
        return telefono_pas;
    }

    public void setTelefono_pas(String telefono_pas) {
        this.telefono_pas = telefono_pas;
    }

    public String getCorreo_pas() {
        return correo_pas;
    }

    public void setCorreo_pas(String correo_pas) {
        this.correo_pas = correo_pas;
    }

    public Long getContraseña_pas() {
        return contraseña_pas;
    }

    public void setContraseña_pas(Long contraseña_pas) {
        this.contraseña_pas = contraseña_pas;
    }

    @Override
    public String toString() {
        return "PaseadorDTO{" +
                "id_pas=" + id_pas +
                ", nombreapellido_pas='" + nombreapellido_pas + '\'' +
                ", telefono_pas='" + telefono_pas + '\'' +
                ", correo_pas='" + correo_pas + '\'' +
                ", contraseña_pas=" + contraseña_pas +
                '}';
    }
}
