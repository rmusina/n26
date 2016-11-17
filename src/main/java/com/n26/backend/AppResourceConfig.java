package com.n26.backend;


import com.n26.backend.configuration.AppConfig;
import com.n26.backend.exceptions.ConstraintValidationExceptionMapper;
import com.n26.backend.exceptions.GenericExceptionMapper;
import com.n26.backend.exceptions.WebApplicationExceptionMapper;
import org.eclipse.jetty.util.log.Log;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.validation.ValidationFeature;


class AppResourceConfig extends ResourceConfig {
    AppResourceConfig(AppConfig config) {
        register(new AppBinder(config));
        register(JacksonFeature.class);
        register(ValidationFeature.class);

        register(LoggingFeature.class);
        register(ConstraintValidationExceptionMapper.class);
        register(GenericExceptionMapper.class);
        register(WebApplicationExceptionMapper.class);

        property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, config.logLevel);
        property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_SERVER, LoggingFeature.Verbosity.PAYLOAD_ANY);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        packages("com.n26.backend.controllers");
    }
}
