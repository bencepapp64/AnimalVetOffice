package org.example.backend.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Long> {
    @Query("SELECT a FROM Animal a LEFT JOIN FETCH a.owner")
    List<Animal> findAllWithOwners();
}
