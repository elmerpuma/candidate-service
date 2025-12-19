package com.company.clients.service;

import com.company.clients.dto.request.CreateClientRequest;
import com.company.clients.dto.response.ClientMetricsResponse;
import com.company.clients.dto.response.ClientResponse;
import java.util.List;

public interface ClientService {
    ClientResponse createClient(CreateClientRequest request);
    List<ClientResponse> getAllClients();
    ClientMetricsResponse getMetrics();
}

