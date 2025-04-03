package com.egorkin.tutorial.model;

public class GreetingResponse {

  private String greeting;
  int dieRoll;

  public String getGreeting() {
    return greeting;
  }

  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }

  public int getDieRoll() {
    return dieRoll;
  }

  public void setDieRoll(int dieRoll) {
    this.dieRoll = dieRoll;
  }
}
