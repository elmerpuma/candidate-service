package com.company.clients.service.impl;

import com.company.clients.dto.request.CreateClientRequest;
import com.company.clients.dto.response.ClientMetricsResponse;
import com.company.clients.dto.response.ClientResponse;
import com.company.clients.entity.ClientEntity;
import com.company.clients.exception.BusinessException;
import com.company.clients.mapper.ClientMapper;
import com.company.clients.metrics.ClientMetricsCalculator;
import com.company.clients.repository.ClientRepository;
import com.company.clients.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final ClientMetricsCalculator calculator = new ClientMetricsCalculator();

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public ClientResponse createClient(CreateClientRequest request) {
        if (request.getAge() < 0) {
            throw new BusinessException("NEGATIVE_AGE", "La edad no puede ser negativa");
        }
        ClientEntity entity = ClientMapper.toEntity(
                request.getFirstName(),
                request.getLastName(),
                request.getAge(),
                request.getBirthDate()
        );
        ClientEntity saved = repository.save(entity);
        return ClientMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponse> getAllClients() {
        List<ClientEntity> all = repository.findAll();
        List<ClientResponse> responses = new ArrayList<>();
        for (ClientEntity e : all) {
            responses.add(ClientMapper.toResponse(e));
        }
        return responses;
    }

    @Override
    @Transactional(readOnly = true)
    public ClientMetricsResponse getMetrics() {
        List<ClientEntity> all = repository.findAll();
        List<Integer> ages = new ArrayList<>();
        for (ClientEntity e : all) {
            ages.add(e.getAge());
        }
        double avg = calculator.average(ages);
        double std = calculator.stdDev(ages);
        ClientMetricsResponse r = new ClientMetricsResponse();
        r.setAverageAge(avg);
        r.setAgeStdDeviation(std);
        r.setTotalClients(all.size());
        return r;
    }
}

