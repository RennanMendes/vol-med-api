package med.vol.api.dto.doctor;

import jakarta.validation.constraints.NotNull;
import med.vol.api.dto.adress.AddressDTO;

public record UpdateDoctor(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDTO address) {


}
