package med.vol.api.dto.doctor;

import med.vol.api.model.Doctor;
import med.vol.api.model.Specialty;

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
