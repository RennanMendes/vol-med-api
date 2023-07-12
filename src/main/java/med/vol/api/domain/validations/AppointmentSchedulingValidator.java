package med.vol.api.domain.validations;

import med.vol.api.domain.dto.appointment.AppointmentDTO;

public interface AppointmentSchedulingValidator {

    void validate(AppointmentDTO data);
}
