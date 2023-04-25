package med.voll.api.domain.medico.DTOS;

import med.voll.api.domain.direccion.DTOS.RegisterDirectionDTO;

public record DataResponseMedicDTO(

        Long id,
        String nombre,
        String apellido,
        String documento,
        String telefono,
        RegisterDirectionDTO direccion
) { }
