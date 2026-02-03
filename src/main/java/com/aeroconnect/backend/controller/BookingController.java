package com.aeroconnect.backend.controller;

import com.aeroconnect.backend.dto.MessageResponse;
import com.aeroconnect.backend.entity.Booking;
import com.aeroconnect.backend.entity.Flight;
import com.aeroconnect.backend.entity.User;
import com.aeroconnect.backend.repository.BookingRepository;
import com.aeroconnect.backend.repository.FlightRepository;
import com.aeroconnect.backend.repository.UserRepository;
import com.aeroconnect.backend.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS}, maxAge = 3600)
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Booking> getMyBookings() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(userDetails.getId()).get();
        return bookingRepository.findByUser(user);
    }

    @PostMapping("/{flightId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> bookFlight(@PathVariable Long flightId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(userDetails.getId()).get();
        
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        if (!"SCHEDULED".equals(flight.getStatus()) && !"ON_TIME".equals(flight.getStatus()) && !"DELAYED".equals(flight.getStatus())) {
             return ResponseEntity.badRequest().body(new MessageResponse("Flight is not available for booking"));
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingDate(new Date());
        booking.setStatus("CONFIRMED");

        bookingRepository.save(booking);

        return ResponseEntity.ok(new MessageResponse("Flight booked successfully!"));
    }

    @DeleteMapping("/{bookingId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Security Check: Ensure user owns this booking
        if (!booking.getUser().getId().equals(userDetails.getId())) {
            return ResponseEntity.status(403).body(new MessageResponse("Error: Unauthorized to cancel this booking"));
        }

        bookingRepository.delete(booking);

        return ResponseEntity.ok(new MessageResponse("Booking cancelled successfully!"));
    }
}
