package med.vol.api.domain.validations;

import med.vol.api.domain.dto.appointment.AppointmentDTO;
import med.vol.api.domain.exception.ExceptionValidation;
import med.vol.api.domain.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMedicalValidator implements AppointmentSchedulingValidator {

    @Autowired
    private DoctorRepository repository;

    public void validate(AppointmentDTO data) {
        if (data.idDoctor() == null) {
            return;
        }

        var doctorIsActive = repository.findActiveById(data.idDoctor());
        if (!doctorIsActive) {
            throw new ExceptionValidation("Consulta não pode ser agendado com médico excluído");
        }

    }
}
