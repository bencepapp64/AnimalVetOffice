package org.example.backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.backend.model.Animal;
import org.example.backend.model.MedicalEvent;
import org.example.backend.model.Owner;
import org.example.backend.model.OwnerRepository;
import org.example.frontend.BackendManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class SpringBackendManager implements BackendManager {
    private ConfigurableApplicationContext ctx;

    @Override
    public void start() {
        if ((ctx!=null)&&(ctx.isActive())) return;


        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });

        ctx = SpringApplication.run(SpringDataFxApplication.class);



    }

    @Override
    public void stop() {
        ctx.close();
    }

    @Override
    public void test() {
        ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).test();
    }

    @Override
    public void saveOwner(String name, String phone, String email) {
        ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).saveOwner(name, phone, email);
    }

    public List<Owner> getOwners() {
        return ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).getOwners();

    }
    public void deleteOwnerById(Long id) {
        ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).deleteOwnerById(id);

    }

    public void updateOwner(Owner owner) {
        ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).updateOwner(owner);
    }

    @Override
    public Owner getOwnerById(Integer ownerId) {
        return ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).getOwnerById(ownerId);
    }

    @Override
    public List<Owner> searchOwners(String searchTerm) {
        return ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).searchOwners(searchTerm);
    }

    @Override
    public void saveAnimal(String name, String breed, Integer age, String diagnose, Integer ownerId) {
        ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).saveAnimal(name, breed, age, diagnose, ownerId);
    }

    @Override
    public List<Animal> getAnimals() {
        return ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).getAnimals();
    }

    @Override
    public void deleteAnimalById(Long id) {
        ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).deleteAnimalById(id);
    }

    @Override
    public void updateAnimal(Animal animal, Integer ownerId) {
        ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).updateAnimal(animal, ownerId);
    }

    @Override
    public Animal getAnimalById(Integer animalId) {
        return ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).getAnimalById(animalId);
    }

    @Override
    public List<Animal> searchAnimals(String searchTerm) {
        return ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).searchAnimals(searchTerm);
    }

    @Override
    public void saveMedicalEvent(Integer animalId, String type, LocalDate date, String name) {
        ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).saveMedicalEvent(animalId, type, date, name);
    }

    @Override
    public List<MedicalEvent> getMedicalEvents() {
        return ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).getMedicalEvents();
    }

    @Override
    public void deleteMedicalEventById(Long id) {
        ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).deleteMedicalEventById(id);
    }

    @Override
    public void updateMedicalEvent(MedicalEvent medicalEvent) {
        ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).updateMedicalEvent(medicalEvent);
    }

    @Override
    public List<Integer> getNumbersOfOwnersAnimalsEvents() {
        return ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).getNumbersOfOwnersAnimalsEvents();
    }
}
