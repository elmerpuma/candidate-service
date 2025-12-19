package com.company.clients.mapper;

import com.company.clients.dto.response.ClientResponse;
import com.company.clients.entity.ClientEntity;
import com.company.clients.util.DateUtils;

public class ClientMapper {
    public static ClientEntity toEntity(String firstName, String lastName, Integer age, java.time.LocalDate birthDate) {
        ClientEntity e = new ClientEntity();
        e.setFirstName(firstName);
        e.setLastName(lastName);
        e.setAge(age);
        e.setBirthDate(birthDate);
        e.setCreatedAt(java.time.OffsetDateTime.now());
        return e;
    }

    public static ClientResponse toResponse(ClientEntity e) {
        ClientResponse r = new ClientResponse();
        r.setId(e.getId());
        r.setFirstName(e.getFirstName());
        r.setLastName(e.getLastName());
        r.setAge(e.getAge());
        r.setBirthDate(e.getBirthDate());
        r.setCreatedAt(e.getCreatedAt());
        r.setEstimatedEventDate(DateUtils.estimateLifeExpectancyDate(e.getBirthDate()));
        return r;
    }
}

