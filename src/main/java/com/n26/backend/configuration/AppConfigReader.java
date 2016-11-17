package com.n26.backend.configuration;


import java.io.File;
import java.io.IOException;

public interface AppConfigReader {
    AppConfig readConfig(File file) throws IOException;
}
