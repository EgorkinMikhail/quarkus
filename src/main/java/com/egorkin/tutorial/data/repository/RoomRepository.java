package com.egorkin.tutorial.data.repository;

import com.egorkin.tutorial.data.entity.RoomEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoomRepository implements PanacheRepository<RoomEntity > {
}
