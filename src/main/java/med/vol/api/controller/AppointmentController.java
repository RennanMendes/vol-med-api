package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.domain.dto.appointment.AppointmentDTO;
import med.vol.api.domain.dto.appointment.AppointmentDetailDTO;
import med.vol.api.domain.service.AppointmentSchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentSchedulingService appointmentService;

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentDetailDTO> scheduleAppointment(@RequestBody @Valid AppointmentDTO appointmentDTO){
        var dto = appointmentService.scheduling(appointmentDTO);

        return ResponseEntity.ok(dto);
    }
}
