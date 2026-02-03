package com.aeroconnect.backend.loader;

import com.aeroconnect.backend.entity.Flight;
import com.aeroconnect.backend.entity.User;
import com.aeroconnect.backend.repository.FlightRepository;
import com.aeroconnect.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        // Initialize Users if table is empty
        if (userRepository.count() == 0) {
            System.out.println("No users found. Creating sample users...");
            
            // Admin User
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("123456"));
            admin.setEmail("admin@aeroconnect.com");
            admin.setRole("ROLE_ADMIN");
            userRepository.save(admin);
            
            System.out.println("Created Admin: username='admin', password='123456'");

            // Normal User
            User user = new User();
            user.setUsername("raj");
            user.setPassword(encoder.encode("123456"));
            user.setEmail("raj@gmail.com");
            user.setRole("ROLE_USER");
            userRepository.save(user);
            
            System.out.println("Created User: username='raj', password='123456'");
        }

        // Initialize Flights if table is empty
        if (flightRepository.count() == 0) {
            System.out.println("No flights found. Creating sample flights...");

            // 1. Air India (On Time)
            Flight f1 = new Flight();
            f1.setFlightNumber("AI-101");
            f1.setAirlineName("Air India");
            f1.setSource("New Delhi (DEL)");
            f1.setDestination("Mumbai (BOM)");
            f1.setDepartureTime(new Date(System.currentTimeMillis() + 3600000)); // +1 hour
            f1.setArrivalTime(new Date(System.currentTimeMillis() + 10800000)); // +3 hours
            f1.setPrice(6500.0);
            f1.setStatus("ON_TIME");
            flightRepository.save(f1);

            // 2. Indigo (Delayed)
            Flight f2 = new Flight();
            f2.setFlightNumber("6E-304");
            f2.setAirlineName("IndiGo");
            f2.setSource("Bangalore (BLR)");
            f2.setDestination("Chennai (MAA)");
            f2.setDepartureTime(new Date(System.currentTimeMillis() + 7200000));
            f2.setArrivalTime(new Date(System.currentTimeMillis() + 9000000));
            f2.setPrice(3200.0);
            f2.setStatus("DELAYED");
            flightRepository.save(f2);

            // 3. Vistara (Cancelled)
            Flight f3 = new Flight();
            f3.setFlightNumber("UK-885");
            f3.setAirlineName("Vistara");
            f3.setSource("Mumbai (BOM)");
            f3.setDestination("Delhi (DEL)");
            f3.setDepartureTime(new Date(System.currentTimeMillis() + 18000000));
            f3.setArrivalTime(new Date(System.currentTimeMillis() + 25200000));
            f3.setPrice(7800.0);
            f3.setStatus("CANCELLED");
            flightRepository.save(f3);

            // 4. SpiceJet (Scheduled)
            Flight f4 = new Flight();
            f4.setFlightNumber("SG-555");
            f4.setAirlineName("SpiceJet");
            f4.setSource("Kolkata (CCU)");
            f4.setDestination("Bangalore (BLR)");
            f4.setDepartureTime(new Date(System.currentTimeMillis() + 86400000)); // +1 day
            f4.setArrivalTime(new Date(System.currentTimeMillis() + 97200000));
            f4.setPrice(5100.0);
            f4.setStatus("SCHEDULED");
            flightRepository.save(f4);
            
            // 5. Akasa Air (On Time)
            Flight f5 = new Flight();
            f5.setFlightNumber("QP-110");
            f5.setAirlineName("Akasa Air");
            f5.setSource("Goa (GOI)");
            f5.setDestination("Mumbai (BOM)");
            f5.setDepartureTime(new Date(System.currentTimeMillis() + 43200000));
            f5.setArrivalTime(new Date(System.currentTimeMillis() + 46800000));
            f5.setPrice(2900.0);
            f5.setStatus("ON_TIME");
            flightRepository.save(f5);
            
         // 6. IndiGo (Delayed)
            Flight f6 = new Flight();
            f6.setFlightNumber("6E-452");
            f6.setAirlineName("IndiGo");
            f6.setSource("Chennai (MAA)");
            f6.setDestination("Bangalore (BLR)");
            f6.setDepartureTime(new Date(System.currentTimeMillis() + 3600000));  // +1 hour
            f6.setArrivalTime(new Date(System.currentTimeMillis() + 7200000));    // +2 hours
            f6.setPrice(3400.0);
            f6.setStatus("DELAYED");
            flightRepository.save(f6);

            // 7. Vistara (Scheduled)
            Flight f7 = new Flight();
            f7.setFlightNumber("UK-709");
            f7.setAirlineName("Vistara");
            f7.setSource("Delhi (DEL)");
            f7.setDestination("Pune (PNQ)");
            f7.setDepartureTime(new Date(System.currentTimeMillis() + 54000000)); // +15 hours
            f7.setArrivalTime(new Date(System.currentTimeMillis() + 59400000));   // +16.5 hours
            f7.setPrice(6200.0);
            f7.setStatus("SCHEDULED");
            flightRepository.save(f7);

            // 8. Air India (Cancelled)
            Flight f8 = new Flight();
            f8.setFlightNumber("AI-909");
            f8.setAirlineName("Air India");
            f8.setSource("Mumbai (BOM)");
            f8.setDestination("Hyderabad (HYD)");
            f8.setDepartureTime(new Date(System.currentTimeMillis() + 10800000)); // +3 hours
            f8.setArrivalTime(new Date(System.currentTimeMillis() + 14400000));   // +4 hours
            f8.setPrice(4800.0);
            f8.setStatus("CANCELLED");
            flightRepository.save(f8);

            // 9. SpiceJet (On Time)
            Flight f9 = new Flight();
            f9.setFlightNumber("SG-331");
            f9.setAirlineName("SpiceJet");
            f9.setSource("Kolkata (CCU)");
            f9.setDestination("Delhi (DEL)");
            f9.setDepartureTime(new Date(System.currentTimeMillis() + 25200000)); // +7 hours
            f9.setArrivalTime(new Date(System.currentTimeMillis() + 30600000));   // +8.5 hours
            f9.setPrice(5300.0);
            f9.setStatus("ON_TIME");
            flightRepository.save(f9);

            // 10. GoFirst (Scheduled)
            Flight f10 = new Flight();
            f10.setFlightNumber("G8-214");
            f10.setAirlineName("GoFirst");
            f10.setSource("Ahmedabad (AMD)");
            f10.setDestination("Mumbai (BOM)");
            f10.setDepartureTime(new Date(System.currentTimeMillis() + 18000000)); // +5 hours
            f10.setArrivalTime(new Date(System.currentTimeMillis() + 21000000));   // +5.8 hours
            f10.setPrice(2600.0);
            f10.setStatus("SCHEDULED");
            flightRepository.save(f10);


            System.out.println("Sample flights created.");
        }
    }
}
