package com.elm.learning2.repoistory;


import com.elm.learning2.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepoistory {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Task> findAll() {
        return tasks;
    }

    public Task save(Task task){
        Task newTask = new Task(
                idCounter.getAndIncrement(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted()
        );
        tasks.add(newTask);
        return newTask;
    }
}
