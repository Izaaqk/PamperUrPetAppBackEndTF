package pe.pamperurpet.pamperurpetapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pago;
    private String nombreapellido_pago;
    private String correo_pago;
    private String direccion_pago;
    private String distrito_pago;
    private Long telefono_pago;
    private String raza_pago;
    private String tarjeta_pago;
    private String propietario_pago;
    private Long numTarjeta_pago;
    private String mesexpiracion_pago;
    private Long añoexpiracion_pago;
    private Long cvv_pago;

    @OneToMany(mappedBy = "pago", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_membresia")
    private Membresia membresia;

    public Pago(Long id_pago, String nombreapellido_pago, String correo_pago, String direccion_pago, String distrito_pago, Long telefono_pago, String raza_pago, String tarjeta_pago, String propietario_pago, Long numTarjeta_pago, String mesexpiracion_pago, Long añoexpiracion_pago, Long cvv_pago) {
        this.id_pago = id_pago;
        this.nombreapellido_pago = nombreapellido_pago;
        this.correo_pago = correo_pago;
        this.direccion_pago = direccion_pago;
        this.distrito_pago = distrito_pago;
        this.telefono_pago = telefono_pago;
        this.raza_pago = raza_pago;
        this.tarjeta_pago = tarjeta_pago;
        this.propietario_pago = propietario_pago;
        this.numTarjeta_pago = numTarjeta_pago;
        this.mesexpiracion_pago = mesexpiracion_pago;
        this.añoexpiracion_pago = añoexpiracion_pago;
        this.cvv_pago = cvv_pago;
    }

    public Long getId_pago() {
        return id_pago;
    }

    public void setId_pago(Long id_pago) {
        this.id_pago = id_pago;
    }

    public String getNombreapellido_pago() {
        return nombreapellido_pago;
    }

    public void setNombreapellido_pago(String nombreapellido_pago) {
        this.nombreapellido_pago = nombreapellido_pago;
    }

    public String getCorreo_pago() {
        return correo_pago;
    }

    public void setCorreo_pago(String correo_pago) {
        this.correo_pago = correo_pago;
    }

    public String getDireccion_pago() {
        return direccion_pago;
    }

    public void setDireccion_pago(String direccion_pago) {
        this.direccion_pago = direccion_pago;
    }

    public String getDistrito_pago() {
        return distrito_pago;
    }

    public void setDistrito_pago(String distrito_pago) {
        this.distrito_pago = distrito_pago;
    }

    public Long getTelefono_pago() {
        return telefono_pago;
    }

    public void setTelefono_pago(Long telefono_pago) {
        this.telefono_pago = telefono_pago;
    }

    public String getRaza_pago() {
        return raza_pago;
    }

    public void setRaza_pago(String raza_pago) {
        this.raza_pago = raza_pago;
    }

    public String getTarjeta_pago() {
        return tarjeta_pago;
    }

    public void setTarjeta_pago(String tarjeta_pago) {
        this.tarjeta_pago = tarjeta_pago;
    }

    public String getPropietario_pago() {
        return propietario_pago;
    }

    public void setPropietario_pago(String propietario_pago) {
        this.propietario_pago = propietario_pago;
    }

    public Long getNumTarjeta_pago() {
        return numTarjeta_pago;
    }

    public void setNumTarjeta_pago(Long numTarjeta_pago) {
        this.numTarjeta_pago = numTarjeta_pago;
    }

    public String getMesexpiracion_pago() {
        return mesexpiracion_pago;
    }

    public void setMesexpiracion_pago(String mesexpiracion_pago) {
        this.mesexpiracion_pago = mesexpiracion_pago;
    }

    public Long getAñoexpiracion_pago() {
        return añoexpiracion_pago;
    }

    public void setAñoexpiracion_pago(Long añoexpiracion_pago) {
        this.añoexpiracion_pago = añoexpiracion_pago;
    }

    public Long getCvv_pago() {
        return cvv_pago;
    }

    public void setCvv_pago(Long cvv_pago) {
        this.cvv_pago = cvv_pago;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "id_pago=" + id_pago +
                ", nombreapellido_pago='" + nombreapellido_pago + '\'' +
                ", correo_pago='" + correo_pago + '\'' +
                ", direccion_pago='" + direccion_pago + '\'' +
                ", distrito_pago='" + distrito_pago + '\'' +
                ", telefono_pago=" + telefono_pago +
                ", raza_pago='" + raza_pago + '\'' +
                ", tarjeta_pago='" + tarjeta_pago + '\'' +
                ", propietario_pago='" + propietario_pago + '\'' +
                ", numTarjeta_pago=" + numTarjeta_pago +
                ", mesexpiracion_pago='" + mesexpiracion_pago + '\'' +
                ", añoexpiracion_pago=" + añoexpiracion_pago +
                ", cvv_pago=" + cvv_pago +
                '}';
    }
}
