package med.vol.api.domain.dto.doctor;

import med.vol.api.domain.model.Doctor;
import med.vol.api.domain.model.Specialty;

public record ListOfDoctor(
        Long id,
        String name,
        String email,
        String crm,
        Specialty specialty
) {

    public ListOfDoctor(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
