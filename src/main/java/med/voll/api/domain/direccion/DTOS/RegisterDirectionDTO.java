package med.voll.api.domain.direccion.DTOS;

import jakarta.validation.constraints.NotBlank;

public record RegisterDirectionDTO(

        @NotBlank
        String calle,

        @NotBlank
        String distrito,

        @NotBlank
        String ciudad,

        @NotBlank
        String numero,

        @NotBlank
        String complemento
) { }
