/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.task.controller;

import com.example.task.model.Task;
import com.example.task.model.TaskPayload;
import com.example.task.model.TaskResponse;
import com.example.task.service.TaskService;
import io.swagger.models.Response;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/task")
public class TaskController {
    
    @Autowired
    TaskService taskService;
    
    @PostMapping(value = "/createTask")
    public ResponseEntity<?> create(
    @RequestBody TaskPayload taskPayload,
    @RequestParam String token
    ) {
        Optional<TaskResponse> create = taskService.create(taskPayload, token);
        if(create.isPresent()) {
            return ResponseEntity.ok(create.get());
        } else {
            return ResponseEntity.badRequest().body("api failed");
        }
        
    }
    
    @GetMapping(value = "/getTask")
    public void get(final String token) {
        
            
    }
}
