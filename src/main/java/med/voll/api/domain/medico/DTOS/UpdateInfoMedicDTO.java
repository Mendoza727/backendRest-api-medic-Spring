package med.voll.api.domain.medico.DTOS;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DTOS.RegisterDirectionDTO;

public record UpdateInfoMedicDTO(

        @NotNull
        Long id,
        String nombre,
        String apellido,
        String telefono,
        String documento,
        RegisterDirectionDTO direccion
) {
}
