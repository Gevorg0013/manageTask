/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.task.controller;

/**
 *
 * @author user
 */
import com.example.task.model.UserPayload;
import com.example.task.model.UserRegister;
import com.example.task.model.UserResponse;
import com.example.task.repository.UserRepository;
import com.example.task.service.TokenGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@Slf4j
@RequestMapping(value = "/test")

public class UserRegisterController {

   
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestBody UserPayload payload) throws NoSuchAlgorithmException {
        UserRegister user = new UserRegister();
        Optional<UserRegister> findByEmail = userRepository.findByEmail(payload.getEmail());
        if (findByEmail.isPresent()) {
            log.info("user with this email is already exist");
            return ResponseEntity.badRequest().body("bad credentials");
        }
        user.setFirstName(payload.getFirstName());
        user.setEmail(payload.getEmail());
        user.setLastName(payload.getLastName());
        user.setRole(payload.getRole());
        String hashedPW = BCrypt.hashpw(payload.getPassword(), BCrypt.gensalt());
        boolean checkpw = BCrypt.checkpw(payload.getPassword(), hashedPW); //always returns false
        log.info("boolean value is  = " + checkpw);
        log.info("hashedPW = " + hashedPW);
        user.setPassword(hashedPW);
        UserRegister save = userRepository.save(user);
        
        return ResponseEntity.ok(save);
    }
    
    @GetMapping(value = "/signIn")
    public ResponseEntity<?> signIn(
            @RequestParam final String password,
            @RequestParam final String email
    ) throws NoSuchAlgorithmException {
        String generateToken = tokenGenerator.generateToken(email);
        Optional<UserRegister> findByEmail = userRepository.findByEmail(email);
        if (findByEmail.isPresent()) {
            log.info("email is correct:" + "passWord from payload = " + password + "    password from db = " + findByEmail.get().getPassword());
            boolean checkpw = BCrypt.checkpw(password, findByEmail.get().getPassword());
            if (checkpw == true) {
                UserResponse response = new UserResponse();
                response.setFirstName(findByEmail.get().getFirstName());
                response.setLastName(findByEmail.get().getLastName());
                response.setEmail(findByEmail.get().getEmail());
                response.setRole(findByEmail.get().getRole());
                response.setToken(generateToken);
                response.setPassword(findByEmail.get().getPassword());
                
                return ResponseEntity.ok().body(response);
            }
        }
        
        return ResponseEntity.badRequest().body("can't find user by this criteria");
    }
}
