package com.example.propconnectsolo.controllers;

import com.example.propconnectsolo.models.Property;
import com.example.propconnectsolo.models.User;
import com.example.propconnectsolo.repositories.NoteRepository;
import com.example.propconnectsolo.repositories.PropertyRepository;
import com.example.propconnectsolo.repositories.UserRepository;
import com.example.propconnectsolo.services.LiveWeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PropertyController {
    private final NoteRepository noteDao;
    private final PropertyRepository propDao;
    private final UserRepository userDao;
    private final LiveWeatherService liveWeatherService;

//    @Value("${api.filestack.key}")
    public String apiKey;


    public PropertyController(NoteRepository noteDao, PropertyRepository propDao, UserRepository userDao, LiveWeatherService liveWeatherService) {
        this.noteDao = noteDao;
        this.propDao = propDao;
        this.userDao = userDao;
        this.liveWeatherService = liveWeatherService;
    }

    @GetMapping("/props")
    public String showAllProps(Model mode){
        return "props/index";
    }

    @GetMapping("props/create")
    public String createPropForm(Model model){
        model.addAttribute("prop", new Property());
        model.addAttribute("apikey", apiKey);
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

}
