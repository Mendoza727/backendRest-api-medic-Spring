package med.voll.api.domain.medico.DTOS;

import med.voll.api.domain.medico.MedicoClass;

public record GetDataMedicDTO(

        Long id,
        String nombre,
        String apellido,
        String especialidad,
        String email,
        String documento

) {

    public GetDataMedicDTO(MedicoClass medico) {
        this(
                medico.getId(),
                medico.getNombre(),
                medico.getApellido(),
                medico.getEspecialidad().toString(),
                medico.getEmail(),
                medico.getDocumento()
        );
    }
}
