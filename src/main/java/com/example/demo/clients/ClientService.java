package com.example.demo.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository repository;

    @Transactional
    public void save(Client client) {
        repository.save(client);
    }

    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public BigDecimal sumConsumption() {
        return repository.calculateConsumption(Math.random() * 10);
    }
}
