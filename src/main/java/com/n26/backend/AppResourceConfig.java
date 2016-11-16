package com.n26.backend;


import com.n26.backend.exceptions.ConstraintValidationExceptionMapper;
import com.n26.backend.exceptions.GenericExceptionMapper;
import com.n26.backend.exceptions.WebApplicationExceptionMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.validation.ValidationFeature;


class AppResourceConfig extends ResourceConfig {
    AppResourceConfig() {
        register(new AppBinder());
        register(JacksonFeature.class);
        register(ValidationFeature.class);

        register(ConstraintValidationExceptionMapper.class);
        register(GenericExceptionMapper.class);
        register(WebApplicationExceptionMapper.class);

        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        packages("com.n26.backend.controllers");
    }
}
