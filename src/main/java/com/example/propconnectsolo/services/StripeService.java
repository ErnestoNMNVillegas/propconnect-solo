//package com.example.propconnectsolo.services;
//
//import com.example.propconnectsolo.models.ChargeRequest;
//import com.example.propconnectsolo.repositories.NoteRepository;
//import com.example.propconnectsolo.repositories.PropertyRepository;
//import com.example.propconnectsolo.repositories.UserRepository;
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.Charge;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import javax.naming.AuthenticationException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class StripeService {
//
//
//    @Value("${STRIPE_SECRET_KEY}")
//    String secretKey;
//
//
//    @PostConstruct
//    public void init() {
//        Stripe.apiKey = secretKey;
//    }
//    public Charge charge(ChargeRequest chargeRequest)
//            throws StripeException {
//        Map<String, Object> chargeParams = new HashMap<>();
//        chargeParams.put("amount", chargeRequest.getAmount());
//        chargeParams.put("currency", chargeRequest.getCurrency());
//        chargeParams.put("description", chargeRequest.getDescription());
//        chargeParams.put("source", chargeRequest.getStripeToken());
//        return Charge.create(chargeParams);
//    }
//}
