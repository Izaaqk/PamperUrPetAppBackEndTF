package pe.pamperurpet.pamperurpetapp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PropietarioDTO {
    private Long propietarioid;
    private String nombreapellido_prop;
    private String telefono_prop;
    private String correo_prop;
    private Long contraseña_prop;
    private MembresiaDTO membresiaDTO;

    public PropietarioDTO(Long propietarioid, String nombreapellido_prop, String telefono_prop, String correo_prop, Long contraseña_prop) {
        this.propietarioid = propietarioid;
        this.nombreapellido_prop = nombreapellido_prop;
        this.telefono_prop = telefono_prop;
        this.correo_prop = correo_prop;
        this.contraseña_prop = contraseña_prop;
    }

    public Long getPropietarioid() {
        return propietarioid;
    }

    public void setPropietarioid(Long propietarioid) {
        this.propietarioid = propietarioid;
    }

    public String getNombreapellido_prop() {
        return nombreapellido_prop;
    }

    public void setNombreapellido_prop(String nombreapellido_prop) {
        this.nombreapellido_prop = nombreapellido_prop;
    }

    public String getTelefono_prop() {
        return telefono_prop;
    }

    public void setTelefono_prop(String telefono_prop) {
        this.telefono_prop = telefono_prop;
    }

    public String getCorreo_prop() {
        return correo_prop;
    }

    public void setCorreo_prop(String correo_prop) {
        this.correo_prop = correo_prop;
    }

    public Long getContraseña_prop() {
        return contraseña_prop;
    }

    public void setContraseña_prop(Long contraseña_prop) {
        this.contraseña_prop = contraseña_prop;
    }

    @Override
    public String toString() {
        return "PropietarioDTO{" +
                "propietarioid=" + propietarioid +
                ", nombreapellido_prop='" + nombreapellido_prop + '\'' +
                ", telefono_prop='" + telefono_prop + '\'' +
                ", correo_prop='" + correo_prop + '\'' +
                ", contraseña_prop=" + contraseña_prop +
                '}';
    }
}
