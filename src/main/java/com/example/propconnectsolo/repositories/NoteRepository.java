package com.example.propconnectsolo.repositories;

import com.example.propconnectsolo.models.Note;
import com.example.propconnectsolo.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Note findById(long id);

    List<Note> findNotesByProperty(Property property);

    @Query("from Note a where a.title LIKE  %:term% OR a.body LIKE  %:term% OR a.category like %:term% OR a.property.city LIKE %:term% OR a.property.state LIKE %:term% OR a.property.street_add LIKE %:term%")
    List<Note> searchByTitleLike(@Param("term") String term);


}
