package com.example.demo.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT SUM(c.value) FROM Client c WHERE c.value > :random")
    BigDecimal calculateConsumption(double random);

}
