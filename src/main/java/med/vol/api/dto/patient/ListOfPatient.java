package med.vol.api.dto.patient;

import med.vol.api.model.Patient;

public record ListOfPatient(Long id,String name, String email, String cpf) {

    public  ListOfPatient(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }

}
