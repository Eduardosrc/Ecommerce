package com.theo.ecommerce_back.api.model;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class RegistrationBody {

    @NotBlank
    @Size(min = 3, max = 255)
    private String username;

    @NotBlank
    @Size(max = 32)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Mínimo 6 letras y un número")
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, max = 255)
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 255)
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String lastName;

}
