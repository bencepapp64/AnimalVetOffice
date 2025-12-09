package org.example.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.model.Animal;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@ToString(exclude = "animals")
public class Owner {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String phone;
    String email;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Animal> animals;
}
