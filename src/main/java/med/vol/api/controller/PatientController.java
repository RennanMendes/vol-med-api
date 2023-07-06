package med.vol.api.controller;


import jakarta.validation.Valid;
import med.vol.api.dto.doctor.DoctorDTO;
import med.vol.api.dto.patient.ListOfPatient;
import med.vol.api.dto.patient.PatientDOT;
import med.vol.api.dto.patient.UpdatePatientDTO;
import med.vol.api.model.Patient;
import med.vol.api.repository.PetientRepository;
import med.vol.api.dto.patient.RegisterPatientData;
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
public class PatientController {

    @Autowired
    private PetientRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity<PatientDOT> patient(@RequestBody @Valid RegisterPatientData patientData, UriComponentsBuilder uriBuilder) {
        Patient patient = new Patient(patientData);
        repository.save(patient);

        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientDOT(patient));
    }

    @GetMapping
    public ResponseEntity<Page<ListOfPatient>> listPatients(@PageableDefault(size = 10, sort = "name") Pageable pagination){
        var listOfPatient =  repository.findAllByActiveTrue(pagination).map(ListOfPatient::new);

        return ResponseEntity.ok(listOfPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDOT> detail(@PathVariable Long id) {
        Patient patient = repository.getReferenceById(id);

        return ResponseEntity.ok(new PatientDOT(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientDOT> update(@RequestBody @Valid UpdatePatientDTO updatePatientDTO){
        Patient patient = repository.getReferenceById(updatePatientDTO.id());
        patient.updateData(updatePatientDTO);

        return ResponseEntity.ok(new PatientDOT(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        var patient = repository.getReferenceById(id);
        patient.delete(patient);

        return ResponseEntity.noContent().build();
    }


}
