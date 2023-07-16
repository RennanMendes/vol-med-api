package med.vol.api.controller;

import med.vol.api.domain.dto.appointment.AppointmentDTO;
import med.vol.api.domain.dto.appointment.AppointmentDetailDTO;
import med.vol.api.domain.model.Specialty;
import med.vol.api.domain.service.AppointmentSchedulingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppointmentDTO> appointmentJson;

    @Autowired
    private JacksonTester<AppointmentDetailDTO> appointmentDetailJson;

    @MockBean
    private AppointmentSchedulingService appointmentService;

    @Test
    @WithMockUser
    @DisplayName("Should return http code 400, invalid information")
    void scheduleAppointment_Case1() throws Exception {
        var response = mvc.perform(post("/appointment"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @WithMockUser
    @DisplayName("Should return http code 200, valid information")
    void scheduleAppointment_Case2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGIA;
        var appointmentDetailDTO = new AppointmentDetailDTO(null, 2L, 2L, date);

        when(appointmentService.scheduling(any())).thenReturn(appointmentDetailDTO);

        var response = mvc.perform(post("/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentJson.write(
                                new AppointmentDTO(2L, 2L, date, specialty)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJson = appointmentDetailJson.write(appointmentDetailDTO).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}