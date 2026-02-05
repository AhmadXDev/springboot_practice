package com.elm.learning2.service;

import com.elm.learning2.dto.TaskRequest;
import com.elm.learning2.dto.TaskSummaryResponse;
import com.elm.learning2.exception.TaskNotFoundException;
import com.elm.learning2.model.Task;
import com.elm.learning2.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    private static final int MAX_PAGE_SIZE = 50;
    private static final List<String> ALLOWED_SORT_FIELDS =
            List.of("id", "title", "description");


    // Get tasks using Pageable
    public Page<TaskSummaryResponse> getTasks(Integer completed, int page, int size, String sortBy)  {
        if (page < 0) throw new IllegalArgumentException("Page index must be >= 0");

        if (size < 1 || size > MAX_PAGE_SIZE) throw new IllegalArgumentException("Size must be between 1 and " + MAX_PAGE_SIZE);

        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) throw new IllegalArgumentException("Invalid sort filed, sortBy must be one of: " + ALLOWED_SORT_FIELDS);


        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).ascending()
        );

        Page<Task> taskPage =
                completed != null
                    ? taskRepository.findByCompleted(completed == 1, pageable)
                    : taskRepository.findAll(pageable);

        return taskPage.map(TaskSummaryResponse::from);


    }

    public TaskSummaryResponse createTask(TaskRequest request) {
        Task task = new Task(
                null,
                request.getTitle(),
                request.getDescription(),
                false
        );

        Task savedTask = taskRepository.save(task);

        return new TaskSummaryResponse(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.isCompleted()
        );
    }


    @Transactional
    public List<TaskSummaryResponse> createTaskBulk(TaskRequest request) {
        List<TaskSummaryResponse> response = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Task task = new Task(
                    null,
                    request.getTitle(),
                    request.getDescription(),
                    false
            );

            Task savedTask = taskRepository.save(task);

            response.add(
                    new TaskSummaryResponse(
                            savedTask.getId(),
                            savedTask.getTitle(),
                            savedTask.isCompleted()
                    )
            );
        }

        return response;
    }

    public TaskSummaryResponse updateTask(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        Task updated = new Task(
                task.getId(),
                request.getTitle(),
                request.getDescription(),
                task.isCompleted()

        );

        Task saved = taskRepository.save(updated);

        return new TaskSummaryResponse(
                saved.getId(),
                saved.getTitle(),
                saved.isCompleted()
        );
    }

    @Transactional()
    public void deleteTask(Long id) {
        Task task = findTaskById(id);

        if (task.isDeleted()) {
            return;
        }

        task.markAsDeleted(true);
    }

    //! Wrong, I need to return TaskResponse and not expose Task
    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompleted(true);
    }

    //! Same as above
    public List<Task> getUncompletedTasks() {
        return taskRepository.findByCompleted(false);
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public TaskSummaryResponse updateTaskCompletion(Long id, boolean completed) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.markCompleted(completed);
        Task updatedTAsk = taskRepository.save(task);

        return TaskSummaryResponse.from(updatedTAsk);
    }

}
