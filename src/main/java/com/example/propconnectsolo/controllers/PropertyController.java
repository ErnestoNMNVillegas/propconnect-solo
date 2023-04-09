package com.example.propconnectsolo.controllers;

import com.example.propconnectsolo.repositories.NoteRepository;
import com.example.propconnectsolo.repositories.PropertyRepository;
import com.example.propconnectsolo.repositories.UserRepository;
import com.example.propconnectsolo.services.LiveWeatherService;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PropertyController {
    private final NoteRepository noteDao;
    private final PropertyRepository propDao;
    private final UserRepository userDao;
    private final LiveWeatherService liveWeatherService;


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


}
