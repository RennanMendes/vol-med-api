package med.vol.api.domain.dto.doctor;

import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.dto.adress.AddressDTO;

public record UpdateDoctor(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDTO address) {


}
