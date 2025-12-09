package org.example.frontend;

import org.example.backend.model.Animal;
import org.example.backend.model.Owner;
import org.example.backend.model.MedicalEvent;

import java.time.LocalDate;
import java.util.List;

public interface BackendManager {
    public void start();
    public void stop();
    public void test();

    public void saveOwner(String name, String phone, String email);
    public List<Owner> getOwners();
    public void deleteOwnerById(Long id);
    public void updateOwner(Owner owner);

    public void saveAnimal(String name, String breed, Integer age, String diagnose);
    public List<Animal> getAnimals();
    public void deleteAnimalById(Long id);
    public void updateAnimal(Animal animal);
    public Animal getAnimalById(Integer animalId);

    public void saveMedicalEvent(Integer animalId, String type, LocalDate date, String name);
    public List<MedicalEvent> getMedicalEvents();
    public void deleteMedicalEventById(Long id);
    public void updateMedicalEvent(MedicalEvent medicalEvent);
}
