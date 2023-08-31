package com.example.propconnectsolo.controllers;

import com.example.propconnectsolo.data.ChargeRequest;
import com.example.propconnectsolo.models.User;
import com.example.propconnectsolo.repositories.UserRepository;
import com.example.propconnectsolo.services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChargeController {
    @Autowired
    private StripeService paymentsService;

    private final UserRepository userDao;

    public ChargeController(UserRepository userDao) {
        this.userDao = userDao;
    }


    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model)
            throws StripeException {
        chargeRequest.setDescription("PropConnect charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.USD);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        model.addAttribute("created", charge.getCreated());

        //Saves the Created date to the subscription field of user object
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDetails =  userDao.findUserById(userDetails.getId());
        userDetails.setSubscription(charge.getCreated());
        userDao.save(userDetails);
        return "stripe/result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "stripe/result";
    }
}

