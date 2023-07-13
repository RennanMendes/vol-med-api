package med.vol.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.vol.api.domain.dto.doctor.ListOfDoctor;
import med.vol.api.domain.dto.doctor.RegisterDoctorData;
import med.vol.api.domain.dto.doctor.UpdateDoctor;
import med.vol.api.domain.dto.doctor.DoctorDTO;
import med.vol.api.domain.model.Doctor;
import med.vol.api.domain.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctor")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterDoctorData data, UriComponentsBuilder uriBuilder) {
        var doctor = new Doctor(data);
        repository.save(doctor);

        var uri = uriBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDTO(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<ListOfDoctor>> listDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var listOfDoctors = repository.findAllByActiveTrue(pagination).map(ListOfDoctor::new);

        return ResponseEntity.ok(listOfDoctors);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDTO> update(@RequestBody @Valid UpdateDoctor updateData) {
        var doctor = repository.getReferenceById(updateData.id());
        doctor.dataUpdate(updateData);

        return ResponseEntity.ok(new DoctorDTO(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delet();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> detail(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);

        return ResponseEntity.ok(new DoctorDTO(doctor));
    }

}
