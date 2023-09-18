/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.task.service;

import com.example.task.model.TaskPayload;
import com.example.task.model.TaskResponse;
import java.util.Optional;

/**
 *
 * @author user
 */
public interface TaskService {

    Optional<TaskResponse> create(final TaskPayload taskPayload,final String token);

}
