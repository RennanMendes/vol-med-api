package med.vol.api.dto.patient;

import med.vol.api.model.Address;
import med.vol.api.model.Patient;

public record PatientDOT(
        Long id,
        String name,
        String email,
        String phone,
        String cpf,
        Address address
        ) {

        public PatientDOT(Patient patient){
                this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(), patient.getAddress());
        }
}
