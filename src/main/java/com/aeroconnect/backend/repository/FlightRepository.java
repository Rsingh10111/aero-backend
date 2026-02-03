package com.aeroconnect.backend.repository;

import com.aeroconnect.backend.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    // Custom query methods can be added here if needed
}
