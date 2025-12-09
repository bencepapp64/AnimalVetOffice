package org.example.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.model.Owner;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Animal {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String breed;
    Integer age;
    String diagnose;
//    Integer ownerId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
