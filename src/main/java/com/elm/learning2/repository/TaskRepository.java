package com.elm.learning2.repository;

import com.elm.learning2.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {

    // SELECT * FROM TASK WHERE completed = ? With pageable
    Page<Task> findByCompleted(boolean completed, Pageable pageable);

    // SELECT * FROM TASK WHERE completed = ? without pageable
    List<Task> findByCompleted(boolean completed);

    // SELECT * FROM TASK WHERE title = ?
    List<Task> findByTitle(String title);

    // SELECT * FROM TASK WHERE completed = ? and title = ?
    List<Task> findByCompletedAndTitle(boolean completed, String title);

}
