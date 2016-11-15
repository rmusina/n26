package com.n26.backend;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.ws.rs.ext.RuntimeDelegate;

import java.io.IOException;
import java.net.InetSocketAddress;

public class BackendHttpServer {

    public HttpServer startServer() throws IOException {
        final HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                server.stop(0);
            }
        }));

        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new JaxRsApplication(), HttpHandler.class);

        server.createContext("127.0.0.1", handler);
        server.start();

        return server;
    }
}
