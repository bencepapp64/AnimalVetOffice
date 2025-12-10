package org.example.backend.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query("SELECT o FROM Owner o LEFT JOIN FETCH o.animals")
    List<Owner> findAllWithAnimals();

    @Query("SELECT o FROM Owner o WHERE " +
            "LOWER(o.name) LIKE %:searchTerm% OR " +
            "LOWER(o.phone) LIKE %:searchTerm% OR " +
            "LOWER(o.email) LIKE %:searchTerm%")
    List<Owner> searchOwners(@Param("searchTerm") String searchTerm);
}
