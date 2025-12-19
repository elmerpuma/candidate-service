package com.company.clients.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public class ClientResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("nombre")
    private String firstName;
    @JsonProperty("apellido")
    private String lastName;
    @JsonProperty("edad")
    private Integer age;
    @JsonProperty("fechaNacimiento")
    private LocalDate birthDate;
    @JsonProperty("fechaCreacion")
    private OffsetDateTime createdAt;
    @JsonProperty("fechaEstimadaEvento")
    private LocalDate estimatedEventDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getEstimatedEventDate() {
        return estimatedEventDate;
    }

    public void setEstimatedEventDate(LocalDate estimatedEventDate) {
        this.estimatedEventDate = estimatedEventDate;
    }
}

