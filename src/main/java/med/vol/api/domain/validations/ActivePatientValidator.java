package med.vol.api.domain.validations;

import med.vol.api.domain.dto.appointment.AppointmentDTO;
import med.vol.api.domain.dto.patient.PatientDTO;
import med.vol.api.domain.exception.ExceptionValidation;
import med.vol.api.domain.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements AppointmentSchedulingValidator{

    @Autowired
    private PatientRepository repository;

    public void validate(AppointmentDTO data) {
        var patientIsActive = repository.findActiveById(data.idPatient());
        if (!patientIsActive) {
            throw new ExceptionValidation("Consulta não pode ser agendada com paciente excluído");
        }


    }

}
