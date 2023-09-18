package com.example.task.repository;

/**
 *
 * @author gevorga
 */
import com.example.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
