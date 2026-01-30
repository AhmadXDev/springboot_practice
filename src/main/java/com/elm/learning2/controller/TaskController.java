package com.elm.learning2.controller;

import com.elm.learning2.dto.TaskRequest;
import com.elm.learning2.dto.TaskResponse;
import com.elm.learning2.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<TaskResponse> getTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks")
    public TaskResponse createTask(@Valid @RequestBody TaskRequest request){
        return taskService.createTask(request);
    }










}
