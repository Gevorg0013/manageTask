/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.task.service;

import com.example.task.model.Task;
import com.example.task.model.TaskPayload;
import com.example.task.model.TaskResponse;
import com.example.task.repository.TaskRepository;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service("TaskService")
@Slf4j
public class TaskServiceImpl implements TaskService  {

    @Autowired
    TaskRepository taskrepo;

    @Autowired
    TokenGenerator tokenVerify;
    @Override
    public Optional<TaskResponse> create( TaskPayload taskPayload, String token){
        boolean verifyToken = tokenVerify.verifyToken(token,"0013");
        if (verifyToken == false) {
            return Optional.empty();
        }
        Task task = new Task();
        log.info("decription013: " + taskPayload.getDescription());
        task.setDescription(taskPayload.getDescription());
        task.setTaskShortName(taskPayload.getTaskShortName());
        task.setTime(LocalDateTime.now());
        Task save = taskrepo.save(task);

        String number = save.getId().toString() + "-th task:";
        return Optional.of(new TaskResponse(save.getTaskShortName(), number, save.getDescription(), save.getTime()));
    }

}
