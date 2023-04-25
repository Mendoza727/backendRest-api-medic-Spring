package med.voll.api.domain.medico.DTOS;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DTOS.RegisterDirectionDTO;
import med.voll.api.domain.medico.ENUMS.EspecialidadEnum;

public record RegisterDataMedicDTO(
        @NotBlank
        String nombre,
        @NotBlank
        String apellido,
        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefono,

        @NotBlank
        @Pattern( regexp = "\\d{4,50}")
        String documento,


        @NotNull
        EspecialidadEnum especialidad,

        @NotNull
        @Valid
        RegisterDirectionDTO direccion
) {

}
