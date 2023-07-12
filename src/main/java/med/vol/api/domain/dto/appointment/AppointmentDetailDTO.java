package med.vol.api.domain.dto.appointment;

import med.vol.api.domain.model.Appointment;

import java.time.LocalDateTime;

public record AppointmentDetailDTO(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {
    public AppointmentDetailDTO(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDate());
    }
}
