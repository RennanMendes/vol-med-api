package med.vol.api.controller;


import jakarta.validation.Valid;
import med.vol.api.dto.patient.ListOfPatient;
import med.vol.api.dto.patient.UpdatePatientDTO;
import med.vol.api.model.Patient;
import med.vol.api.repository.PetientRepository;
import med.vol.api.dto.patient.RegisterPatientData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PetientRepository repository;
    @PostMapping
    @Transactional
    public void patient(@RequestBody @Valid RegisterPatientData patientData) {
        repository.save(new Patient(patientData));
    }

    @GetMapping
    public Page<ListOfPatient> listPatients(@PageableDefault(size = 10, sort = "name") Pageable pagination){
        return repository.findAllByActiveTrue(pagination).map(ListOfPatient::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdatePatientDTO updatePatientDTO){
        var patient = repository.getReferenceById(updatePatientDTO.id());
        patient.updateData(updatePatientDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        var patient = repository.getReferenceById(id);
        patient.delete(patient);
    }
}
