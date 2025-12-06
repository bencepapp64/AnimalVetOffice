package org.example.backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.backend.model.Animal;
import org.example.backend.model.Owner;
import org.example.backend.model.OwnerRepository;
import org.example.frontend.BackendManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
    public void saveAnimal(String breed, Integer age, String diagnose) {
        ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).saveAnimal(breed, age, diagnose);
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
    public void updateAnimal(Animal animal) {
        ((SpringDataFxApplication)ctx.getBean(SpringDataFxApplication.class)).updateAnimal(animal);
    }


}
