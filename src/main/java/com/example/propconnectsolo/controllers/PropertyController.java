package com.example.propconnectsolo.controllers;

import com.example.propconnectsolo.models.Property;
import com.example.propconnectsolo.models.User;
import com.example.propconnectsolo.repositories.NoteRepository;
import com.example.propconnectsolo.repositories.PropertyRepository;
import com.example.propconnectsolo.repositories.UserRepository;
import com.example.propconnectsolo.services.LiveWeatherService;
import jakarta.websocket.DeploymentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.cdi.Eager;
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
