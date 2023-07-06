package med.vol.api.domain.dto.patient;

import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.dto.adress.AddressDTO;

public record UpdatePatientDTO(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDTO address) {
}
