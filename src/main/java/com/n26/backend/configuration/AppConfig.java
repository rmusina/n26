package com.n26.backend.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppConfig {
    @JsonProperty
    public int port;

    @JsonProperty("interval_size_seconds")
    public int intervalSizeSeconds;

    @JsonProperty("log_level")
    public String logLevel;
}
