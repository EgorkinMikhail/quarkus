package com.egorkin.tutorial.service;

import com.egorkin.tutorial.model.GreetingResponse;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class GreetingService {

  @ConfigProperty(name = "greeting.text")
  private String greeting;

  DieRollerService dieRollerService;

  public GreetingService(DieRollerService dieRollerService) {
    this.dieRollerService = dieRollerService;
  }

  public GreetingResponse getGreeting() {
    GreetingResponse response = new GreetingResponse();
    response.setGreeting(this.greeting);
    response.setDieRoll(this.dieRollerService.getRoll());
    return response;
  }

}
