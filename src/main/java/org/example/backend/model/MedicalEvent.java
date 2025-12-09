package org.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@ToString
public class MedicalEvent {
    @Id
    @GeneratedValue
    Long id;
//    Integer animalId;
    String type;
    LocalDate date;
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;
}
