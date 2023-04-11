package com.example.propconnectsolo.controllers;

import com.example.propconnectsolo.models.User;
import com.example.propconnectsolo.repositories.NoteRepository;
import com.example.propconnectsolo.repositories.PropertyRepository;
import com.example.propconnectsolo.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final NoteRepository noteDao;
    private final PropertyRepository propDao;

    private PasswordEncoder passwordEncoder;


    public UserController(UserRepository userDao, NoteRepository noteDao, PropertyRepository propDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.noteDao = noteDao;
        this.propDao = propDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String signUpForm(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User userDetails = (User) authentication.getPrincipal();
//        userDetails.setUsername(username);
//        userDetails.setName(name);
//        userDetails.setEmail(email);
//        userDetails.setPassword(hash);

        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showProfile(Model model){
        return "users/profile";
    }

    @GetMapping("/profile/edit")
    public String showEditProfile(Model model){
        model.addAttribute("user", new User());
        return "users/edit-profile";
    }

    @GetMapping("/profile/{id}/edit")
    public String updateUser (@PathVariable long id, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User editedUser = userDao.getReferenceById(id);
        if(user.getId() == editedUser.getId()){
            model.addAttribute("user", userDao.getReferenceById(id));
            return "users/register";
        }
        return "redirect:/login";
    }

//    @PostMapping("profile/edit")
//    public String saveEditProfile(@ModelAttribute User user, @RequestParam(name="username") String username, @RequestParam(name="name") String name, @RequestParam(name="email") String email, @RequestParam(name="password") String password){
//        user = userDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//        String hash = passwordEncoder.encode(password);
//        user.setPassword(hash);
//        user.setUsername(username);
//        user.setName(name);
//        user.setEmail(email);
//        userDao.save(user);
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User userDetails = (User) authentication.getPrincipal();
//        userDetails.setUsername(username);
//        userDetails.setName(name);
//        userDetails.setEmail(email);
//        userDetails.setPassword(hash);
//        return "redirect:/users/profile";
//    }

}
