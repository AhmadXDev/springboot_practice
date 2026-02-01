package com.elm.learning2.service;

import com.elm.learning2.dto.TaskRequest;
import com.elm.learning2.dto.TaskResponse;
import com.elm.learning2.exception.TaskNotFoundException;
import com.elm.learning2.model.Task;
import com.elm.learning2.repoistory.TaskRepoistory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepoistory taskRepoistory;

    public TaskService(TaskRepoistory taskRepoistory){
        this.taskRepoistory = taskRepoistory;
    }

    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepoistory.findAll();
        List<TaskResponse> response = new ArrayList<>();

        for(Task task : tasks){
            response.add(
                    new TaskResponse(
                            task.getId(),
                            task.getTitle(),
                            task.isCompleted()
                    )
            );
        }
        return response;

    }

    public TaskResponse createTask(TaskRequest request){
        Task task = new Task(
                null,
                request.getTitle(),
                request.getDescription(),
                false
        );

        Task savedTask = taskRepoistory.save(task);

        return new TaskResponse(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.isCompleted()
        );
    }

    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Task task = new Task(
                id,
                taskRequest.getTitle(),
                taskRequest.getDescription(),
                false
        );

        Task updated = taskRepoistory.update(id, task);

        if (updated == null) {
            throw new TaskNotFoundException(id);
        }

        return new TaskResponse(
                updated.getId(),
                updated.getTitle(),
                updated.isCompleted()
        );

    }

    public void deleteTask(Long id) {
        if (!taskRepoistory.delete(id)) {
            throw new TaskNotFoundException(id);
        }

    }
}
