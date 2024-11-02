package com.gaw.dvdrental.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private long id;

  @NotEmpty(message = "Name should not be empty")
  private String name;

  @NotEmpty(message = "Email should not be empty")
  @Email
  private String email;

  @NotEmpty(message = "Password should not be empty")
  private String password;
}
