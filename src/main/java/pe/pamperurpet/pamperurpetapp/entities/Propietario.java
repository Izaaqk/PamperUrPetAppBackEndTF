package pe.pamperurpet.pamperurpetapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Transactional
@Table(name = "Propietario")
public class Propietario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "propietario_id")
    private Long propietarioid;
    @Column(name = "nombreapellido_prop", length = 50)
    private String nombreapellido_prop;
    @Column(name = "telefono_prop", length = 20)
    private String telefono_prop;
    @Column(name = "correo_prop", length = 35)
    private String correo_prop;
    private Long contraseña_prop;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mascota> mascotas = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_membresia")
    private Membresia membresia;
    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();

    public Propietario(Long propietarioid, String nombreapellido_prop, String telefono_prop, String correo_prop, Long contraseña_prop) {
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
        return "Propietario{" +
                "propietarioid=" + propietarioid +
                ", nombreapellido_prop='" + nombreapellido_prop + '\'' +
                ", telefono_prop='" + telefono_prop + '\'' +
                ", correo_prop='" + correo_prop + '\'' +
                ", contraseña_prop=" + contraseña_prop +
                '}';
    }
}
