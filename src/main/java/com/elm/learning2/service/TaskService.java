package com.elm.learning2.service;

import com.elm.learning2.dto.TaskRequest;
import com.elm.learning2.dto.TaskResponse;
import com.elm.learning2.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = new ArrayList<>();

        tasks.add(new Task(1L, "Learn Sping Boot", "Understand basics", false));
        tasks.add(new Task(2L, "Practice Java", "Write daily code", true));

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
                3L,
                request.getTitle(),
                request.getDescription(),
                false
        );

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.isCompleted()
        );
    }
}
