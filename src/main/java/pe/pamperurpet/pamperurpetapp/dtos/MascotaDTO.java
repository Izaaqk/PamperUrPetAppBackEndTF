package pe.pamperurpet.pamperurpetapp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MascotaDTO {
    private Long id_mas;
    private String nombre_mas;
    private String raza_mas;
    private Long edad_mas;


    public MascotaDTO(Long id_mas, String nombre_mas, String raza_mas, Long edad_mas) {
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
        return "MascotaDTO{" +
                "id_mas=" + id_mas +
                ", nombre_mas='" + nombre_mas + '\'' +
                ", raza_mas='" + raza_mas + '\'' +
                ", edad_mas=" + edad_mas +
                '}';
    }
}
