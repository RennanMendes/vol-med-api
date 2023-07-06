package med.vol.api.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.domain.dto.patient.RegisterPatientData;
import med.vol.api.domain.dto.patient.UpdatePatientDTO;

@Table(name = "patients")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    private Boolean active;

    @Embedded
    private Address address;

    public Patient(RegisterPatientData patientData) {
        this.name = patientData.name();
        this.email = patientData.email();
        this.phone = patientData.phone();
        this.cpf = patientData.cpf();
        this.address = new Address(patientData.address());
        this.active = true;
    }

    public void updateData(UpdatePatientDTO data) {
        this.name = data.name() != null ? data.name() : this.name;
        this.phone = data.phone() != null ? data.phone() : this.phone;

        if (data.address() != null) {
            this.address.updateAddress(data.address());
        }

    }

    public void delete(Patient patient) {
        this.active = false;
    }
}
