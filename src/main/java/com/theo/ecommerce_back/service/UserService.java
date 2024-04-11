package com.theo.ecommerce_back.service;

import com.theo.ecommerce_back.api.model.RegistrationBody;
import com.theo.ecommerce_back.exception.UserAlreadyExistException;
import com.theo.ecommerce_back.model.LocalUser;
import com.theo.ecommerce_back.repository.LocalUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private LocalUserRepository localUserRepository;

    public UserService(LocalUserRepository localUserRepository){
        this.localUserRepository = localUserRepository;
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

        //TODO: Encriptar contraseña
        user.setPassword(registrationBody.getPassword());

        return localUserRepository.save(user);
    }

}
