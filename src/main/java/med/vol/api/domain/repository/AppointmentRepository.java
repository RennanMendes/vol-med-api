package med.vol.api.domain.repository;

import med.vol.api.domain.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Boolean existsByDoctorIdAndDate(Long idDoctor, LocalDateTime date);

    Boolean existsByPatientIdAndDateBetween(Long id, LocalDateTime firstHour, LocalDateTime lastHour);
}
