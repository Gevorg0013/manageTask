/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.task.repository;

import com.example.task.model.UserRegister;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface UserRepository extends JpaRepository<UserRegister, Long> {
//    Optional<User> findByUsernameIgnoreCase(String userName);
    Optional<UserRegister> findByPassword(String password);
    Optional<UserRegister> findByEmail(String email);
    
    
}