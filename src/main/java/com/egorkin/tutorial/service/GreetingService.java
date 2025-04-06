package com.egorkin.tutorial.service;

import com.egorkin.tutorial.data.entity.GuestEntity;
import com.egorkin.tutorial.data.entity.RoomEntity;
import com.egorkin.tutorial.data.repository.GuestRepository;
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
  private final GuestRepository guestRepository;

  public GreetingService(DieRollerService dieRollerService, RoomRepository roomRepository, GuestRepository guestRepository) {
    this.dieRollerService = dieRollerService;
    this.roomRepository = roomRepository;
    this.guestRepository = guestRepository;
  }

  public GreetingResponse getGreeting() {
    GreetingResponse response = new GreetingResponse();
    response.setGreeting(this.greeting);
    response.setDieRoll(this.dieRollerService.getRoll());
    List<RoomEntity> rooms = this.roomRepository.listAll();
    List<String> roomNumbers = new ArrayList<>();
    rooms.forEach(room -> roomNumbers.add(room.getRoomNumber()));
    response.setRoomNumbers(roomNumbers);
    List<GuestEntity> guests = this.guestRepository.listAll();
    List<String> guestEmails = new ArrayList<>();
    response.setGuestEmails(guestEmails);
    return response;
  }

}
