package org.example.backend.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalEventRepository extends JpaRepository<MedicalEvent,Long> {
    @Query("SELECT m FROM MedicalEvent m LEFT JOIN FETCH m.animal")
    List<MedicalEvent> findAllWithAnimals();
}
