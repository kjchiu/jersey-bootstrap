package com.kjchiu.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Service {

    
    private static final Logger logger = LoggerFactory.getLogger(Service.class);
    private int port;
    private Server server;
    private ResourceConfig config;

    public Service(int port, ResourceConfig config) {
        this.port = port;
        this.config = config;

    }

    public void run() throws Exception {

        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.setContextPath("/");

        ServletContainer container = new ServletContainer(config);

        ServletHolder servlet = new ServletHolder(container);
        servletContextHandler.addServlet(servlet, "/*");

        server = new Server(port);
        server.setHandler(servletContextHandler);
        logger.debug("Listening on port {}", port);
        server.start();
        server.join();

    }

}
