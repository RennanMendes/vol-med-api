package med.vol.api.domain.validations;

import med.vol.api.domain.dto.appointment.AppointmentDTO;
import med.vol.api.domain.exception.ExceptionValidation;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class OpeningHoursValidator implements AppointmentSchedulingValidator {

    public void validate(AppointmentDTO data) {
        var appointmentDate = data.date();
        Boolean sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        Boolean beforeOpening = appointmentDate.getHour() < 7;
        Boolean afterClosing = appointmentDate.getHour() > 18;

        if (sunday || beforeOpening || afterClosing) {
            throw new ExceptionValidation("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
