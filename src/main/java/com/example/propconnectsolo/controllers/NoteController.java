package com.example.propconnectsolo.controllers;

import com.example.propconnectsolo.repositories.NoteRepository;
import com.example.propconnectsolo.repositories.PropertyRepository;
import com.example.propconnectsolo.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NoteController {

    private final UserRepository userDao;

    private final PropertyRepository propDao;

    private final NoteRepository noteDao;


    public NoteController(UserRepository userDao, PropertyRepository propDao, NoteRepository noteDao) {
        this.userDao = userDao;
        this.propDao = propDao;
        this.noteDao = noteDao;
    }

    @GetMapping("/notes")
    public String showAllNotes(Model model){
        model.addAttribute("notes", noteDao.findAll());
        model.addAttribute("props", propDao.findAll());
        return "notes/index";
    }

    @GetMapping("/notes/{id}")
    public String indNoteShow(@PathVariable long id, Model model){
        model.addAttribute("notes", noteDao.findById(id));
        model.addAttribute("users", userDao.findById(id));
        model.addAttribute("props", propDao.findById(id));
        return "notes/show";
    }

}
