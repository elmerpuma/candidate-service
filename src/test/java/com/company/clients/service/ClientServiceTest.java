package com.company.clients.service;

import com.company.clients.dto.request.CreateClientRequest;
import com.company.clients.dto.response.ClientMetricsResponse;
import com.company.clients.dto.response.ClientResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ClientServiceTest {
    @Autowired
    private ClientService service;

    @Test
    void createAndMetrics() {
        CreateClientRequest r1 = new CreateClientRequest();
        r1.setFirstName("Ana");
        r1.setLastName("Lopez");
        r1.setAge(25);
        r1.setBirthDate(LocalDate.of(2000, 1, 1));
        ClientResponse c1 = service.createClient(r1);
        assertNotNull(c1.getId());

        CreateClientRequest r2 = new CreateClientRequest();
        r2.setFirstName("Luis");
        r2.setLastName("Suarez");
        r2.setAge(35);
        r2.setBirthDate(LocalDate.of(1990, 2, 2));
        service.createClient(r2);

        List<ClientResponse> all = service.getAllClients();
        assertEquals(2, all.size());

        ClientMetricsResponse m = service.getMetrics();
        assertEquals(2, m.getTotalClients());
        assertTrue(m.getAverageAge() > 0);
        assertTrue(m.getAgeStdDeviation() >= 0);
    }
}

