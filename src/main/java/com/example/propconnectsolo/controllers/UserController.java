package com.example.propconnectsolo.controllers;

import com.example.propconnectsolo.models.User;
import com.example.propconnectsolo.repositories.NoteRepository;
import com.example.propconnectsolo.repositories.PropertyRepository;
import com.example.propconnectsolo.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final NoteRepository noteDao;
    private final PropertyRepository propDao;

//    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, NoteRepository noteDao, PropertyRepository propDao) {
        this.userDao = userDao;
        this.noteDao = noteDao;
        this.propDao = propDao;
    }

    @GetMapping("Register")
    public String signUpForm(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }



}
