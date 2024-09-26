package com.theo.ecommerce_back.service;

import com.theo.ecommerce_back.api.model.LoginBody;
import com.theo.ecommerce_back.api.model.RegistrationBody;
import com.theo.ecommerce_back.exception.UserAlreadyExistException;
import com.theo.ecommerce_back.model.LocalUser;
import com.theo.ecommerce_back.repository.LocalUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private LocalUserRepository localUserRepository;
    private EncryptionService encryptionService;
    private JWTService jwtService;

    public UserService(LocalUserRepository localUserRepository, EncryptionService encryptionService, JWTService jwtService){
        this.localUserRepository = localUserRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistException {

        if(localUserRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()
                || localUserRepository.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()){
            throw new UserAlreadyExistException();
        }

        LocalUser user = new LocalUser();

        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());

        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));

        return localUserRepository.save(user);
    }

    public String loginUser(LoginBody loginBody){
        Optional<LocalUser> user = localUserRepository.findByUsernameIgnoreCase(loginBody.getUsername());
        if(user.isPresent()){
            if(encryptionService.verifyPassword(loginBody.getPassword(), user.get().getPassword())){
                return jwtService.generateJWT(user.get());
            }
        }
        return null;
    }


















}
