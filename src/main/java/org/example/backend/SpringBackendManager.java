package org.example.backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.backend.model.Owner;
import org.example.frontend.BackendManager;
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

}
