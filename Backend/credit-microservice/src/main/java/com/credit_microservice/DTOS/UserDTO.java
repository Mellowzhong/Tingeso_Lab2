package com.credit_microservice.DTOS;

import com.credit_microservice.entities.Credit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String rut;
    private String address;
    private int age;
    List<Credit> credits;
}
