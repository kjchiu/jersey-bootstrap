package com.kjchiu.jetty;

import org.glassfish.jersey.server.ResourceConfig;

import java.util.ArrayList;
import java.util.List;

public class ServiceBuilder {

    private int port = 80;

    private List<Object> resources;

    public ServiceBuilder() {
        resources = new ArrayList<>();
    }

    public ServiceBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    /**
     * Add jaxrs resource
     * @param resource
     * @return service builder
     */
    public ServiceBuilder addResource(Object resource) {
        resources.add(resource);
        return this;
    }

    /**
     * Build service
     * @return configured service
     */
    public Service build() {

        ResourceConfig config = new ResourceConfig();
        resources.forEach(config::register);
        return new Service(port, config);

    }
}
