package com.company.clients.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientMetricsResponse {
    @JsonProperty("promedioEdad")
    private double averageAge;
    @JsonProperty("desviacionEstandarEdad")
    private double ageStdDeviation;
    @JsonProperty("totalClientes")
    private long totalClients;

    public double getAverageAge() {
        return averageAge;
    }

    public void setAverageAge(double averageAge) {
        this.averageAge = averageAge;
    }

    public double getAgeStdDeviation() {
        return ageStdDeviation;
    }

    public void setAgeStdDeviation(double ageStdDeviation) {
        this.ageStdDeviation = ageStdDeviation;
    }

    public long getTotalClients() {
        return totalClients;
    }

    public void setTotalClients(long totalClients) {
        this.totalClients = totalClients;
    }
}

