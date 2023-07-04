package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.dto.doctor.ListOfDoctor;
import med.vol.api.dto.doctor.RegisterDoctorData;
import med.vol.api.dto.doctor.UpdateDoctor;
import med.vol.api.model.Doctor;
import med.vol.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid RegisterDoctorData data) {
        repository.save(new Doctor(data));
    }

    @GetMapping
    public Page<ListOfDoctor> listDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return repository.findAllByActiveTrue(pagination).map(ListOfDoctor::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateDoctor updateData) {
        var doctor = repository.getReferenceById(updateData.id());
        doctor.dataUpdate(updateData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delet();
    }

}
