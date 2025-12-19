package com.company.clients.repository;

import com.company.clients.entity.ClientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClientRepositoryTest {
    @Autowired
    private ClientRepository repository;

    @Test
    void saveAndFind() {
        ClientEntity e = new ClientEntity();
        e.setFirstName("Juan");
        e.setLastName("Perez");
        e.setAge(30);
        e.setBirthDate(LocalDate.of(1995, 5, 20));
        e.setCreatedAt(OffsetDateTime.now());
        ClientEntity saved = repository.save(e);
        assertNotNull(saved.getId());
        assertTrue(repository.findById(saved.getId()).isPresent());
    }
}

