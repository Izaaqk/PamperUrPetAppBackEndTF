package pe.pamperurpet.pamperurpetapp.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Transactional
@NoArgsConstructor
@Entity
@Table(name = "Admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id_admin;
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<Propietario> propietarios = new HashSet<>();

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<Paseador> paseadores = new HashSet<>();

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<Mascota> mascotas = new HashSet<>();

    public Admin(Long id_admin) {
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
        return "Admin{" +
                "id_admin=" + id_admin +
                '}';
    }
}
