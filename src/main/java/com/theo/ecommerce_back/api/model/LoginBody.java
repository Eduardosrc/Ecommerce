package com.theo.ecommerce_back.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginBody {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
