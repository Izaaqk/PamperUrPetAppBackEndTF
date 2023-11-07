package pe.pamperurpet.pamperurpetapp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MembresiaDTO {
    private Long id_memb;
    private Long numerotarjeta_memb;
    private String fechavencimiento_memb;
    private Long cvv_memb;
    private String nombrecompleto_memb;
    private String membresiaelegida_memb;
    private double montopagar_memb;

    public MembresiaDTO(Long id_memb, Long numerotarjeta_memb, String fechavencimiento_memb, Long cvv_memb, String nombrecompleto_memb, String membresiaelegida_memb, double montopagar_memb) {
        this.id_memb = id_memb;
        this.numerotarjeta_memb = numerotarjeta_memb;
        this.fechavencimiento_memb = fechavencimiento_memb;
        this.cvv_memb = cvv_memb;
        this.nombrecompleto_memb = nombrecompleto_memb;
        this.membresiaelegida_memb = membresiaelegida_memb;
        this.montopagar_memb = montopagar_memb;
    }

    public Long getId_memb() {
        return id_memb;
    }

    public void setId_memb(Long id_memb) {
        this.id_memb = id_memb;
    }

    public Long getNumerotarjeta_memb() {
        return numerotarjeta_memb;
    }

    public void setNumerotarjeta_memb(Long numerotarjeta_memb) {
        this.numerotarjeta_memb = numerotarjeta_memb;
    }

    public String getFechavencimiento_memb() {
        return fechavencimiento_memb;
    }

    public void setFechavencimiento_memb(String fechavencimiento_memb) {
        this.fechavencimiento_memb = fechavencimiento_memb;
    }

    public Long getCvv_memb() {
        return cvv_memb;
    }

    public void setCvv_memb(Long cvv_memb) {
        this.cvv_memb = cvv_memb;
    }

    public String getNombrecompleto_memb() {
        return nombrecompleto_memb;
    }

    public void setNombrecompleto_memb(String nombrecompleto_memb) {
        this.nombrecompleto_memb = nombrecompleto_memb;
    }

    public String getMembresiaelegida_memb() {
        return membresiaelegida_memb;
    }

    public void setMembresiaelegida_memb(String membresiaelegida_memb) {
        this.membresiaelegida_memb = membresiaelegida_memb;
    }

    public double getMontopagar_memb() {
        return montopagar_memb;
    }

    public void setMontopagar_memb(double montopagar_memb) {
        this.montopagar_memb = montopagar_memb;
    }

    @Override
    public String toString() {
        return "MembresiaDTO{" +
                "id_memb=" + id_memb +
                ", numerotarjeta_memb=" + numerotarjeta_memb +
                ", fechavencimiento_memb='" + fechavencimiento_memb + '\'' +
                ", cvv_memb=" + cvv_memb +
                ", nombrecompleto_memb='" + nombrecompleto_memb + '\'' +
                ", membresiaelegida_memb='" + membresiaelegida_memb + '\'' +
                ", montopagar_memb=" + montopagar_memb +
                '}';
    }
}
