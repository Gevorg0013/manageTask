/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.task.model;

import javax.persistence.Column;
import lombok.Data;

/**
 *
 * @author user
 */
@Data
public class UserPayload {

    @Column(unique = true)
    private String email;
    private String firstName;
    private UserRole role;
    private String lastName;
    private String password;
}
