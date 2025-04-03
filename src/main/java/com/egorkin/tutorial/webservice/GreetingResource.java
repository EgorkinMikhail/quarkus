package com.egorkin.tutorial.webservice;

import com.egorkin.tutorial.model.GreetingResponse;
import com.egorkin.tutorial.service.GreetingService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    GreetingService greetingService;

    public GreetingResource(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GET
    public GreetingResponse hello() {
        return this.greetingService.getGreeting();
    }
}
