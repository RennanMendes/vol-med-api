package med.vol.api.dto.patient;

import jakarta.validation.constraints.NotNull;
import med.vol.api.dto.adress.AddressDTO;

public record UpdatePatientDTO(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDTO address) {
}
