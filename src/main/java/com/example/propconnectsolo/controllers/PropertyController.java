package com.example.propconnectsolo.controllers;

import com.example.propconnectsolo.data.ChargeRequest;
import com.example.propconnectsolo.models.Property;
import com.example.propconnectsolo.models.User;
import com.example.propconnectsolo.repositories.NoteRepository;
import com.example.propconnectsolo.repositories.PropertyRepository;
import com.example.propconnectsolo.repositories.UserRepository;
import com.example.propconnectsolo.services.LiveWeatherService;
import com.example.propconnectsolo.services.StripeService;
import jakarta.websocket.DeploymentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PropertyController {
    private final NoteRepository noteDao;
    private final PropertyRepository propDao;
    private final UserRepository userDao;
    private final LiveWeatherService liveWeatherService;

    private final StripeService paymentsService;

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    public PropertyController(NoteRepository noteDao, PropertyRepository propDao, UserRepository userDao, LiveWeatherService liveWeatherService, StripeService paymentsService) {
        this.noteDao = noteDao;
        this.propDao = propDao;
        this.userDao = userDao;
        this.liveWeatherService = liveWeatherService;
        this.paymentsService = paymentsService;
    }


    @GetMapping("/props")
    public String showAllProps(Model model){
        model.addAttribute("props", propDao.findAll());
        return "props/index";
    }

    @GetMapping("/props/{id}")
    public String getOneProp(@PathVariable long id, Model model) throws DeploymentException {
        Property prop = propDao.findById(id);
        model.addAttribute("prop", prop);
        model.addAttribute("notes", noteDao.findNotesByProperty(prop));
        model.addAttribute("weather", liveWeatherService.getCurrentWeather(prop.getCity(), "us"));
        return "props/show";
    }

    @GetMapping("props/create")
    public String createPropForm(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getSubscription() == 0){
            model.addAttribute("amount", 50 * 100); // in cents
            model.addAttribute("stripePublicKey", stripePublicKey);
            model.addAttribute("currency", ChargeRequest.Currency.USD);
            System.out.println("ChargeRequest.Currency.USD = " + ChargeRequest.Currency.USD);
            return "stripe/checkout";
        }
//        System.out.println("user.getSubscription() = " + user.getSubscription());
        model.addAttribute("prop", new Property());
        return "props/create";
    }

    @PostMapping("/props/create")
    public String saveProp(@ModelAttribute Property prop){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Property origProp = propDao.findById(prop.getId());
        if(origProp == null || user.getId() == origProp.getUser().getId()) {
            prop.setUser(user);
            propDao.save(prop);
        }
        return "redirect:/profile";
    }

    @GetMapping("/props/{id}/edit")
    public String editPropForm(Model model, @PathVariable long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Property prop = propDao.findById(id);
        if (user.getId() == prop.getUser().getId()){
            model.addAttribute("prop", prop);
            return "props/create";
        } else {
            return "redirect:/props";
        }
    }

    @GetMapping("/props/{id}/delete")
    public String confirmDelete(@PathVariable long id, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("prop", propDao.findById(id));
        return "props/delete";
    }

    @PostMapping("/props/{id}/delete")
    public String deleteProp(@PathVariable long id, @RequestParam(name="prop-id") long propId){
        if (id == propId){
            Property prop = propDao.findById(id);
            propDao.delete(prop);
        }
        return "redirect:/props";
    }


}
