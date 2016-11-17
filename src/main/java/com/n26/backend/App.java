package com.n26.backend;


import com.n26.backend.configuration.AppConfig;
import com.n26.backend.configuration.AppConfigReader;
import com.n26.backend.configuration.YamlAppConfigReader;

import java.io.File;

public class App {

    public static void main(String[] args) {
        BackendHttpServer server = new BackendHttpServer();
        AppConfigReader configReader = new YamlAppConfigReader();

        try {
            AppConfig config = configReader.readConfig(new File("config.yaml"));
            server.startServer(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
