//package com.example.propconnectsolo.controllers;
//
//import com.example.propconnectsolo.models.ChargeRequest;
//import com.example.propconnectsolo.repositories.NoteRepository;
//import com.example.propconnectsolo.repositories.PropertyRepository;
//import com.example.propconnectsolo.repositories.UserRepository;
//import com.example.propconnectsolo.services.StripeService;
//import com.stripe.exception.StripeException;
//import com.stripe.model.Charge;
//import lombok.extern.java.Log;
//import org.springframework.beans.factory.BeanCreationException;
//import org.springframework.beans.factory.UnsatisfiedDependencyException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.naming.AuthenticationException;
//
//@Component
//@Controller
//public class ChargeController {
////    @Autowired
//    private final StripeService paymentsService;
//
//    private final UserRepository userDao;
//
//    private final PropertyRepository propDao;
//
//    private final NoteRepository noteDao;
//
//    @Autowired
//    public ChargeController(StripeService paymentsService, UserRepository userDao, PropertyRepository propDao, NoteRepository noteDao) {
//        this.paymentsService = paymentsService;
//        this.userDao = userDao;
//        this.propDao = propDao;
//        this.noteDao = noteDao;
//    }
//
//
//    @Transactional
//    @PostMapping("/charge")
//    public String charge(ChargeRequest chargeRequest, Model model)
//            throws StripeException,BeanCreationException {
//        chargeRequest.setDescription("Example charge");
//        chargeRequest.setCurrency(ChargeRequest.Currency.USD);
//        Charge charge = paymentsService.charge(chargeRequest);
//        model.addAttribute("id", charge.getId());
//        model.addAttribute("status", charge.getStatus());
//        model.addAttribute("chargeId", charge.getId());
//        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
//        return "stripe/result";
//    }
//
//    @ExceptionHandler(StripeException.class)
//    public String handleError(Model model, StripeException ex) {
//        model.addAttribute("error", ex.getMessage());
//        return "stripe/result";
//    }
//}
//
