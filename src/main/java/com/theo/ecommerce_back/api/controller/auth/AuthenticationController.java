package com.theo.ecommerce_back.api.controller.auth;

import com.theo.ecommerce_back.api.model.LoginBody;
import com.theo.ecommerce_back.api.model.LoginResponse;
import com.theo.ecommerce_back.api.model.RegistrationBody;
import com.theo.ecommerce_back.exception.UserAlreadyExistException;
import com.theo.ecommerce_back.model.LocalUser;
import com.theo.ecommerce_back.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<LocalUser> registerUser(@Valid @RequestBody RegistrationBody registrationBody){
        try {
            return new ResponseEntity<>(userService.registerUser(registrationBody), HttpStatus.CREATED);
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody){
        String jwt = userService.loginUser(loginBody);
        if(jwt == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }





















}
