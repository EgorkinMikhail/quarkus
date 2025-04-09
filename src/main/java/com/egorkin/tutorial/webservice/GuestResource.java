package com.egorkin.tutorial.webservice;

import com.egorkin.tutorial.data.entity.GuestEntity;
import com.egorkin.tutorial.data.entity.RoomEntity;
import com.egorkin.tutorial.data.repository.GuestRepository;
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

@Path("/guests")
@Produces("application/json")
public class GuestResource {

  GuestRepository guestRepository;

  public GuestResource(GuestRepository guestRepository) {
    this.guestRepository = guestRepository;
  }

  @GET
  public List<GuestEntity> getGuests() {
    return this.guestRepository.listAll();
  }

  @POST
  @ResponseStatus(201)
  @Transactional
  public GuestEntity addGuest(GuestEntity guestEntity) {
    this.guestRepository.persist(guestEntity);
    return guestEntity;
  }

  @GET
  @Path("/{id}")
  public GuestEntity getGuest(@RestPath(value = "id") long id) {
    GuestEntity guestEntity = this.guestRepository.findById(id);
    if (guestEntity == null) {
      throw new WebApplicationException(Response.status(404).entity(id + " id does not exists").build());
    }
    return guestEntity;
  }

  @PUT
  @Path("/{id}")
  @ResponseStatus(204)
  @Transactional
  public void setRoom(@RestPath(value = "id") long id, GuestEntity guestEntity) {
    if (guestEntity.getGuestId() != id) {
      throw new WebApplicationException(Response.status(400).entity("ids don't match").build());
    }
    GuestEntity room = this.guestRepository.findById(id);
    room.setAddress(guestEntity.getAddress());
    room.setCountry(guestEntity.getCountry());
    room.setEmailAddress(guestEntity.getEmailAddress());
    room.setFirstName(guestEntity.getFirstName());
    room.setLastName(guestEntity.getLastName());
    room.setPhoneNumber(guestEntity.getPhoneNumber());
    room.setState(guestEntity.getState());
    this.guestRepository.persist(room);
  }

  @DELETE
  @Path("/{id}")
  @ResponseStatus(205)
  @Transactional
  public void removeRoom(@RestPath(value = "id") long id, GuestEntity guestEntity) {
    this.guestRepository.delete(guestEntity);
  }
}
