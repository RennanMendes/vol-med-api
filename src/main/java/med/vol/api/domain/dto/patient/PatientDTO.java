package med.vol.api.domain.dto.patient;

import med.vol.api.domain.model.Address;
import med.vol.api.domain.model.Patient;

public record PatientDTO(
        Long id,
        String name,
        String email,
        String phone,
        String cpf,
        Address address
        ) {

        public PatientDTO(Patient patient){
                this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(), patient.getAddress());
        }
}
