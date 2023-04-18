package com.example.propconnectsolo.repositories;

import com.example.propconnectsolo.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Property findById(long id);


}
