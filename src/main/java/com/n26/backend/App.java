package com.n26.backend;


public class App {

    public static void main(String[] args) {
        BackendHttpServer server = new BackendHttpServer();

        try {
            server.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
