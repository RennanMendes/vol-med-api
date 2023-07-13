package med.vol.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.vol.api.domain.dto.patient.ListOfPatient;
import med.vol.api.domain.dto.patient.PatientDTO;
import med.vol.api.domain.dto.patient.UpdatePatientDTO;
import med.vol.api.domain.model.Patient;
import med.vol.api.domain.repository.PatientRepository;
import med.vol.api.domain.dto.patient.RegisterPatientData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patient")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity<PatientDTO> patient(@RequestBody @Valid RegisterPatientData patientData, UriComponentsBuilder uriBuilder) {
        Patient patient = new Patient(patientData);
        repository.save(patient);

        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientDTO(patient));
    }

    @GetMapping
    public ResponseEntity<Page<ListOfPatient>> listPatients(@PageableDefault(size = 10, sort = "name") Pageable pagination){
        var listOfPatient =  repository.findAllByActiveTrue(pagination).map(ListOfPatient::new);

        return ResponseEntity.ok(listOfPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> detail(@PathVariable Long id) {
        Patient patient = repository.getReferenceById(id);

        return ResponseEntity.ok(new PatientDTO(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientDTO> update(@RequestBody @Valid UpdatePatientDTO updatePatientDTO){
        Patient patient = repository.getReferenceById(updatePatientDTO.id());
        patient.updateData(updatePatientDTO);

        return ResponseEntity.ok(new PatientDTO(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        var patient = repository.getReferenceById(id);
        patient.delete(patient);

        return ResponseEntity.noContent().build();
    }


}
