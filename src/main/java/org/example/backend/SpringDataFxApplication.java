package org.example.backend;

import org.example.backend.model.Owner;
import org.example.backend.model.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringDataFxApplication implements CommandLineRunner {

    @Autowired
    OwnerRepository ownerRepository;



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
}
