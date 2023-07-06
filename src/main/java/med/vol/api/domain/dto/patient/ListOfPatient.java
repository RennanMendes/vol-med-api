package med.vol.api.domain.dto.patient;

import med.vol.api.domain.model.Patient;

public record ListOfPatient(Long id,String name, String email, String cpf) {

    public  ListOfPatient(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }

}
