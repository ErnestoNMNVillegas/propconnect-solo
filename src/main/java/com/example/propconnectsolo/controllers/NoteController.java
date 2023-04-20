package com.example.propconnectsolo.controllers;

import com.example.propconnectsolo.models.Note;
import com.example.propconnectsolo.models.Property;
import com.example.propconnectsolo.repositories.NoteRepository;
import com.example.propconnectsolo.repositories.PropertyRepository;
import com.example.propconnectsolo.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("notes/create/{id}")
    public String createNoteForm(@PathVariable long id, Model model){
        Property property = propDao.findById(id);
        Note note = new Note();
        note.setProperty(property);
        model.addAttribute("note", note);
        model.addAttribute("propId", id);
        return "notes/create";
    }

    @PostMapping("notes/create")
    public String createNote(@RequestParam(name="propId") long id, @ModelAttribute Note note){
        Property property = propDao.findById(id);
        note.setProperty(property);
        noteDao.save(note);
        return "redirect:/notes";
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

    @GetMapping("/notes/search")
    public String seachResults(@RequestParam(name="q") String query, Model model){
        model.addAttribute("notes", noteDao.searchByTitleLike(query));
        return "notes/index";
    }

    @GetMapping("/notes/{id}/delete")
    public String confirmDelete(@PathVariable long id, Model model){
        model.addAttribute("notes", noteDao.findById(id));
        return "notes/delete";
    }

    @PostMapping("/notes/{id}/delete")
    public String deleteNote(@PathVariable long id, @RequestParam(name="note-id") long noteId) {
        if (id == noteId){
            Note note = noteDao.findById(id);
            noteDao.delete(note);
        }
        return "redirect:/notes";
    }

    @GetMapping("/notes/{id}/edit")
    public String editNoteForm(Model model, @PathVariable long id){
        Note note = noteDao.findById(id);
        model.addAttribute("notes", note);
        return "notes/edit-note";
    }

    @PostMapping("/notes/edit")
    public String editNote(@ModelAttribute Note note){
        noteDao.save(note);
        return "redirect:/notes";
    }

}
