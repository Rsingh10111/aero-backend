package com.aeroconnect.backend.controller;

import com.aeroconnect.backend.dto.PaymentRequest;
import com.aeroconnect.backend.dto.PaymentResponse;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PaymentController {

    @Autowired
    private RazorpayClient razorpayClient;

    @Value("${razorpay.key.id}")
    private String keyId;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody PaymentRequest paymentRequest) {
        try {
            JSONObject orderRequest = new JSONObject();
            // DEMO MODE: Force amount to 1 INR (100 paise) so real payment is easy/negligible
            orderRequest.put("amount", 100); 
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "txn_" + System.currentTimeMillis());
            orderRequest.put("payment_capture", 1); // Auto capture

            Order order = razorpayClient.orders.create(orderRequest);

            PaymentResponse response = new PaymentResponse(
                    order.get("id"),
                    keyId,
                    order.get("amount"),
                    order.get("currency")
            );

            return ResponseEntity.ok(response);
        } catch (RazorpayException e) {
            return ResponseEntity.badRequest().body("Error creating Razorpay order: " + e.getMessage());
        }
    }
}
