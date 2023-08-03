package med.vol.api.domain.repository;

import med.vol.api.domain.dto.adress.AddressDTO;
import med.vol.api.domain.dto.doctor.RegisterDoctorData;
import med.vol.api.domain.dto.patient.RegisterPatientData;
import med.vol.api.domain.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("should return null")
    void randomChoiceOfDoctor_Case1() {

        // given or arrange
        var nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var doctor = registerDoctor("Pedro", "pedro@email.com", "123456", Specialty.CARDIOLOGIA);
        var patient = registerPatient("Andressa", "andressa@email.com", "00000000000");
        registerAppointment(doctor, patient, nextMonday);

        // when or act
        var freeDoctor = doctorRepository.randomChoiceOfDoctor(Specialty.CARDIOLOGIA, nextMonday);

        // then or assert
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("should return a doctor")
    void randomChoiceOfDoctor_Case2() {
        var nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var doctor = registerDoctor("Pedro", "pedro@email.com", "123456", Specialty.CARDIOLOGIA);
        var freeDoctor = doctorRepository.randomChoiceOfDoctor(Specialty.CARDIOLOGIA, nextMonday);

        assertThat(freeDoctor).isEqualTo(doctor);
    }

    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        entityManager.persist(new Appointment(null, doctor, patient, date));
    }

    private Doctor registerDoctor(String nome, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(DoctorData(nome, email, crm, specialty));
        entityManager.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String cpf) {
        var patient = new Patient(patientData(name, email, cpf));
        entityManager.persist(patient);
        return patient;
    }

    private RegisterDoctorData DoctorData(String name, String email, String crm, Specialty specialty) {
        return new RegisterDoctorData(
                name,
                email,
                crm,
                "61999999999",
                specialty,
                addressData());
    }

    private RegisterPatientData patientData(String name, String email, String cpf) {
        return new RegisterPatientData(
                name,
                email,
                "61999999999",
                cpf,
                addressData());
    }

    private AddressDTO addressData() {
        return new AddressDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null);
    }

}