package com.company.clients.controller;

import com.company.clients.dto.request.CreateClientRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    private String login() throws Exception {
        Map<String, String> body = Map.of("username", "admin", "password", "password");
        String res = mockMvc.perform(post("/api/v1/auth/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Map<?, ?> json = mapper.readValue(res, Map.class);
        return (String) json.get("token");
    }

    @Test
    void createAndMetricsEndpoints() throws Exception {
        String token = login();
        CreateClientRequest req = new CreateClientRequest();
        req.setFirstName("Mario");
        req.setLastName("Rossi");
        req.setAge(40);
        req.setBirthDate(LocalDate.of(1985, 3, 1));
        mockMvc.perform(post("/api/v1/clients")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/clients/metrics")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void businessError422() throws Exception {
        String token = login();
        Map<String, Object> bad = Map.of(
                "nombre", "Bad",
                "apellido", "Age",
                "edad", -1,
                "fechaNacimiento", "2000-01-01"
        );
        mockMvc.perform(post("/api/v1/clients")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bad)))
                .andExpect(status().isUnprocessableEntity());
    }
}

