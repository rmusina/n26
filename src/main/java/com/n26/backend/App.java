package com.n26.backend;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        BackendHttpServer server = new BackendHttpServer();

        try {
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
