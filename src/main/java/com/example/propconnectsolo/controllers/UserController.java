package com.example.propconnectsolo.controllers;

import com.example.propconnectsolo.models.User;
import com.example.propconnectsolo.repositories.NoteRepository;
import com.example.propconnectsolo.repositories.PropertyRepository;
import com.example.propconnectsolo.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final NoteRepository noteDao;
    private final PropertyRepository propDao;

    public UserController(UserRepository userDao, NoteRepository noteDao, PropertyRepository propDao) {
        this.userDao = userDao;
        this.noteDao = noteDao;
        this.propDao = propDao;
    }

    @GetMapping("/register")
    public String signUpForm(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user){
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/current-weather";
    }


}
