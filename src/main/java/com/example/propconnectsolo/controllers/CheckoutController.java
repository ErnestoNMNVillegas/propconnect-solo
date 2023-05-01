package com.example.propconnectsolo.controllers;

import com.example.propconnectsolo.data.ChargeRequest;
import com.example.propconnectsolo.services.StripeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {

    private final StripeService paymentsService;


    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    public CheckoutController(StripeService paymentsService) {
        this.paymentsService = paymentsService;
    }


    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
        System.out.println("ChargeRequest.Currency.USD = " + ChargeRequest.Currency.USD);
        return "stripe/checkout";
    }
}

