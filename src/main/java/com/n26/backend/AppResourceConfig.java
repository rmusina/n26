package com.n26.backend;


import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;


class AppResourceConfig extends ResourceConfig {
    AppResourceConfig() {
        register(new AppBinder());
        register(JacksonFeature.class);
        packages("com.n26.backend.controllers");
    }
}
