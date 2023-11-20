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
@Table(name = "Mascota")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mas;
    private String nombre_mas;
    private String raza_mas;
    private Long edad_mas;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY) // Una mascota pertenece a un propietario
    @JoinColumn(name = "fk_propietario_id") // Nombre de la columna de la clave foránea
    private Propietario propietario;

    @ManyToOne(fetch = FetchType.LAZY) // Una mascota pertenece a un propietario
    @JoinColumn(name = "id_pas") // Nombre de la columna de la clave foránea
    private Paseador paseador;

    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();

    public Mascota(Long id_mas, String nombre_mas, String raza_mas, Long edad_mas) {
        this.id_mas = id_mas;
        this.nombre_mas = nombre_mas;
        this.raza_mas = raza_mas;
        this.edad_mas = edad_mas;
    }

    public Long getId_mas() {
        return id_mas;
    }

    public void setId_mas(Long id_mas) {
        this.id_mas = id_mas;
    }

    public String getNombre_mas() {
        return nombre_mas;
    }

    public void setNombre_mas(String nombre_mas) {
        this.nombre_mas = nombre_mas;
    }

    public String getRaza_mas() {
        return raza_mas;
    }

    public void setRaza_mas(String raza_mas) {
        this.raza_mas = raza_mas;
    }

    public Long getEdad_mas() {
        return edad_mas;
    }

    public void setEdad_mas(Long edad_mas) {
        this.edad_mas = edad_mas;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "id_mas=" + id_mas +
                ", nombre_mas='" + nombre_mas + '\'' +
                ", raza_mas='" + raza_mas + '\'' +
                ", edad_mas=" + edad_mas +
                '}';
    }
}
