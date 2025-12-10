package org.example.backend;

import org.example.backend.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicalEventServiceTest {

    @InjectMocks
    private SpringDataFxApplication springDataFxApplication;

    @Mock private OwnerRepository ownerRepository;
    @Mock private AnimalRepository animalRepository;
    @Mock private MedicalEventRepository medicalEventRepository;

    // MOCK ENTITIES
    private final Long ANIMAL_ID = 50L;
    private final Animal MOCK_ANIMAL = Animal.builder().id(ANIMAL_ID).name("Fülöp").build();
    private final LocalDate EVENT_DATE = LocalDate.of(2025, 12, 11);

    // CREATE
    @Test
    void testSaveMedicalEvent_WithValidAnimal() {
        when(animalRepository.findById(ANIMAL_ID)).thenReturn(Optional.of(MOCK_ANIMAL));

        springDataFxApplication.saveMedicalEvent(
                ANIMAL_ID.intValue(),
                "Oltás",
                EVENT_DATE,
                "Dr. Kovács"
        );

        verify(medicalEventRepository, times(1)).save(argThat(event ->
                event.getType().equals("Oltás") &&
                event.getDate().equals(EVENT_DATE) &&
                event.getAnimal().getId().equals(ANIMAL_ID) &&
                event.getName().equals("Dr. Kovács")
        ));
    }

    // READ
    @Test
    void testGetMedicalEvents_ShouldCallFindAllWithAnimals() {
        springDataFxApplication.getMedicalEvents();
        verify(medicalEventRepository, times(1)).findAllWithAnimals();
    }

    @Test
    void testSearchMedicalEvents_ShouldCallRepositorySearch() {
        String searchTerm = "vizsgálat";

        when(medicalEventRepository.findAllWithAnimals()).thenReturn(List.of());

        springDataFxApplication.searchMedicalEvents(searchTerm);

        verify(medicalEventRepository, times(1)).searchMedicalEvents(eq("vizsgálat"));
    }

    // UPDATE
    @Test
    void testUpdateMedicalEvent_ShouldCallRepositorySave() {
        MedicalEvent eventToUpdate = MedicalEvent.builder().id(60L).type("Új vizsgálat").build();
        springDataFxApplication.updateMedicalEvent(eventToUpdate);
        verify(medicalEventRepository, times(1)).save(eventToUpdate);
    }

    // DELETE
    @Test
    void testDeleteMedicalEventById() {
        Long idToDelete = 70L;
        springDataFxApplication.deleteMedicalEventById(idToDelete);
        verify(medicalEventRepository, times(1)).deleteById(idToDelete);
    }
}