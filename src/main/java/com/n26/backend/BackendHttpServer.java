package com.n26.backend;


import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;


class BackendHttpServer {

    void startServer() throws Exception {
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        ServletContainer jerseyServletContainer = new ServletContainer(new AppResourceConfig());
        ServletHolder jerseyServletHolder = new ServletHolder(jerseyServletContainer);
        servletContextHandler.setContextPath("/");
        servletContextHandler.addServlet(jerseyServletHolder, "/*");

        HandlerCollection handlerList = new HandlerCollection();
        handlerList.setHandlers(new Handler[]{ servletContextHandler });

        Server server = new Server(8082);
        server.setHandler(handlerList);
        server.start();
        server.join();
    }
}
