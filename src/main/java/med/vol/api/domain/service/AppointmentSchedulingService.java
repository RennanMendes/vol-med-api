package med.vol.api.domain.service;

import med.vol.api.domain.dto.appointment.AppointmentDTO;
import med.vol.api.domain.dto.appointment.AppointmentDetailDTO;
import med.vol.api.domain.exception.ExceptionValidation;
import med.vol.api.domain.model.Appointment;
import med.vol.api.domain.model.Doctor;
import med.vol.api.domain.repository.AppointmentRepository;
import med.vol.api.domain.repository.DoctorRepository;
import med.vol.api.domain.repository.PatientRepository;
import med.vol.api.domain.validations.AppointmentSchedulingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AppointmentSchedulingService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<AppointmentSchedulingValidator> validators;

    public AppointmentDetailDTO scheduling(AppointmentDTO data) {
        if (!patientRepository.existsById(data.idPatient())) {
            throw new ExceptionValidation("Id do paciente informado não existe!");
        }

        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ExceptionValidation("Id do médico informado não existe!");
        }

        validators.forEach(v -> v.validate(data));

        var doctor = chooseDoctor(data);

        if (doctor==null) {
            throw new ExceptionValidation("Não existe médico disponível nessa data");
        }

        var patient = patientRepository.findById(data.idPatient()).get();
        var appointment = new Appointment(null, doctor, patient, data.date());

        appointmentRepository.save(appointment);

        return new AppointmentDetailDTO(appointment);
    }

    private Doctor chooseDoctor(AppointmentDTO data) {
        if (data.idDoctor() != null) {
            return doctorRepository.getReferenceById(data.idDoctor());
        }

        if (data.specialty() == null) {
            throw new ExceptionValidation("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return doctorRepository.randomChoiceOfDoctor(data.specialty(), data.date());

    }

}
