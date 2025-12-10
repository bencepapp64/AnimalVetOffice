package org.example.backend.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalEventRepository extends JpaRepository<MedicalEvent,Long> {
    @Query("SELECT m FROM MedicalEvent m LEFT JOIN FETCH m.animal")
    List<MedicalEvent> findAllWithAnimals();

    @Query("SELECT m FROM MedicalEvent m LEFT JOIN FETCH m.animal WHERE " +
            "LOWER(m.type) LIKE %:searchTerm% OR " +
            "LOWER(m.name) LIKE %:searchTerm% OR " +
            "LOWER(m.animal.name) LIKE %:searchTerm%")
    List<MedicalEvent> searchMedicalEvents(@Param("searchTerm") String searchTerm);
}
