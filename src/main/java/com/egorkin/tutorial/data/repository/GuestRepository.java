package com.egorkin.tutorial.data.repository;

import com.egorkin.tutorial.data.entity.GuestEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GuestRepository  implements PanacheRepository<GuestEntity> {
}
