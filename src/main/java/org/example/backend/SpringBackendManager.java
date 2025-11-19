package org.example.backend;

import org.example.frontend.BackendManager;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringBackendManager implements BackendManager {
    private ConfigurableApplicationContext ctx;

    @Override
    public void start() {
        if ((ctx!=null)&&(ctx.isActive())) return;
        ctx = SpringApplication.run(SpringDataFxApplication.class);
    }

    @Override
    public void stop() {
        ctx.close();
    }

}
