package org.example.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.model.Owner;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@ToString(exclude = {"medicalEvents", "owner"})
public class Animal {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String breed;
    Integer age;
    String diagnose;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MedicalEvent> medicalEvents;
}
