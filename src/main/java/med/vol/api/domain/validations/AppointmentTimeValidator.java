package med.vol.api.domain.validations;

import med.vol.api.domain.dto.appointment.AppointmentDTO;
import med.vol.api.domain.exception.ExceptionValidation;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AppointmentTimeValidator implements AppointmentSchedulingValidator {

    public void validate(AppointmentDTO data) {
        var appointmentDate = data.date();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, appointmentDate).toMinutes();

        if (differenceInMinutes < 30) {
            throw new ExceptionValidation("Consulta deve ser agendada com antecedência minima de 30min");
        }
    }

}
