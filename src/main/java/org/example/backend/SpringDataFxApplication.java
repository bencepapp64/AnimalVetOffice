package org.example.backend;

import org.example.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

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
        return ownerRepository.findAll();
    }

    public void deleteOwnerById(Long id){
        ownerRepository.deleteById(id);
    }

    public void updateOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    public void saveAnimal(String name, String breed, Integer age, String diagnose) {
        Animal a = Animal.builder().name(name).breed(breed).age(age).diagnose(diagnose).build();
        animalRepository.save(a);
    }

    public List<Animal> getAnimals(){
        return animalRepository.findAll();
    }

    public void deleteAnimalById(Long id){
        animalRepository.deleteById(id);
    }

    public void updateAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    public void saveMedicalEvent(Integer animalId, String type, LocalDate date, String name) {
        MedicalEvent m =  MedicalEvent.builder().animalId(animalId).type(type).date(date).name(name).build();
        medicalEventRepository.save(m);
    }

    public List<MedicalEvent> getMedicalEvents(){
        return medicalEventRepository.findAll();
    }

    public void deleteMedicalEventById(Long id){
        medicalEventRepository.deleteById(id);
    }

    public void updateMedicalEvent(MedicalEvent medicalEvent) {
        medicalEventRepository.save(medicalEvent);
    }
}
