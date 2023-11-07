package pe.pamperurpet.pamperurpetapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reser;
    private String fechainicio_reser;
    private String fechafin_reser;
    private Long numeromascotas_mas;
    private Long id_pas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_propietario_id")
    private Propietario propietario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_paseador_id")
    private Paseador paseador;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_pago_id")
    private Pago pago;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_mascota_id")
    private Mascota mascota;

    public Reserva(Long id_reser, String fechainicio_reser, String fechafin_reser, Long numeromascotas_mas, Long id_pas) {
        this.id_reser = id_reser;
        this.fechainicio_reser = fechainicio_reser;
        this.fechafin_reser = fechafin_reser;
        this.numeromascotas_mas = numeromascotas_mas;
        this.id_pas = id_pas;
    }

    public Long getId_reser() {
        return id_reser;
    }

    public void setId_reser(Long id_reser) {
        this.id_reser = id_reser;
    }

    public String getFechainicio_reser() {
        return fechainicio_reser;
    }

    public void setFechainicio_reser(String fechainicio_reser) {
        this.fechainicio_reser = fechainicio_reser;
    }

    public String getFechafin_reser() {
        return fechafin_reser;
    }

    public void setFechafin_reser(String fechafin_reser) {
        this.fechafin_reser = fechafin_reser;
    }

    public Long getNumeromascotas_mas() {
        return numeromascotas_mas;
    }

    public void setNumeromascotas_mas(Long numeromascotas_mas) {
        this.numeromascotas_mas = numeromascotas_mas;
    }

    public Long getId_pas() {
        return id_pas;
    }

    public void setId_pas(Long id_pas) {
        this.id_pas = id_pas;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id_reser=" + id_reser +
                ", fechainicio_reser='" + fechainicio_reser + '\'' +
                ", fechafin_reser='" + fechafin_reser + '\'' +
                ", numeromascotas_mas=" + numeromascotas_mas +
                ", id_pas=" + id_pas +
                '}';
    }
}
