package com.example.propconnectsolo.repositories;

import com.example.propconnectsolo.models.Note;
import com.example.propconnectsolo.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Note findById(long id);

    List<Note> findNotesByProperty(Property property);

}
