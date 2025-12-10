package org.example.backend;

import org.example.backend.model.AnimalRepository;
import org.example.backend.model.MedicalEventRepository;
import org.example.backend.model.Owner;
import org.example.backend.model.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceTest {

    @InjectMocks
    private SpringDataFxApplication springDataFxApplication;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private AnimalRepository animalRepository;
    @Mock
    private MedicalEventRepository medicalEventRepository;

    // CREATE
    @Test
    void testSaveOwner_ShouldCallRepositorySaveWithCorrectData() {
        String name = "Nagy István";
        String phone = "06201234567";
        String email = "nagy.i@example.com";

        springDataFxApplication.saveOwner(name, phone, email);

        ArgumentCaptor<Owner> ownerCaptor = ArgumentCaptor.forClass(Owner.class);

        verify(ownerRepository, times(1)).save(ownerCaptor.capture());

        Owner capturedOwner = ownerCaptor.getValue();
        assertThat(capturedOwner.getName()).isEqualTo(name);
        assertThat(capturedOwner.getEmail()).isEqualTo(email);
    }

    // READ
    @Test
    void testGetOwnerById_WhenFound() {
        Long ownerId = 1L;
        Owner mockOwner = Owner.builder().id(ownerId).name("Tulajdonos1").build();

        when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(mockOwner));

        Owner foundOwner = springDataFxApplication.getOwnerById(ownerId.intValue());

        assertThat(foundOwner).isNotNull();
        assertThat(foundOwner.getName()).isEqualTo("Tulajdonos1");
    }

    @Test
    void testGetOwnerById_WhenNotFound() {
        Long nonExistentId = 99L;

        when(ownerRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        Owner foundOwner = springDataFxApplication.getOwnerById(nonExistentId.intValue());

        assertThat(foundOwner).isNull();
    }

    @Test
    void testSearchOwners_ShouldCallRepositoryWithCorrectTerm() {
        String searchTerm = "valami";

        springDataFxApplication.searchOwners(searchTerm);

        verify(ownerRepository, times(1)).searchOwners(eq("%valami%"));
    }

    // UPDATE
    @Test
    void testUpdateOwner_ShouldCallRepositorySave() {
        Owner ownerToUpdate = Owner.builder().id(2L).name("Szabó János").build();

        springDataFxApplication.updateOwner(ownerToUpdate);

        verify(ownerRepository, times(1)).save(ownerToUpdate);
    }

    // DELETE
    @Test
    void testDeleteOwnerById_ShouldCallRepositoryDeleteById() {
        Long idToDelete = 3L;

        springDataFxApplication.deleteOwnerById(idToDelete);

        verify(ownerRepository, times(1)).deleteById(idToDelete);
    }
}