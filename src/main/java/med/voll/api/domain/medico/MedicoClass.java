package med.voll.api.domain.medico;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;
import med.voll.api.domain.medico.DTOS.RegisterDataMedicDTO;
import med.voll.api.domain.medico.DTOS.UpdateInfoMedicDTO;
import med.voll.api.domain.medico.ENUMS.EspecialidadEnum;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MedicoClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Boolean activo;
    private @NotBlank @Pattern(regexp = "\\d{4,50}") String documento;

    @Enumerated(EnumType.STRING)
    private EspecialidadEnum especialidad;

    @Embedded
    private Direccion direccion;

    public MedicoClass(RegisterDataMedicDTO datosMedico) {
        this.activo = true;
        this.nombre = datosMedico.nombre();
        this.apellido = datosMedico.apellido();
        this.email = datosMedico.email();
        this.telefono = datosMedico.telefono();
        this.documento = datosMedico.documento();
        this.especialidad = datosMedico.especialidad();
        this.direccion = new Direccion(datosMedico.direccion());
    }

    public void updateInfo(UpdateInfoMedicDTO updateMedic) {

        if(updateMedic.nombre() != null) {
            this.nombre = updateMedic.nombre();
        }

        if(updateMedic.apellido() != null) {
            this.apellido = updateMedic.apellido();
        }

        if(updateMedic.documento() != null) {
            this.documento = updateMedic.documento();
        }

        if(updateMedic.telefono() != null) {
            this.telefono = updateMedic.telefono();
        }

        if(updateMedic.direccion() != null) {
            this.direccion = direccion.updateDirection(updateMedic.direccion());
        }
    }

    public void changeStatusMedic() {
        this.activo = false;
    }
}
