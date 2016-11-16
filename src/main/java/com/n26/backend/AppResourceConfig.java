package com.n26.backend;


import org.glassfish.jersey.server.ResourceConfig;

public class AppResourceConfig extends ResourceConfig {
    public AppResourceConfig() {
        register(new AppBinder());
        packages("com.n26.backend.controllers");
    }
}
