package med.vol.api.domain.dto.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.model.Doctor;
import med.vol.api.domain.model.Patient;
import med.vol.api.domain.model.Specialty;

import java.time.LocalDateTime;

public record AppointmentDTO(
        Long idDoctor,
        @NotNull
        Long idPatient,

        @NotNull
        @Future
        LocalDateTime date,

        Specialty specialty) {
}
