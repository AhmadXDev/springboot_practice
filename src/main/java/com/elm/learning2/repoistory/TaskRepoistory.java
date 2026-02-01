package com.elm.learning2.repoistory;


import com.elm.learning2.dto.TaskRequest;
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

    public Task update(Long id, Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                Task currentTask = tasks.get(i);
                Task newTask = new Task(
                        id,
                        updatedTask.getTitle(),
                        updatedTask.getDescription(),
                        updatedTask.isCompleted()
                );
                tasks.set(i, newTask);
                return updatedTask;
            }
        }
        return null;
    }

    public boolean delete(Long id){
        return tasks.removeIf(task -> task.getId().equals(id));
    }
}
