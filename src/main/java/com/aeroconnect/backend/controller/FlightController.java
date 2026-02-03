package com.aeroconnect.backend.controller;

import com.aeroconnect.backend.dto.MessageResponse;
import com.aeroconnect.backend.entity.Flight;
import com.aeroconnect.backend.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));
        return ResponseEntity.ok(flight);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Flight createFlight(@RequestBody Flight flight) {
        return flightRepository.save(flight);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flightDetails) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));

        flight.setFlightNumber(flightDetails.getFlightNumber());
        flight.setAirlineName(flightDetails.getAirlineName());
        flight.setSource(flightDetails.getSource());
        flight.setDestination(flightDetails.getDestination());
        flight.setDepartureTime(flightDetails.getDepartureTime());
        flight.setArrivalTime(flightDetails.getArrivalTime());
        flight.setPrice(flightDetails.getPrice());
        flight.setStatus(flightDetails.getStatus());

        Flight updatedFlight = flightRepository.save(flight);
        return ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));
        flightRepository.delete(flight);
        return ResponseEntity.ok(new MessageResponse("Flight deleted successfully!"));
    }
}
