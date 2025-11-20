package org.example.backend.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalEventRepository extends JpaRepository<MedicalEvent,String> {
}
