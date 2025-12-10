package org.example.backend;

import org.example.backend.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;

import java.util.Optional;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

    @InjectMocks
    private SpringDataFxApplication springDataFxApplication;

    @Mock private OwnerRepository ownerRepository;
    @Mock private AnimalRepository animalRepository;
    @Mock private MedicalEventRepository medicalEventRepository;

    // MOCK ENTITIES
    private final Long OWNER_ID = 10L;
    private final Owner MOCK_OWNER = Owner.builder().id(OWNER_ID).name("Tulajdonos").build();

    // CREATE
    @Test
    void testSaveAnimal_WithValidOwner() {
        when(ownerRepository.findById(OWNER_ID)).thenReturn(Optional.of(MOCK_OWNER));

        springDataFxApplication.saveAnimal("Rex", "Juhász", 5, "Fájós láb", OWNER_ID.intValue());

        verify(animalRepository, times(1)).save(argThat(animal ->
                        animal.getName().equals("Rex") &&
                        animal.getBreed().equals("Juhász") &&
                        animal.getAge().equals(5) &&
                        animal.getDiagnose().equals("Fájós láb") &&
                        animal.getOwner().getId().equals(OWNER_ID)
        ));
    }

    // READ
    @Test
    void testGetAnimalById_Found() {
        Long animalId = 20L;
        Animal mockAnimal = Animal.builder().id(animalId).name("Mock Dog").build();
        when(animalRepository.findById(animalId)).thenReturn(Optional.of(mockAnimal));

        Animal found = springDataFxApplication.getAnimalById(animalId.intValue());

        assertThat(found.getName()).isEqualTo("Mock Dog");
        verify(animalRepository, times(1)).findById(animalId);
    }

    @Test
    void testSearchAnimals_WithTerm() {
        String searchTerm = "juhász";

        springDataFxApplication.searchAnimals(searchTerm);

        verify(animalRepository, times(1)).searchAnimals(eq("%juhász%"));
    }

    // UPDATE
    @Test
    void testUpdateAnimal_ShouldAssignNewOwnerAndSave() {
        Long animalId = 30L;
        Long newOwnerId = 15L;

        Animal animalToUpdate = Animal.builder().id(animalId).name("Fülöp").build();
        Owner newMockOwner = Owner.builder().id(newOwnerId).name("Tóth Jakab").build();

        when(ownerRepository.findById(newOwnerId)).thenReturn(Optional.of(newMockOwner));

        springDataFxApplication.updateAnimal(animalToUpdate, newOwnerId.intValue());

        verify(animalRepository, times(1)).save(argThat(animal ->
                animal.getOwner().getId().equals(newOwnerId) &&
                animal.getName().equals("Fülöp")
        ));
    }

    // DELETE
    @Test
    void testDeleteAnimalById() {
        Long idToDelete = 40L;
        springDataFxApplication.deleteAnimalById(idToDelete);
        verify(animalRepository, times(1)).deleteById(idToDelete);
    }
}