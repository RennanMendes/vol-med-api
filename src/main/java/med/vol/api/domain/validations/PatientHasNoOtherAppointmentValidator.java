package med.vol.api.domain.validations;

import med.vol.api.domain.dto.appointment.AppointmentDTO;
import med.vol.api.domain.exception.ExceptionValidation;
import med.vol.api.domain.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientHasNoOtherAppointmentValidator implements AppointmentSchedulingValidator {

    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentDTO data) {
        var firstHour = data.date().withHour(7);
        var lastHour = data.date().withHour(18);
        var hasAnotherAppointment = repository.existsByPatientIdAndDateBetween(data.idPatient(), firstHour, lastHour);
        if (hasAnotherAppointment) {
            throw new ExceptionValidation("Paciente já possui uma consulta neste dia");
        }

    }

}
