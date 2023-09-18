
package com.example.task.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author gevorga
 * 
 * 
 */
@Table(name = "task")
@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String taskShortName;
    private String description;
    LocalDateTime time;
}
