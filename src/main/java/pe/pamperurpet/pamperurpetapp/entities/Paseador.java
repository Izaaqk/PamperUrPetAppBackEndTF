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
@Table(name = "Paseador")
public class Paseador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pas;
    @Column(name = "nombreapellido_pas", length = 50)
    private String nombreapellido_pas;
    @Column(name = "telefono_pas", length = 20)
    private String telefono_pas;
    @Column(name = "correo_pas", length = 35)
    private String correo_pas;
    private Long contraseña_pas;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @OneToMany(mappedBy = "paseador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();
    @OneToMany(mappedBy = "paseador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mascota> mascotas = new ArrayList<>();

    public Paseador(Long id_pas, String nombreapellido_pas, String telefono_pas, String correo_pas, Long contraseña_pas) {
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
        return "Paseador{" +
                "id_pas=" + id_pas +
                ", nombreapellido_pas='" + nombreapellido_pas + '\'' +
                ", telefono_pas='" + telefono_pas + '\'' +
                ", correo_pas='" + correo_pas + '\'' +
                ", contraseña_pas=" + contraseña_pas +
                '}';
    }
}
