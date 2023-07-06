package med.vol.api.dto.doctor;

import med.vol.api.model.Address;
import med.vol.api.model.Doctor;
import med.vol.api.model.Specialty;

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
