package com.egorkin.tutorial.service;

import com.egorkin.tutorial.data.entity.RoomEntity;
import com.egorkin.tutorial.data.repository.RoomRepository;
import com.egorkin.tutorial.model.GreetingResponse;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GreetingService {

  @ConfigProperty(name = "greeting.text")
  private String greeting;

  private final DieRollerService dieRollerService;
  private final RoomRepository roomRepository;

  public GreetingService(DieRollerService dieRollerService, RoomRepository roomRepository) {
    this.dieRollerService = dieRollerService;
    this.roomRepository = roomRepository;
  }

  public GreetingResponse getGreeting() {
    GreetingResponse response = new GreetingResponse();
    response.setGreeting(this.greeting);
    response.setDieRoll(this.dieRollerService.getRoll());
    List<RoomEntity> rooms = this.roomRepository.listAll();
    List<String> roomNumbers = new ArrayList<>();
    rooms.forEach(room -> roomNumbers.add(room.getRoomNumber()));
    response.setRoomNumbers(roomNumbers);
    return response;
  }

}
