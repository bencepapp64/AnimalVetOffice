package org.example.backend;

import org.example.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringDataFxApplication implements CommandLineRunner {

    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private MedicalEventRepository medicalEventRepository;


    public static void main(String[] args) {
        SpringApplication.run(SpringDataFxApplication.class, args);
    }

    public void run(String... args) throws Exception {

    }

    public void test(){
        System.out.println("test");

        Owner o = new Owner();
        System.out.println("Owner created");

        o.setName("Bonjoasdasdurasd");
        o.setEmail("asd@asdasdaasd.hu");
        o.setPhone("123676789");

        System.out.println("Owner filled");

        ownerRepository.save(o);
    }

    public void saveOwner(String name, String phone, String email) {
        Owner o =  Owner.builder().name(name).phone(phone).email(email).build();
        ownerRepository.save(o);
    }

    public List<Owner> getOwners(){
        return ownerRepository.findAllWithAnimals();
    }

    public void deleteOwnerById(Long id){
        ownerRepository.deleteById(id);
    }

    public void updateOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    public Owner getOwnerById(Integer ownerId) {
        if (ownerId == null) {
            return null;
        }

        Long id = ownerId.longValue();

        Optional<Owner> owner = ownerRepository.findById(id);

        return owner.orElse(null);
    }

    public List<Owner> searchOwners(String searchTerm) { // <-- ÚJ
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return ownerRepository.findAllWithAnimals();
        }
        String normalizedSearchTerm = "%" + searchTerm.toLowerCase().trim() + "%";
        return ownerRepository.searchOwners(normalizedSearchTerm);
    }

    public void saveAnimal(String name, String breed, Integer age, String diagnose, Integer ownerId) {
        Owner owner = getOwnerById(ownerId);
        Animal a = Animal.builder().name(name).breed(breed).age(age).diagnose(diagnose).owner(owner).build();
        animalRepository.save(a);
    }

    public List<Animal> getAnimals(){
        return animalRepository.findAllWithOwners();
    }

    public void deleteAnimalById(Long id){
        animalRepository.deleteById(id);
    }

    public void updateAnimal(Animal animal, Integer ownerId) {
        Owner owner = getOwnerById(ownerId);
        animal.setOwner(owner);
        animalRepository.save(animal);
    }

    public Animal getAnimalById(Integer animalId) {
        if (animalId == null) {
            return null;
        }

        Long id = animalId.longValue();

        Optional<Animal> animal = animalRepository.findById(id);

        return animal.orElse(null);
    }

    public Animal getAnimalByIdForSave(Integer animalId) {
        if (animalId == null) return null;
        return animalRepository.findById(animalId.longValue()).orElse(null);
    }

    public List<Animal> searchAnimals(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return animalRepository.findAllWithOwners();
        }
        String normalizedSearchTerm = "%" + searchTerm.toLowerCase().trim() + "%";
        return animalRepository.searchAnimals(normalizedSearchTerm);
    }

    public void saveMedicalEvent(Integer animalId, String type, LocalDate date, String name) {
        Animal animal = getAnimalByIdForSave(animalId);
        MedicalEvent m =  MedicalEvent.builder().animal(animal).type(type).date(date).name(name).build();
        medicalEventRepository.save(m);
    }

    public List<MedicalEvent> getMedicalEvents(){
        return medicalEventRepository.findAllWithAnimals();
    }

    public void deleteMedicalEventById(Long id){
        medicalEventRepository.deleteById(id);
    }

    public void updateMedicalEvent(MedicalEvent medicalEvent) {
        medicalEventRepository.save(medicalEvent);
    }

    public List<Integer> getNumbersOfOwnersAnimalsEvents() {
        int numbersOfOwners = (int)ownerRepository.count() ;
        int numbersOfAnimals =  (int)animalRepository.count() ;
        int numbersOfEvents =  (int)medicalEventRepository.count() ;

        List<Integer> list = new ArrayList<>();
        list.add(numbersOfOwners);
        list.add(numbersOfAnimals);
        list.add(numbersOfEvents);
        return list;
    }

}
