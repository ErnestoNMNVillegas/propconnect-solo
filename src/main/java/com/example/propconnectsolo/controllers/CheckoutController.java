//package com.example.propconnectsolo.controllers;
//
//import com.example.propconnectsolo.models.ChargeRequest;
//import com.example.propconnectsolo.repositories.NoteRepository;
//import com.example.propconnectsolo.repositories.PropertyRepository;
//import com.example.propconnectsolo.repositories.UserRepository;
//import com.example.propconnectsolo.services.StripeService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class CheckoutController {
//
//    private final StripeService paymentsService;
//
//    private final UserRepository userDao;
//
//    private final PropertyRepository propDao;
//
//    private final NoteRepository noteDao;
//
//
//    @Value("${STRIPE_PUBLIC_KEY}")
//    private String stripePublicKey;
//
//    public CheckoutController(StripeService paymentsService, UserRepository userDao, PropertyRepository propDao, NoteRepository noteDao) {
//        this.paymentsService = paymentsService;
//        this.userDao = userDao;
//        this.propDao = propDao;
//        this.noteDao = noteDao;
//    }
//
//
//    @RequestMapping("/checkout")
//    public String checkout(Model model) {
//        model.addAttribute("amount", 50 * 100); // in cents
//        model.addAttribute("stripePublicKey", stripePublicKey);
//        model.addAttribute("currency", ChargeRequest.Currency.USD);
//        return "stripe/checkout";
//    }
//}
//
