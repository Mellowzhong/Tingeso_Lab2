package com.microservice.userMicroservice.Repositories;

import com.microservice.userMicroservice.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByFirstNameAndLastNameAndRut(String firstName, String lastName, String rut);
}
