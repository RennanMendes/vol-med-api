package med.vol.api.domain.dto.doctor;

import med.vol.api.domain.model.Address;
import med.vol.api.domain.model.Doctor;
import med.vol.api.domain.model.Specialty;

public record DoctorDTO(
        Long id,
        String name,
        String email,
        String crm,
        String phone,
        Specialty specialty,
        Address address
) {
    public DoctorDTO(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhone(), doctor.getSpecialty(), doctor.getAddress());
    }
}
