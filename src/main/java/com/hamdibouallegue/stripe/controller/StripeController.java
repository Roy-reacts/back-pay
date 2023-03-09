package com.hamdibouallegue.stripe.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

@RestController
@RequestMapping(value = "/api")
public class StripeController {
// create a Gson object
	private static Gson gson = new Gson();


	@PostMapping("/create-payment-intent")
public ResponseEntity<?> createPaymentIntent(@RequestBody Map<String, Object> payload) {
    try {
        Stripe.apiKey = "pk_test_51MdoZSC8gOLcVvt1j758f50RoVGs2eKEIRDDE3V8sMPqn8G4vqgofEHMtM5uhRwGjRhiQsryOReSIIKfopD6vnRK00iv1pX5it";

        PaymentIntentCreateParams params = PaymentIntentCreateParams
            .builder()
            .setAmount(1000L)
            .setCurrency("usd")
            .setDescription("Example payment")
            .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        return ResponseEntity.ok().body(paymentIntent.getClientSecret());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
}