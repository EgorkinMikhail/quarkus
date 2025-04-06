package com.egorkin.tutorial.webservice;

import com.egorkin.tutorial.data.entity.RoomEntity;
import com.egorkin.tutorial.data.repository.RoomRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/rooms")
@Produces("application/json")
public class RoomResource {

  final RoomRepository roomRepository;

  public RoomResource(RoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }

  @GET
  public List<RoomEntity> getRooms() {
    return this.roomRepository.listAll();
  }

  @POST
  @ResponseStatus(201)
  @Transactional
  public RoomEntity addRoom(RoomEntity roomEntity) {
     this.roomRepository.persist(roomEntity);
     return roomEntity;
  }

  @GET
  @Path("/{id}")
  public RoomEntity getRoom(@RestPath(value = "id") long id) {
    RoomEntity roomEntity = this.roomRepository.findById(id);
    if (roomEntity == null) {
      throw new WebApplicationException(Response.status(404).entity(id + " id does not exists").build());
    }
    return roomEntity;
  }

  @PUT
  @Path("/{id}")
  @ResponseStatus(204)
  @Transactional
  public void setRoom(@RestPath(value = "id") long id, RoomEntity roomEntity) {
    if (roomEntity.getRoomId() != id) {
      throw new WebApplicationException(Response.status(400).entity("ids don't match").build());
    }
    RoomEntity room = this.roomRepository.findById(id);
    room.setRoomNumber(roomEntity.getRoomNumber());
    room.setBedInfo(roomEntity.getBedInfo());
    room.setName(roomEntity.getName());
    this.roomRepository.persist(room);
  }

  @DELETE
  @Path("/{id}")
  @ResponseStatus(205)
  @Transactional
  public void removeRoom(@RestPath(value = "id") long id, RoomEntity roomEntity) {
    this.roomRepository.delete(roomEntity);
  }

}
