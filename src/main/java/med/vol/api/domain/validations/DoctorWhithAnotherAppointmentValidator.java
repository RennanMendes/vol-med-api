package med.vol.api.domain.validations;

import med.vol.api.domain.dto.appointment.AppointmentDTO;
import med.vol.api.domain.exception.ExceptionValidation;
import med.vol.api.domain.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWhithAnotherAppointmentValidator implements AppointmentSchedulingValidator {

    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentDTO data) {
        var hasAnotherAppointment = repository.existsByDoctorIdAndDate(data.idDoctor(), data.date());
        if (hasAnotherAppointment) {
            throw new ExceptionValidation("Médico já possui outra consulta neste horario");
        }
    }
}
