package org.example.backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.backend.model.Owner;
import org.example.backend.model.OwnerRepository;
import org.example.frontend.BackendManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

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

}
