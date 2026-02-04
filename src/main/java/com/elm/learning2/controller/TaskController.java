package com.elm.learning2.controller;

import com.elm.learning2.dto.TaskDetailResponse;
import com.elm.learning2.dto.TaskRequest;
import com.elm.learning2.dto.TaskSummaryResponse;
import com.elm.learning2.dto.UpdateTaskCompletionRequest;
import com.elm.learning2.model.Task;
import com.elm.learning2.repository.TaskRepository;
import com.elm.learning2.service.TaskService;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "Task management API")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
    }

    // Main endpoints:

    @GetMapping
    public Page<TaskSummaryResponse> getTasks(
            @RequestParam(required = false) Integer completed,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return taskService.getTasks(completed, page, size, sortBy);
    }

    // Create a task
    @PostMapping
    public ResponseEntity<TaskSummaryResponse> createTask(@Valid @RequestBody TaskRequest request){
        TaskSummaryResponse response = taskService.createTask(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // Create a bulk of tasks with the same properties
    @PostMapping("/tasksbulk")
    public ResponseEntity<List<TaskSummaryResponse>> createTaskBulk(@Valid @RequestBody TaskRequest request){
        List<TaskSummaryResponse> responses = taskService.createTaskBulk(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(responses);

    }

    @PutMapping("/{id}")
    public TaskSummaryResponse updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest request){
        return taskService.updateTask(id, request);
    }

    @PatchMapping("/{id}/completed")
    public TaskSummaryResponse updateTaskCompletion(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskCompletionRequest request
    ) {
        return taskService.updateTaskCompletion(id, request.getCompleted());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    @GetMapping("/{id}")
    public TaskDetailResponse getTaskById(@PathVariable Long id){
        Task task = taskService.findTaskById(id);
        return new TaskDetailResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted()
        );
    }
}
