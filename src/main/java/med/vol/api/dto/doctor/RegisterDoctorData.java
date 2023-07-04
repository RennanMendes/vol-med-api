package med.vol.api.dto.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.dto.adress.AddressDTO;
import med.vol.api.model.Specialty;

public record RegisterDoctorData(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotBlank
        String phone,

        @NotNull
        Specialty specialty,
        @NotNull
        @Valid
        AddressDTO address) {
}
