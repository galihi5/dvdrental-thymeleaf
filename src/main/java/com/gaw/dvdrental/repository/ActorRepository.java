package com.gaw.dvdrental.repository;

import com.gaw.dvdrental.model.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
